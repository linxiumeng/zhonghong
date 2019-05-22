package org.springblade.information.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.entity.FinancePriceType;
import org.springblade.common.utils.DateUtils;
import org.springblade.common.utils.HttpClientUtils;
import org.springblade.information.service.FinancePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.springblade.common.utils.DateUtils.DATE_PATTERN;

/**
 * @author hanbin
 * 新浪的金融数据源 使用java的版本
 */
@Component
@Api(tags = "新浪的金融数据源")
public class SinaOilCrawlerTaskTimer {

    private static final Logger logger = LoggerFactory.getLogger(SinaOilCrawlerTaskTimer.class);

    /**
     * 基本url
     */
    private static final String BASE_URL = "https://hq.sinajs.cn/etag.php?";
    /**
     * 断掉的标示位
     */
    private static final String COMMA = "\"";

    /**
     * splite分解完为14位
     */
    private static final int FOREIGN_SPLITE_COUNT = 14;

    private static final int IN_SPLITE_COUNT = 28;

    private static final int INDEX_NUMBER_COUNT = 11;

    FinancePriceService financePriceService;

    RedisTemplate redisTemplate;


    @Autowired
    public SinaOilCrawlerTaskTimer(FinancePriceService financePriceService1, RedisTemplate redisTemplate) {
        this.financePriceService = financePriceService1;
        this.redisTemplate = redisTemplate;

        Date currentDate = new Date();
        currentDate.setHours(0);

        QueryWrapper loaWrapper = Wrappers.query();
        loaWrapper.eq("code", FinancePriceType.HF_OIL.getCode());
        loaWrapper.gt("create_time", currentDate);
        int loaCount = financePriceService.count(loaWrapper);
        if (loaCount == 0) {
            //todo
            doSaveCLData();
        }

        QueryWrapper loaWrapper1 = Wrappers.query();
        loaWrapper1.eq("code", FinancePriceType.HF_OIL.getCode());
        loaWrapper1.gt("create_time", currentDate);
        int loaCount1 = financePriceService.count(loaWrapper1);
        if (loaCount1 == 0) {
            //todo
            doSaveOILData();
        }
    }

    private void doSaveCLData() {

        String response = getDailyKPriceResponse("https://stock2.finance.sina.com.cn/futures/api/openapi.php/GlobalFuturesService.getGlobalFuturesMinLine?symbol=CL&callback=var%20t1hf_CL=");

        String completeResponse = filterNoUseString(response);

        insertBatch(completeResponse, FinancePriceType.HF_CL);
    }

    private void doSaveOILData() {

        String response = getDailyKPriceResponse("https://stock2.finance.sina.com.cn/futures/api/openapi.php/GlobalFuturesService.getGlobalFuturesMinLine?symbol=OIL&callback=var%20t1hf_OIL=");

        String completeResponse = filterNoUseString(response);

        insertBatch(completeResponse, FinancePriceType.HF_OIL);
    }

    /**
     * 过滤掉没用的非json字符串
     * <p>
     * /*<script>location.href='//sina.com';</script>
     * var _OIL2019_5_20 = (
     *
     * @return
     */
    private static String filterNoUseString(String response) {

        //获取第一个括号
        int firstQuoStrIndex = response.indexOf("(");

        //获取最后一个括号
        int lastQuoStrIndex = response.lastIndexOf(")");

        if (firstQuoStrIndex == -1 || lastQuoStrIndex == -1) {
            return response;
        }

        firstQuoStrIndex += 1;

        return response.substring(firstQuoStrIndex, lastQuoStrIndex);
    }

