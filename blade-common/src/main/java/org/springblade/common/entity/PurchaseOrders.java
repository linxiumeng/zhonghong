package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class PurchaseOrders {

    /***/
    @TableId(type = IdType.INPUT)
    private Integer id;
    /**
     * 买方id
     */
    private Long buyerId;
    /**
     * 买方公司
     */
    private String buyerCompany;
    /**
     * 买方联系人
     */
    private String buyerLinkman;
    /**
     * 买方电话
     */
    private String buyerPhone;
    /**
     * 买方邮箱
     */
    private String buyerEmail;
    /**
     * 买方地址
     */
    private String buyerAddress;
    /**
     * 卖方id
     */
    private Long providerId;
    /**
     * 卖方公司
     */
    private String providerCompany;
    /**
     * 卖方联系人
     */
    private String providerLinkman;
    /**
     * 卖方电话
     */
    private String providerPhone;
    /**
     * 卖方邮箱
     */
    private String providerEmail;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品图片
     */
    private String goodsPic;
    /**
     * 报价单id
     */
    private Integer quotationId;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品类型
     */
    private String goodsType;
    /**
     * 贸易条款
     */
    private String tradeClause;
    /**
     * 信用方式
     */
    private String paymentBy;
    /**
     * 商品单价
     */
    private String goodsPrice;
    /**
     * 最终报价，浮动价格确认后填入
     */
    private String finalQuotation;
    /**
     * 计价基准
     */
    private String pricingManner;
    /**
     * 计价日期
     */
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
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
     * 买方备注
     */
    private String buyerRemark;
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
     * 采购单状态0为初始化
     * @see org.springblade.common.enums.OrdersEnum
     */
    private OrdersEnum status;
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
     * 商品数量
     */
    private Integer goodsAmount;

    /**
     * 提货地址
     */
    private String shipAddress;

    /**
     * 装载日期
     */
    private String loadDate;

    /**
     * 商品单位
     */
    private String goodsUnit;

    /**
     * 商品備注
     */
    private String goodsRemark;

    /**
     * 合同
     */
    private String contract;

    /**
     * 打回原因
     */
    private String refuseCause;

    /**
     * 结算单据
     */
    private String settleBill;

    /**
     * 原油指标文件
     */
    private String filePoint;

}