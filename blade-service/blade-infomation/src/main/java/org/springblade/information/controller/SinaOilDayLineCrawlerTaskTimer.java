package org.springblade.information.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.entity.FinanceDailyPrice;
import org.springblade.common.entity.FinancePriceType;
import org.springblade.common.utils.DateUtils;
import org.springblade.common.utils.HttpClientUtils;
import org.springblade.information.service.FinanceDailyPriceService;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springblade.common.utils.DateUtils.DATE_PATTERN;

/**
 * @author hanbin
 * 新浪的金融数据源 使用java的版本
 */
//@Component
@Api(tags = "新浪日K线的数据源")
public class SinaOilDayLineCrawlerTaskTimer {

    //   @Resource
    FinanceDailyPriceService financeDailyPriceService;

    private String todayUrlStr = null;


    //  @Autowired
    public SinaOilDayLineCrawlerTaskTimer(FinanceDailyPriceService financeDailyPriceService) {

        todayUrlStr = DateUtils.format(new Date(), "yyyy_M_dd");

        this.financeDailyPriceService = financeDailyPriceService;

        QueryWrapper clWrapper = Wrappers.query();
        clWrapper.eq("code", FinancePriceType.HF_CL.getCode());
        int clCount = financeDailyPriceService.count(clWrapper);

        if (clCount == 0) {
            //todo
            doBatchInsertWTI();
        }

        QueryWrapper oilWrapper = Wrappers.query();
        oilWrapper.eq("code", FinancePriceType.HF_OIL.getCode());
        int oilCount = financeDailyPriceService.count(oilWrapper);

        if (oilCount == 0) {
            //todo
            doBatchInsertBULUNTE();
        }


    }


    private static final Logger logger = LoggerFactory.getLogger(SinaOilDayLineCrawlerTaskTimer.class);

    /**
     * 定时器
     * 每个五秒钟执行一次 @Scheduled(cron = "0/5 * * * * *")  0 20 10 * * ?
     * 每天10点触发@Scheduled(cron = "0 0 10 * * *")
     * <p>
     * example url :  https://stock2.finance.sina.com.cn/futures/api/jsonp.php/var%20_OIL2019_5_20=/GlobalFuturesService.getGlobalFuturesDailyKLine?symbol=OIL&_=2019_5_20
     * <p>
     * <p>
     * https://stock2.finance.sina.com.cn/futures/api/jsonp.php/var%20_CL2019_5_20=/GlobalFuturesService.getGlobalFuturesDailyKLine?symbol=CL&_=2019_5_20
     * 这里每天10点执行一次
     */
    @Scheduled(cron = "0 0/10 * * * *")
    public void scheduled() {

        doWTIOil();

        doBULUNTEOil();

    }

    public void doWTIOil() {
        String response = getDailyKPriceResponse("https://stock2.finance.sina.com.cn/futures/api/jsonp.php/var%20_OIL" + todayUrlStr + "=/GlobalFuturesService.getGlobalFuturesDailyKLine?symbol=CL&_=" + todayUrlStr);

        String completeResponse = filterNoUseString(response);

        FinanceDailyPrice oilPrice = generateFinanceDailyPrice(completeResponse, FinancePriceType.HF_CL);

        if (oilPrice != null) {
            financeDailyPriceService.save(oilPrice);
        }
    }

    public void doBULUNTEOil() {

        String response = getDailyKPriceResponse("https://stock2.finance.sina.com.cn/futures/api/jsonp.php/var%20_OIL" + todayUrlStr + "=/GlobalFuturesService.getGlobalFuturesDailyKLine?symbol=OIL&_=" + todayUrlStr);

        String completeResponse = filterNoUseString(response);

        FinanceDailyPrice bulunTePrice = generateFinanceDailyPrice(completeResponse, FinancePriceType.HF_OIL);

        if (bulunTePrice != null) {
            financeDailyPriceService.save(bulunTePrice);
        }

    }

    private void doBatchInsertWTI() {

        String response = getDailyKPriceResponse("https://stock2.finance.sina.com.cn/futures/api/jsonp.php/var%20_OIL" + todayUrlStr + "=/GlobalFuturesService.getGlobalFuturesDailyKLine?symbol=CL&_=" + todayUrlStr);

        String completeResponse = filterNoUseString(response);

        insertBatch(completeResponse, FinancePriceType.HF_CL);

    }

