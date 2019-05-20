package org.springblade.information.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.entity.FinancePriceType;
import org.springblade.common.utils.DateUtils;
import org.springblade.common.utils.HttpClientUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

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
     * example url :
     * <p>
     * 这里5s执行一次
     */
    @Scheduled(cron = "0 0 10 * * *")
    public void scheduled() {



    }



}
