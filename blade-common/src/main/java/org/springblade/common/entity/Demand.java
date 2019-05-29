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

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * (Demand) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-07 10:57:43
 */
@Data
@TableName("tb_demand")
@ApiModel("需求实体类")
public class Demand implements Serializable {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id",name="id",example = "1")
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 产品类型
     */
    @ApiModelProperty(value = "产品类型",name="fType",example = "")
    @JsonProperty(value = "fType")
   // @NotBlank
    private Long fType;
    /**
     * 需求单状态0为初始化，1为招标中，2为暂停招标，3为停止招标
     */
    @ApiModelProperty(value = "需求单状态0为初始化，1为招标中，2为暂停招标，3为停止招标",name="status",example = "1")
    private Integer status;
    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称",name="fName",example = "中石化原油")
    @JsonProperty(value = "fName")
    private String fName;
    /**
     * 产品单位
     */
    @ApiModelProperty(value = "产品单位",name="fUnit",example = "吨")
    @JsonProperty(value = "fUnit")
    private String fUnit;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",name="fRemark",example = "这油可好用的嘞")
    @JsonProperty(value = "fRemark")
    private String fRemark;
    /**
     * 采购数量
     */
    @ApiModelProperty(value = "采购数量",name="num",example = "66")
    private Integer num;
    /**
     * 贸易条款
     */
    @ApiModelProperty(value = "贸易条款",name="tradeClause",example = "")
    private String tradeClause;
    /**
     * 信用方式
     */
    @ApiModelProperty(value = "信用方式",name="paymentBy",example = "")
    private String paymentBy;
    /**
     * 计价基准
     */
    @ApiModelProperty(value = "计价基准",name="pricingManner",example = "")
    private String pricingManner;
    /**
     * 计价日期
     */
    @ApiModelProperty(value = "计价日期",name="contractualValueDate",example = "2019-12-01 12:23")
    //  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private String contractualValueDate;
    /**
     * 升贴水
     */
    @ApiModelProperty(value = "升贴水",name="premiumsDiscounts",example = "1")
    private String premiumsDiscounts;
    /**
     * 支付日
     */
    @ApiModelProperty(value = "支付日",name="payDay",example = "2019-12-01 12:23")
    private String payDay;
    /**
     * 截止时间
     */
    @ApiModelProperty(value = "截止时间",name="deadline",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date deadline;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",name="remark",example = "这个油真的非常好用的")
    private String remark;
    /**
     * 油产地
     */
    @ApiModelProperty(value = "油产地",name="placeOfOrigin",example = "阿富汗")
    private String placeOfOrigin;
    /**
     * 油种类
     */
    @ApiModelProperty(value = "油种类",name="oilType",example = "原油")
    private String oilType;
    /**
     * api度
     */
    @ApiModelProperty(value = "api度",name="api",example = "1")
    private String api;
    /**
     * 含硫量%
     */
    @ApiModelProperty(value = "含硫量%",name="sulphurContent",example = "1")
    private String sulphurContent;
    /**
     * 酸值mgkoh/g
     */
    @ApiModelProperty(value = "酸值mgkoh/g",name="acidValue",example = "1")
    private String acidValue;
    /**
     * 残炭%
     */
    @ApiModelProperty(value = "残炭%",name="carbonResidue",example = "1")
    private String carbonResidue;
    /**
     * 镍ppm
     */
    @ApiModelProperty(value = "镍ppm",name="nickel",example = "1")
    private String nickel;
    /**
     * 钒ppm
     */
    @ApiModelProperty(value = "钒ppm",name="vanadium",example = "1")
    private String vanadium;
    /**
     * >350摄氏度质量收缩率（%）
     */
    @ApiModelProperty(value = ">350摄氏度质量收缩率（%）",name="shrink",example = "1")
    private String shrink;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="creatTime",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date creatTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间",name="updateTime",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 创建用户id
     */
    @ApiModelProperty(value = "创建用户id",name="creatUserid",example = "1")
    private String creatUserid;

    /**
     * 这里排除表字段
     */
    @ApiModelProperty(value = "这里排除表字段",name="createUser",example = "")
    @TableField(exist = false)
    private UserEntity createUser;
    /**
     * 商品类别表实体类
     */
    @ApiModelProperty(value = "商品类别表实体类",name="goodsTypeEntity",example = "商品")
    @TableField(exist = false)
    private GoodsTypeEntity goodsTypeEntity;

    @TableField(exist = false)
    private Long quotationNumber;
}