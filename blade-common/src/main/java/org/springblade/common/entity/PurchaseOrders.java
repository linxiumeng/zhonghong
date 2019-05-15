package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.enums.OrdersEnum;

import java.util.Date;

/**
 * 采购单表(PurchaseOrders) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-10 17:12:38
 */
@Data
@TableName("tb_purchase_orders")
@ApiModel("采购单表实体类")

public class PurchaseOrders implements Cloneable{

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id",name="orderSize",example = "1")
    @TableId(type = IdType.INPUT)
    private Integer id;
    /**
     * 买方id
     */
    @ApiModelProperty(value = "买方id",name="orderSize",example = "41")
    private Long buyerId;
    /**
     * 买方公司
     */
    @ApiModelProperty(value = "买方公司",name="orderSize",example = "上海诺奚")
    private String buyerCompany;
    /**
     * 买方联系人
     */
    @ApiModelProperty(value = "买方联系人",name="orderSize",example = "张先生")
    private String buyerLinkman;
    /**
     * 买方电话
     */
    @ApiModelProperty(value = "买方电话",name="orderSize",example = "15986354212")
    private String buyerPhone;
    /**
     * 买方邮箱
     */
    @ApiModelProperty(value = "买方邮箱",name="orderSize",example = "524982273@163.com")
    private String buyerEmail;
    /**
     * 买方地址
     */
    @ApiModelProperty(value = "买方地址",name="orderSize",example = "杭州市江干区")
    private String buyerAddress;
    /**
     * 卖方id
     */
    @ApiModelProperty(value = "卖方id",name="orderSize",example = "36")
    private Long providerId;
    /**
     * 卖方公司
     */
    @ApiModelProperty(value = "卖方公司",name="orderSize",example = "某某公司")
    private String providerCompany;
    /**
     * 卖方联系人
     */
    @ApiModelProperty(value = "卖方联系人",name="orderSize",example = "某某")
    private String providerLinkman;
    /**
     * 卖方电话
     */
    @ApiModelProperty(value = "卖方电话",name="orderSize",example = "13626351236")
    private String providerPhone;
    /**
     * 卖方邮箱
     */
    @ApiModelProperty(value = "卖方邮箱",name="orderSize",example = "*******@163.com")
    private String providerEmail;
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id",name="orderSize",example = "136")
    private Integer goodsId;
    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片",name="orderSize",example = "")
    private String goodsPic;
    /**
     * 报价单id
     */
    @ApiModelProperty(value = "报价单id",name="orderSize",example = "136")
    private Integer quotationId;
    /**
     * 商品名
     */
    @ApiModelProperty(value = "商品名",name="orderSize",example = "**石油")
    private String goodsName;
    /**
     * 商品类型
     */
    @ApiModelProperty(value = "商品类型",name="orderSize",example = "1")
    private Long goodsType;
    /**
     * 贸易条款
     */
    @ApiModelProperty(value = "贸易条款",name="orderSize",example = "136")
    private String tradeClause;
    /**
     * 信用方式
     */
    @ApiModelProperty(value = "信用方式",name="orderSize",example = "136")
    private String paymentBy;
    /**
     * 商品单价
     */
    @ApiModelProperty(value = "商品单价",name="orderSize",example = "136")
    private String goodsPrice;
    /**
     * 最终报价，浮动价格确认后填入
     */
    @ApiModelProperty(value = "最终报价，浮动价格确认后填入",name="orderSize",example = "136")
    private String finalQuotation;
    /**
     * 计价基准
     */
    @ApiModelProperty(value = "计价基准",name="orderSize",example = "136")
    private String pricingManner;
    /**
     * 计价日期
     */
    @ApiModelProperty(value = "计价日期",name="orderSize",example = "2019-12-01 12:23")
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private String contractualValueDate;
    /**
     * 升贴水
     */
    @ApiModelProperty(value = "升贴水",name="orderSize",example = "136")
    private String premiumsDiscounts;
    /**
     * 支付日
     */
    @ApiModelProperty(value = "支付日",name="orderSize",example = "2019-12-01 12:23")
    private String payDay;
    /**
     * 买方备注
     */
    @ApiModelProperty(value = "买方备注",name="orderSize",example = "*先生")
    private String buyerRemark;
    /**
     * 油产地
     */
    @ApiModelProperty(value = "油产地",name="orderSize",example = "墨西哥")
    private String placeOfOrigin;
    /**
     * 油种类
     */
    @ApiModelProperty(value = "油种类",name="orderSize",example = "汽油")
    private String oilType;
    /**
     * api度
     */
    @ApiModelProperty(value = "api度",name="orderSize",example = "31")
    private String api;
    /**
     * 含硫量%
     */
    @ApiModelProperty(value = "含硫量%",name="orderSize",example = "21")
    private String sulphurContent;
    /**
     * 酸值mgkoh/g
     */
    @ApiModelProperty(value = "酸值mgkoh/g",name="orderSize",example = "12")
    private String acidValue;
    /**
     * 残炭%
     */
    @ApiModelProperty(value = "残炭%",name="orderSize",example = "14")
    private String carbonResidue;
    /**
     * 镍ppm
     */
    @ApiModelProperty(value = "镍ppm",name="orderSize",example = "21")
    private String nickel;
    /**
     * 钒ppm
     */
    @ApiModelProperty(value = "钒ppm",name="orderSize",example = "15")
    private String vanadium;
    /**
     * >350摄氏度质量收缩率（%）
     */
    @ApiModelProperty(value = ">350摄氏度质量收缩率（%）",name="orderSize",example = "32")
    private String shrink;
    /**
     * 采购单状态0为初始化
     * @see org.springblade.common.enums.OrdersEnum
     */
    @ApiModelProperty(value = "采购单状态0为初始化",name="orderSize",example = "0")
    private OrdersEnum status;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="orderSize",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date creatTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "修改时间",name="orderSize",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量",name="orderSize",example = "136")
    private Integer goodsAmount;

    /**
     * 提货地址
     */
    @ApiModelProperty(value = "提货地址",name="orderSize",example = "阿富汗")
    private String shipAddress;

    /**
     * 装载日期
     */
    @ApiModelProperty(value = "装载日期",name="orderSize",example = "2019-12-01 12:23")
    private String loadDate;

    /**
     * 商品单位
     */
    @ApiModelProperty(value = "商品单位",name="orderSize",example = "吨")
    private String goodsUnit;

    /**
     * 商品備注
     */
    @ApiModelProperty(value = "商品備注",name="orderSize",example = "上好的汽油")
    private String goodsRemark;

    /**
     * 合同
     */
    @ApiModelProperty(value = "合同",name="orderSize",example = "136")
    private String contract;

    /**
     * 打回原因
     */
    @ApiModelProperty(value = "打回原因",name="orderSize",example = "未通过")
    private String refuseCause;

    /**
     * 结算单据
     */
    @ApiModelProperty(value = "结算单据",name="orderSize",example = "136")
    private String settleBill;

    /**
     * 原油指标文件
     */
    @ApiModelProperty(value = "原油指标文件",name="orderSize",example = "136")
    private String filePoint;
    /**
     * 商品类别实体类
     */
    @ApiModelProperty(value = "商品类别实体类",name="goodsTypeEntity",example = "")
    @TableField(exist = false)
    private GoodsTypeEntity goodsTypeEntity;


    @Override
    public Object clone() throws CloneNotSupportedException {
        return (PurchaseOrders)super.clone();
    }
}