package org.springblade.information.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.entity.FinanceDailyPrice;
import org.springblade.common.entity.FinancePriceType;
import org.springblade.common.utils.DateUtils;
import org.springblade.common.utils.HttpClientUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

import static org.springblade.common.utils.DateUtils.DATE_PATTERN;

/**
 * @author hanbin
 * 新浪的金融数据源 使用java的版本
 */
@Component
@Api(tags = "新浪日K线的数据源")
public class SinaOilDayLineCrawlerTaskTimer {

    private static final Logger logger = LoggerFactory.getLogger(SinaOilDayLineCrawlerTaskTimer.class);

    /**
     * 定时器
     * 每个五秒钟执行一次 @Scheduled(cron = "0/5 * * * * *")  0 20 10 * * ?
     * 每天10点触发@Scheduled(cron = "0 0 10 * * *")
     * <p>
     * example url :  https://stock2.finance.sina.com.cn/futures/api/jsonp.php/var%20_OIL2019_5_20=/GlobalFuturesService.getGlobalFuturesDailyKLine?symbol=OIL&_=2019_5_20
     * <p>
     * 这里每天10点执行一次
     */
    @Scheduled(cron = "0 0 10 * * *")
    public void scheduled() {

        String response = getDailyKPriceResponse("");

        String completeResponse = filterNoUseString(response);

        FinanceDailyPrice oilPrice = generateFinanceDailyPrice(completeResponse,FinancePriceType.HF_CL);

        FinanceDailyPrice bulunTePrice = generateFinanceDailyPrice(completeResponse,FinancePriceType.HF_OIL);

        if(oilPrice != null){


        }
        if(bulunTePrice != null){


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

        if(lastDayIndex < 0){
            return null;
        }

        JSONObject lastDayJSON = convertedJSONArr.getJSONObject(lastDayIndex);

        if (lastDayJSON != null) {

            String thisDateStr = lastDayJSON.getString("date");
            if (StringUtils.isNotBlank(thisDateStr)) {

                String todayStr = DateUtils.format(new Date(),DATE_PATTERN);
                if(todayStr.equalsIgnoreCase(thisDateStr)){
                    return null;
                }else{
                    FinanceDailyPrice financeDailyPrice = new FinanceDailyPrice();
                    financeDailyPrice.setCode(financePriceType.getCode());
                    financeDailyPrice.setCurrentPrice(lastDayJSON.getString(""));
                    financeDailyPrice.setTodayHighestPrice(lastDayJSON.getString("high"));
                    financeDailyPrice.setTodayOpenPrice(lastDayJSON.getString("open"));
                    financeDailyPrice.setTodayLowestPrice(lastDayJSON.getString("low"));
                    financeDailyPrice.setCreateDate(DateUtils.stringToDate(todayStr,DATE_PATTERN));
                    financeDailyPrice.setTodayClosePrice(lastDayJSON.getString("close"));
                    return financeDailyPrice;
                }
            }

        }


        return null;
    }


    public static void main(String[] args) {
    }


}