    /**
     * 生成日K的实体
     *
     * @return
     */
    private void insertBatch(String completeResponseString, FinancePriceType financePriceType) {

        JSONArray convertedJSONArr = null;

        try {
            JSONObject jsonObject = JSON.parseObject(completeResponseString);
            if (jsonObject != null) {
                JSONObject resultJSON = jsonObject.getJSONObject("result");
                if (resultJSON != null) {
                    convertedJSONArr = resultJSON.getJSONObject("data").getJSONArray("minLine_1d");
                }
            }
            if (convertedJSONArr == null) {
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        List<FinancePrice> list = new ArrayList<>(250);

        for (int i = 0; i < convertedJSONArr.size(); i++) {

            JSONArray minuteJSONArr = convertedJSONArr.getJSONArray(i);

            if (minuteJSONArr != null) {
                int size = minuteJSONArr.size();
                FinancePrice financePrice = new FinancePrice();
                financePrice.setCode(financePriceType.getCode());
                Date createDate = DateUtils.stringToDate(DateUtils.format(new Date(), DATE_PATTERN) + " " + minuteJSONArr.getString(size - 3), "yyyy-MM-dd HH:mm");
                financePrice.setCreateTime(createDate);
                financePrice.setCurrentPrice(minuteJSONArr.getString(size - 2));
                list.add(financePrice);
            }

            if (list.size() == 20) {
                boolean flag = financePriceService.saveBatch(list);
                if (!flag) {
                    return;
                }
                list.clear();
            }

        }

        for (int i = 0; i < list.size(); i++) {
            financePriceService.upsert(list.get(i));
        }

    }

    /**
     * 获取日k的响应
     *
     * @param url
     * @return
     */
    private String getDailyKPriceResponse(String url) {
        return HttpClientUtils.doGet(url);
    }


    /**
     * 定时器
     * 每个五秒钟执行一次 @Scheduled(cron = "0/5 * * * * *")  0 20 10 * * ?
     * 每天10点触发@Scheduled(cron = "0 0 10 * * *")
     * <p>
     * example url : https://hq.sinajs.cn/etag.php?_=1557995778049&list=hf_CL
     * <p>
     * 这里5s执行一次
     */
    @Scheduled(cron = "0/20 * * * * *")
    public void scheduled() {

        long currentTimeMills = System.currentTimeMillis();


        String completeUrl = BASE_URL + "_=" + currentTimeMills + "&list=" + FinancePriceType.genTotalCode();
        String responseStr = getSinaHttpResonpse(completeUrl);
        logger.info("getSinaHttpResponse str is {} ", responseStr);

        String[] resultArr = responseStr.split("\n");

        FinancePrice financePrice = null;

        for (String sub : resultArr) {

            //todo 增加开闭盘时间

            if (sub.contains(FinancePriceType.HF_CL.getCode())) {
                financePrice = generateForeignFinancePriceByResponse(sub, FinancePriceType.HF_CL);
            } else if (sub.contains(FinancePriceType.HF_OIL.getCode())) {
                financePrice = generateForeignFinancePriceByResponse(sub, FinancePriceType.HF_OIL);
            } else if (sub.contains(FinancePriceType.NF_SC0.getCode())) {
                financePrice = generateInFinancePriceByResponse(sub, FinancePriceType.NF_SC0);
            } else if (sub.contains(FinancePriceType.DINIW.getCode())) {
                financePrice = generateIndexNumberFinancePriceByResponse(sub, FinancePriceType.DINIW);
            }


            if (financePrice != null) {
                logger.info("generateFinancePriceByResponse' model is {} ", financePrice.toString());
                try {
                    //todo 入库
                    financePriceService.upsert(financePrice);
                } catch (DuplicateKeyException e) {
                    logger.info("重复数据入库失败 ： {} ", financePrice.toString());
                } catch (Exception e) {
                    //将堆栈信息打印出来
                    String fullStackTrace = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
                    logger.error("出现了异常 {} ", fullStackTrace);
                }
            }


        }


    }


    /* *//**
     *  定时器清除掉前一天的分时数据
     * @return
     *//*
    public void timRemoveDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10); // 控制时
        calendar.set(Calendar.MINUTE, 0);    // 控制分
        calendar.set(Calendar.SECOND, 0);    // 控制秒

        Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的10：00：00

        Timer timer = new Timer();

    }*/

    /**
     * 获取新浪网站的返回信息
     *
     * @param url
     * @return var hq_str_hf_CL="62.45,0.6933,62.44,62.45,62.55,62.08,17:05:55,62.02,62.13,6685,0,0,2019-05-16,纽约原油";
     */
    public static String getSinaHttpResonpse(String url) {
        return HttpClientUtils.doGet(url);
    }

    private static String[] filtAndSpliteString(String response, int commaPosition) {
        //截取字符串
        String information = response.substring(commaPosition);
        //替换所有 "
        information = information.replaceAll(COMMA, "").replace(";", "").replaceAll("\n", "");

        logger.info("str done is {} ", information);

        return information.split(",");

    }

    /**
     * 根据 response 生成 json
     *
     * @param response var hq_str_hf_CL="62.45,0.6933,62.44,62.45,62.55,62.08,17:05:55,62.02,62.13,6685,0,0,2019-05-16,纽约原油";
     * @return
     */
    public FinancePrice generateForeignFinancePriceByResponse(String response, FinancePriceType financePriceType) {

        FinancePrice financePrice = null;
        int commaPosition = 0;
        if (response != null && (commaPosition = response.indexOf(COMMA)) > 0) {

            String[] informationArr = filtAndSpliteString(response, commaPosition);

            //判断处理的数据的分组是不是为14
            if (informationArr.length == FOREIGN_SPLITE_COUNT) {

                String jsonDateString = informationArr[12] + " " + informationArr[6];
                String redisDateString = (String) redisTemplate.opsForValue().get(financePriceType.getCode());
                if (Objects.equals(jsonDateString, redisDateString)) {
                    return null;
                } else {
                    redisTemplate.opsForValue().set(financePriceType.getCode(), jsonDateString);
                }

                financePrice = new FinancePrice();

                //设置code
                financePrice.setCode(financePriceType.getCode());

                Date createDate = DateUtils.stringToDate(informationArr[12] + " " + informationArr[6].substring(0, informationArr[6].lastIndexOf(":")), "yyyy-MM-dd HH:mm");
                logger.info("create date is {} ", createDate);
                if (createDate == null) {
                    createDate = new Date();
                    createDate.setSeconds(0);
                }

                financePrice.setCreateTime(createDate);
                financePrice.setCurrentPrice(informationArr[0]);
                financePrice.setId(null);
                financePrice.setYesterdayClosePrice(informationArr[7]);
                financePrice.setTodayHighestPrice(informationArr[4]);
                financePrice.setTodayLowestPrice(informationArr[5]);
                financePrice.setTodayOpenPrice(informationArr[8]);
            }
        }

        return financePrice;

    }

    /**
     * 根据 response 生成 json
     *
     * @param response var hq_str_nf_SC0="原油连续,103153,510.40,514.90,507.40,506.50,514.40,514.50,514.40,511.80,504.60,18,22,33010,199422,沪,原油,2019-05-17,0,511.400,481.100,511.400,465.300,511.400,464.800,511.400,426.200,9.660";
     * @return
     */
    public static FinancePrice generateInFinancePriceByResponse(String response, FinancePriceType financePriceType) {

        FinancePrice financePrice = null;
        int commaPosition = 0;
        if (response != null && (commaPosition = response.indexOf(COMMA)) > 0) {

            String[] informationArr = filtAndSpliteString(response, commaPosition);

            logger.info("str arr done is {} ", Arrays.toString(informationArr));

            //判断处理的数据的分组是不是为14
            if (informationArr.length == IN_SPLITE_COUNT) {
                financePrice = new FinancePrice();

                financePrice.setCode(financePriceType.getCode());
                Date createDate = new Date();
                createDate.setSeconds(0);
                financePrice.setCreateTime(createDate);
                financePrice.setCurrentPrice(informationArr[8]);
                financePrice.setId(null);
                financePrice.setYesterdayClosePrice(informationArr[3]);
                financePrice.setTodayHighestPrice(informationArr[6]);
                financePrice.setTodayLowestPrice(informationArr[7]);
                financePrice.setTodayOpenPrice(informationArr[5]);
            }
        }

        return financePrice;

    }

    /**
     * 根据 response 生成 json
     *
     * @param response var hq_str_nf_SC0="原油连续,103153,510.40,514.90,507.40,506.50,514.40,514.50,514.40,511.80,504.60,18,22,33010,199422,沪,原油,2019-05-17,0,511.400,481.100,511.400,465.300,511.400,464.800,511.400,426.200,9.660";
     * @return
     */
    public static FinancePrice generateIndexNumberFinancePriceByResponse(String response, FinancePriceType financePriceType) {

        FinancePrice financePrice = null;
        int commaPosition = 0;
        if (response != null && (commaPosition = response.indexOf(COMMA)) > 0) {

            String[] informationArr = filtAndSpliteString(response, commaPosition);

            logger.info("str arr done is {} ", Arrays.toString(informationArr));

            //判断处理的数据的分组是不是为14
            if (informationArr.length == INDEX_NUMBER_COUNT) {
                financePrice = new FinancePrice();

                financePrice.setCode(financePriceType.getCode());
                Date createDate = DateUtils.stringToDate(informationArr[10] + " " + informationArr[0].substring(0, informationArr[0].lastIndexOf(":")), "yyyy-MM-dd HH:mm");
                logger.info("create date is {} ", createDate);
                if (createDate == null) {
                    createDate = new Date();
                    createDate.setSeconds(0);
                }
                financePrice.setCreateTime(createDate);
                financePrice.setCurrentPrice(informationArr[1]);
                financePrice.setId(null);
                financePrice.setYesterdayClosePrice(informationArr[3]);
                financePrice.setTodayHighestPrice(informationArr[6]);
                financePrice.setTodayLowestPrice(informationArr[7]);
                financePrice.setTodayOpenPrice(informationArr[5]);
            }
        }

        return financePrice;

    }

    public static void main(String[] args) {


    }


}
