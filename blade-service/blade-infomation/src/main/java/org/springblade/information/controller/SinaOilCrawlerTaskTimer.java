package org.springblade.information.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.entity.FinancePriceType;
import org.springblade.common.entity.News;
import org.springblade.common.utils.DateUtils;
import org.springblade.common.utils.HttpClientUtils;
import org.springblade.information.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hanbin
 * 新浪的金融数据源 使用java的版本
 */
//@Component
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

    /**
     * 定时器
     * 每个五秒钟执行一次 @Scheduled(cron = "0/5 * * * * *")  0 20 10 * * ?
     * 每天10点触发@Scheduled(cron = "0 0 10 * * *")
     * <p>
     * example url : https://hq.sinajs.cn/etag.php?_=1557995778049&list=hf_CL
     * <p>
     * 这里5s执行一次
     */
    //@Scheduled(cron = "0/5 * * * * *")
    public void scheduled() {

        long currentTimeMills = System.currentTimeMillis();


        String completeUrl = BASE_URL + "_=" + currentTimeMills + "&list=" + FinancePriceType.genTotalCode();
        String responseStr = getSinaHttpResonpse(completeUrl);
        logger.info("getSinaHttpResponse str is {} ", responseStr);

        String[] resultArr = responseStr.split("\n");

        FinancePrice financePrice = null;

        for(String sub : resultArr) {

            if(sub.contains(FinancePriceType.HF_CL.getCode())) {
                financePrice = generateForeignFinancePriceByResponse(sub, FinancePriceType.HF_CL);
            } else if(sub.contains(FinancePriceType.HF_OIL.getCode())){
                financePrice = generateForeignFinancePriceByResponse(sub,FinancePriceType.HF_OIL);
            } else if(sub.contains(FinancePriceType.NF_SC0.getCode())){
                financePrice = generateInFinancePriceByResponse(sub,FinancePriceType.NF_SC0);
            } else if(sub.contains(FinancePriceType.DINIW.getCode())){
                financePrice = generateIndexNumberFinancePriceByResponse(sub,FinancePriceType.DINIW);
            }


        }


        if (financePrice != null) {
            logger.info("generateFinancePriceByResponse' model is {} ", financePrice.toString());
            try {
                //todo 入库
            } catch (DuplicateKeyException e) {
                logger.info("重复数据入库失败 ： {} ", financePrice.toString());
            } catch (Exception e) {
                //将堆栈信息打印出来
                String fullStackTrace = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
                logger.error("出现了异常 {} ", fullStackTrace);
            }
        }


    }


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
    public static FinancePrice generateForeignFinancePriceByResponse(String response, FinancePriceType financePriceType) {

        FinancePrice financePrice = null;
        int commaPosition = 0;
        if (response != null && (commaPosition = response.indexOf(COMMA)) > 0) {

            String[] informationArr = filtAndSpliteString(response, commaPosition);

            //判断处理的数据的分组是不是为14
            if (informationArr.length == FOREIGN_SPLITE_COUNT) {
                financePrice = new FinancePrice();

                //设置code
                financePrice.setCode(financePriceType.getCode());

                Date createDate = DateUtils.stringToDate(informationArr[12] + " " + informationArr[6], "yyyy-MM-dd HH:mm:ss");
                logger.info("create date is {} ", createDate);
                long createTime = System.currentTimeMillis();
                if (createDate != null) {
                    createTime = createDate.getTime();
                } else {
                    createTime = ((long) (createTime / 1000)) * 1000;
                }
                financePrice.setCreateTime(createTime);
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
                long createTime = System.currentTimeMillis();
                //以秒来算
                createTime = ((long) (createTime / 1000)) * 1000;
                financePrice.setCreateTime(createTime);
                //      financePrice.setCurrentGrowPercent(informationArr[1]);
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
                Date createDate = DateUtils.stringToDate(informationArr[10] + " " + informationArr[0], "yyyy-MM-dd HH:mm:ss");
                logger.info("create date is {} ", createDate);
                long createTime = System.currentTimeMillis();
                if (createDate != null) {
                    createTime = createDate.getTime();
                } else {
                    createTime = ((long) (createTime / 1000)) * 1000;
                }
                financePrice.setCreateTime(createTime);
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

        /*long currentTimeMills = System.currentTimeMillis();


        String completeUrl = BASE_URL + "_=" + currentTimeMills + "&list=" + FinancePriceType.genTotalCode();
        String responseStr = getSinaHttpResonpse(completeUrl);
        logger.info("getSinaHttpResponse str is {} ", responseStr);

        String[] resultArr = responseStr.split("\n");

        FinancePrice financePrice = null;

        for(String sub : resultArr) {

            if(sub.contains(FinancePriceType.HF_CL.getCode())) {
                financePrice = generateForeignFinancePriceByResponse(responseStr, FinancePriceType.HF_CL);
            } else if(sub.contains(FinancePriceType.HF_OIL.getCode())){
                financePrice = generateForeignFinancePriceByResponse(responseStr,FinancePriceType.HF_OIL);
            } else if(sub.contains(FinancePriceType.NF_SC0.getCode())){
                financePrice = generateInFinancePriceByResponse(responseStr,FinancePriceType.NF_SC0);
            } else if(sub.contains(FinancePriceType.DINIW.getCode())){
                financePrice = generateIndexNumberFinancePriceByResponse(responseStr,FinancePriceType.DINIW);
            }


        }


        if (financePrice != null) {
            logger.info("generateFinancePriceByResponse' model is {} ", financePrice.toString());
            try {
                //todo 入库
            } catch (DuplicateKeyException e) {
                logger.info("重复数据入库失败 ： {} ", financePrice.toString());
            } catch (Exception e) {
                //将堆栈信息打印出来
                String fullStackTrace = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
                logger.error("出现了异常 {} ", fullStackTrace);
            }
        }*/

    }


}