    private void doBatchInsertBULUNTE() {
        String response = getDailyKPriceResponse("https://stock2.finance.sina.com.cn/futures/api/jsonp.php/var%20_OIL" + todayUrlStr + "=/GlobalFuturesService.getGlobalFuturesDailyKLine?symbol=OIL&_=" + todayUrlStr);

        String completeResponse = filterNoUseString(response);

        insertBatch(completeResponse, FinancePriceType.HF_OIL);
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
    private static FinanceDailyPrice generateFinanceDailyPrice(String completeResponseString, FinancePriceType financePriceType) {

        JSONArray convertedJSONArr = null;

        try {
            convertedJSONArr = JSON.parseArray(completeResponseString);
        } catch (JSONException e) {
            return null;
        }

        int lastDayIndex = convertedJSONArr.size() - 1;

        if (lastDayIndex < 0) {
            return null;
        }

        JSONObject lastDayJSON = convertedJSONArr.getJSONObject(lastDayIndex);

        if (lastDayJSON != null) {

            String thisDateStr = lastDayJSON.getString("date");
            if (StringUtils.isNotBlank(thisDateStr)) {

                Date lastDate = DateUtils.stringToDate(thisDateStr,DATE_PATTERN);
                String todayStr = DateUtils.format(new Date(), DATE_PATTERN);
                if (DateUtils.format(DateUtils.addDateDays(lastDate,1),DATE_PATTERN).equalsIgnoreCase(todayStr)) {
                    return null;
                } else {
                    FinanceDailyPrice financeDailyPrice = new FinanceDailyPrice();
                    financeDailyPrice.setCode(financePriceType.getCode());
                    financeDailyPrice.setCurrentPrice(lastDayJSON.getString(""));
                    financeDailyPrice.setTodayHighestPrice(lastDayJSON.getString("high"));
                    financeDailyPrice.setTodayOpenPrice(lastDayJSON.getString("open"));
                    financeDailyPrice.setTodayLowestPrice(lastDayJSON.getString("low"));
                    financeDailyPrice.setCreateDate(DateUtils.stringToDate(todayStr, DATE_PATTERN));
                    financeDailyPrice.setTodayClosePrice(lastDayJSON.getString("close"));
                    return financeDailyPrice;
                }
            }

        }


        return null;
    }


    /**
     * 生成日K的实体
     *
     * @return
     */
    private void insertBatch(String completeResponseString, FinancePriceType financePriceType) {

        JSONArray convertedJSONArr = null;

        try {
            convertedJSONArr = JSON.parseArray(completeResponseString);
        } catch (JSONException e) {
            return;
        }

        List<FinanceDailyPrice> list = new ArrayList<>(250);

        for (int i = 0; i < convertedJSONArr.size(); i++) {

            JSONObject lastDayJSON = convertedJSONArr.getJSONObject(i);

            if (lastDayJSON != null) {

                String thisDateStr = lastDayJSON.getString("date");
                if (StringUtils.isNotBlank(thisDateStr)) {
                    FinanceDailyPrice financeDailyPrice = new FinanceDailyPrice();
                    financeDailyPrice.setCode(financePriceType.getCode());
                    financeDailyPrice.setCurrentPrice(lastDayJSON.getString(""));
                    financeDailyPrice.setTodayHighestPrice(lastDayJSON.getString("high"));
                    financeDailyPrice.setTodayOpenPrice(lastDayJSON.getString("open"));
                    financeDailyPrice.setTodayLowestPrice(lastDayJSON.getString("low"));
                    financeDailyPrice.setCreateDate(DateUtils.stringToDate(thisDateStr, DATE_PATTERN));
                    financeDailyPrice.setTodayClosePrice(lastDayJSON.getString("close"));
                    list.add(financeDailyPrice);
                }
            }

            if (list.size() == 20) {
                boolean flag = financeDailyPriceService.saveBatch(list);
                if (!flag) {
                    return;
                }
                list.clear();
            }

        }

        if (list.size() > 0) {
            financeDailyPriceService.saveBatch(list);
        }

    }

    public static void main(String[] args) {
        Date date = new Date();
        date.setMonth(11);
        String todayUrlStr = DateUtils.format(date, "yyyy_M_dd");
        System.out.println(todayUrlStr);
    }


}
