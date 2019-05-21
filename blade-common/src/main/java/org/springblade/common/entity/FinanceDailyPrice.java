package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 走势图的价格实体
 */
@Data
@TableName("tb_finance_daily_k_price")
public class FinanceDailyPrice {

    /**
     * id
     */
    @TableId(type = IdType.INPUT)
    Long id;

    /**
     * 走势图产品编码
     */
    String code;

    /**
     * 日K的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date createDate;

    /**
     * 当前价格
     */
    String currentPrice;

    /**
     * 今日最高价
     */
    String todayHighestPrice;

    /**
     * 今日最低价
     */
    String todayLowestPrice;

    /**
     * 当前成长百分比
     */
    String currentGrowPercent;

    /**
     * 今日开盘价
     */
    String todayOpenPrice;

    /**
     * 昨日收盘价/昨日结算价
     */
    String yesterdayClosePrice;

    /**
     * 今天收盘价
     */
    String todayClosePrice;



}
