package org.springblade.information.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.entity.News;
import org.springblade.common.utils.DateUtils;
import org.springblade.common.utils.HttpClientUtils;
import org.springblade.information.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 新闻爬取的爬虫 使用java的版本
 */
@Component
@Api(tags ="新闻爬虫（java）")
public class SinaOilCrawlerTaskTimer{

    private static final Logger logger = LoggerFactory.getLogger(SinaOilCrawlerTaskTimer.class);

    /**
     * 姐断掉的标示位
     *
     */
    private static final String COMMA = "\"";

    /**
     * splite分解完为14位
     */
    private static final int SPLITE_COUNT  = 14;

    /**
     * 定时器
     * 每个五秒钟执行一次 @Scheduled(cron = "0/5 * * * * *")  0 20 10 * * ?
     * 每天10点触发@Scheduled(cron = "0 0 10 * * *")
     *
     * example url : https://hq.sinajs.cn/etag.php?_=1557995778049&list=hf_CL
     */
    @Scheduled(cron = "0 0 0/1 * * *")
    public void scheduled() {
        logger.info(getSinaHttpResonpse("https://hq.sinajs.cn/etag.php?_=1557995778049&list=hf_CL"));
    }


    /**
     * 获取新浪网站的返回信息
     * @param url
     * @return  var hq_str_hf_CL="62.45,0.6933,62.44,62.45,62.55,62.08,17:05:55,62.02,62.13,6685,0,0,2019-05-16,纽约原油";
     */
    public static String getSinaHttpResonpse(String url){
        return HttpClientUtils.doGet(url);
    }

    /**
     * 根据 response 生成 json
     * @param response var hq_str_hf_CL="62.45,0.6933,62.44,62.45,62.55,62.08,17:05:55,62.02,62.13,6685,0,0,2019-05-16,纽约原油";
     * @return
     */
    public static FinancePrice generateFinancePriceByResponse(String response){

        FinancePrice financePrice = null;
        int commaPosition = 0;
        if(response != null &&  (commaPosition = response.indexOf(COMMA)) > 0){

            //截取字符串
            String information = response.substring(commaPosition);
            //替换所有 "
            information = information.replaceAll(COMMA,"").replace(";","");

            logger.info("str done is {} ",information);

            String[] informationArr = information.split(",");

            logger.info("str arr done is {} ",Arrays.toString(informationArr));

            //判断处理的数据的分组是不是为14
            if(informationArr.length == SPLITE_COUNT){
                financePrice = new FinancePrice();
                financePrice.setCode(informationArr[13]);
                Date createDate = DateUtils.stringToDate(informationArr[12]+" "+informationArr[6],"yyyy-MM-dd HH:mm:ss");
                logger.info("create date is {} ",createDate);
                long createTime = System.currentTimeMillis();
                if(createDate != null){
                    createTime = createDate.getTime();
                }
                financePrice.setCreateTime(createTime);
                financePrice.setCurrentGrowPercent(informationArr[1]);
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


    public static void main(String[] args) {
        String resonpse = getSinaHttpResonpse("https://hq.sinajs.cn/etag.php?_=1557995778049&list=hf_CL");
        logger.info("now response is {} "+resonpse);
        FinancePrice financePrice = generateFinancePriceByResponse(resonpse);
        logger.info("the generate financePrice is {} ",financePrice);
    }




}
