package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 报价单表
 * (Quotation) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-10 11:59:55
 */
@Data
@TableName("tb_quotation")
public class Quotation implements Serializable {

    /***/
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 关联需求单id
     */
    private Integer demandId;
    /**
     * 报价用户id
     */
    private Long userId;
    /**
     * 单价，0为浮动价
     */
    private Double price;
    /**
     * 产品类型
     */
    @JsonProperty(value = "fType")
    private String fType;
    /**
     * 产品名称
     */
    @JsonProperty(value = "fName")
    private String fName;
    /**
     * 产品单位
     */
    @JsonProperty(value = "fUnit")
    private String fUnit;
    /**
     * 交易数量
     */
    private Integer num;
    /**
     * 贸易条款
     */
    private String tradeClause;
    /**
     * 信用方式
     */
    private String paymentBy;
    /**
     * 计价基准
     */
    private String pricingManner;
    /**
     * 计价日期
     */
    private String contractualValueDate;
    /**
     * 升贴水
     */
    private String premiumsDiscounts;
    /**
     * 支付日
     */
    private String payDay;
    /**
     * 备注
     */
    private String remark;
    /**
     * 油产地
     */
    private String placeOfOrigin;
    /**
     * 油种类
     */
    private String oilType;
    /**
     * api度
     */
    private String api;
    /**
     * 含硫量%
     */
    private String sulphurContent;
    /**
     * 酸值mgkoh/g
     */
    private String acidValue;
    /**
     * 残炭%
     */
    private String carbonResidue;
    /**
     * 镍ppm
     */
    private String nickel;
    /**
     * 钒ppm
     */
    private String vanadium;
    /**
     * >350摄氏度质量收缩率（%）
     */
    private String shrink;
    /**
     * 报价单状态0为初始
     */
    private String status;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;


    /**
     * 提货地址
     */
    private String shipAddress;

    /**
     * 装载日期
     */
    private String loadDate;

    /**
     * 其他説明
     */
    private String otherDesc;

    @TableField(exist = false)
    private Demand demand;

    /**
     * 原油指标文件
     */
    private String filePoint;

}