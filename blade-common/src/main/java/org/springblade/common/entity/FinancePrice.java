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
@TableName("tb_finance_price")
public class FinancePrice {

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
     * 创建时间 使用createTime 利用long类型便于排序
     */
    @JsonFormat(pattern = "HH", timezone = "GMT+8")
    Date createTime;

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
