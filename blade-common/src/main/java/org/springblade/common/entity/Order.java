package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表(Order) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-14 10:51:48
 */
@Data
@TableName("tb_order")
@ApiModel("订单表实体类")
public class Order {

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号",name="orderNo",example = "1")
    @TableId(type = IdType.INPUT)
    private Integer orderNo;
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id",name="goodsId",example = "1124")
    private Integer goodsId;
    /**
     * 快照id
     */
    @ApiModelProperty(value = "快照id",name="shotIds",example = "112")
    private String shotIds;
    /**
     * 商品名
     */
    @ApiModelProperty(value = "商品名",name="goodsName",example = "什么什么汽油")
    private String goodsName;
    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片",name="goodsPic",example = "")
    private String goodsPic;
    /**
     * 供应公司名
     */
    @ApiModelProperty(value = "供应公司名",name="componyName",example = "某公司")
    private String componyName;
    /**
     * 采购公司名
     */
    @ApiModelProperty(value = "采购公司名",name="purchaseCompony",example = "某某公司")
    private String purchaseCompony;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",name="userId",example = "1")
    private Long userId;
    /**
     * 采购数量
     */
    @ApiModelProperty(value = "采购数量",name="orderSize",example = "136")
    private Double orderSize;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址",name="address",example = "某某某某某")
    private String address;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话",name="phone",example = "1*********")
    private String phone;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人",name="contacts",example = "谁都行")
    private String contacts;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",name="remark",example = "这个油挺好的")
    private String remark;
    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额",name="orderPrice",example = "666666985")
    private BigDecimal orderPrice;
    /**
     * 订单状态0为初始化
     */
    @ApiModelProperty(value = "订单状态0为初始化",name="status",example = "0")
    private String status;
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

}