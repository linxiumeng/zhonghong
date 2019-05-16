package org.springblade.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 走势图的价格实体
 */
@Data
public class FinancePrice {

    /**
     * id
     */
    Long id;

    /**
     * 走势图产品编码
     */
    String code;

    /**
     * 创建时间 使用createTime 利用long类型便于排序
     */
    Long createTime;

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



}
