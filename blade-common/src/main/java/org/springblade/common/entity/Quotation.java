package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("报价单实体类")
public class Quotation implements Serializable {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id",name="id",example = "1")
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 关联需求单id
     */
    @ApiModelProperty(value = "关联需求单id",name="demandId",example = "21")
    private Integer demandId;
    /**
     * 报价用户id
     */
    @ApiModelProperty(value = "报价用户id",name="userId",example = "23")
    private Long userId;
    /**
     * 单价，0为浮动价
     */
    @ApiModelProperty(value = "单价，0为浮动价",name="price",example = "0")
    private Double price;
    /**
     * 产品类型
     */
    @ApiModelProperty(value = "产品类型",name="fType",example = "13151665")
    @JsonProperty(value = "fType")
    private Long fType;
    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称",name="fName",example = "我是需求")
    @JsonProperty(value = "fName")
    private String fName;
    /**
     * 产品单位
     */
    @ApiModelProperty(value = "产品单位",name="fUnit",example = "****公司")
    @JsonProperty(value = "fUnit")
    private String fUnit;
    /**
     * 交易数量
     */
    @ApiModelProperty(value = "交易数量",name="num",example = "36")
    private Integer num;
    /**
     * 贸易条款
     */
    @ApiModelProperty(value = "贸易条款",name="tradeClause",example = "123")
    private String tradeClause;
    /**
     * 信用方式
     */
    @ApiModelProperty(value = "信用方式",name="paymentBy",example = "123")
    private String paymentBy;
    /**
     * 计价基准
     */
    @ApiModelProperty(value = "计价基准",name="pricingManner",example = "121")
    private String pricingManner;
    /**
     * 计价日期
     */
    @ApiModelProperty(value = "计价日期",name="contractualValueDate",example = "2019-12-01 12:23")
    private String contractualValueDate;
    /**
     * 升贴水
     */
    @ApiModelProperty(value = "升贴水",name="premiumsDiscounts",example = "23")
    private String premiumsDiscounts;
    /**
     * 支付日
     */
    @ApiModelProperty(value = "支付日",name="payDay",example = "2019-12-01 12:23")
    private String payDay;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",name="remark",example = " 这个油非常好")
    private String remark;
    /**
     * 油产地
     */
    @ApiModelProperty(value = "油产地",name="placeOfOrigin",example = "墨西哥")
    private String placeOfOrigin;
    /**
     * 油种类
     */
    @ApiModelProperty(value = "油种类",name="oilType",example = "石油")
    private String oilType;
    /**
     * api度
     */
    @ApiModelProperty(value = "api度",name="api",example = "12")
    private String api;
    /**
     * 含硫量%
     */
    @ApiModelProperty(value = "含硫量%",name="sulphurContent",example = "23")
    private String sulphurContent;
    /**
     * 酸值mgkoh/g
     */
    @ApiModelProperty(value = "酸值mgkoh/g",name="acidValue",example = "12")
    private String acidValue;
    /**
     * 残炭%
     */
    @ApiModelProperty(value = "残炭%",name="carbonResidue",example = "12")
    private String carbonResidue;
    /**
     * 镍ppm
     */
    @ApiModelProperty(value = "镍ppm",name="nickel",example = "25")
    private String nickel;
    /**
     * 钒ppm
     */
    @ApiModelProperty(value = "钒ppm",name="vanadium",example = "12")
    private String vanadium;
    /**
     * >350摄氏度质量收缩率（%）
     */
    @ApiModelProperty(value = ">350摄氏度质量收缩率（%）",name="shrink",example = "36")
    private String shrink;
    /**
     * 报价单状态0为初始
     */
    @ApiModelProperty(value = "报价单状态0为初始",name="status",example = "0")
    private String status;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="createDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",name="updateDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;


    /**
     * 提货地址
     */
    @ApiModelProperty(value = "提货地址",name="shipAddress",example = "杭州市余杭区")
    private String shipAddress;

    /**
     * 装载日期
     */
    @ApiModelProperty(value = "装载日期",name="loadDate",example = "2019-12-01 12:23")
    private String loadDate;

    /**
     * 其他説明
     */
    @ApiModelProperty(value = "其他説明",name="otherDesc",example = "可以随便说点什么")
    private String otherDesc;
    /**
     * 需求
     */
    @ApiModelProperty(value = "需求",name="demand",example = "我是需求")
    @TableField(exist = false)
    private Demand demand;

    /**
     * 原油指标文件
     */
    @ApiModelProperty(value = "原油指标文件",name="filePoint",example = "null")
    private String filePoint;

}