package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Demand implements Serializable {

    /***/
    @TableId(type = IdType.INPUT)
    private Integer id;
    /**
     * 产品类型
     */
    @JsonProperty(value = "fType")
    @NotBlank
    private String fType;
    /**
     * 需求单状态0为初始化，1为招标中，2为暂停招标，3为停止招标
     */
    private String status;
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
    @JsonProperty(value = "fRemark")
    private String fRemark;
    /**
     * 采购数量
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
    //  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
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
     * 截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date deadline;
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
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date creatTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 创建用户id
     */
    private String creatUserid;

    /**
     * 这里排除表字段
     */
    @TableField(exist = false)
    private UserEntity createUser;
}