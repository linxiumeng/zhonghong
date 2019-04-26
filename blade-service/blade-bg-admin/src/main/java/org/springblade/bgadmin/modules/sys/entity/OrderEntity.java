package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 采购单表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_order")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 买方id
     */
    private Integer buyerId;
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
     * 卖方id
     */
    private Integer providerId;
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
    private Date contractualValueDate;
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
     * 订单状态0为初始化
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date creatTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：买方id
     */
    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * 获取：买方id
     */
    public Integer getBuyerId() {
        return buyerId;
    }

    /**
     * 设置：买方公司
     */
    public void setBuyerCompany(String buyerCompany) {
        this.buyerCompany = buyerCompany;
    }

    /**
     * 获取：买方公司
     */
    public String getBuyerCompany() {
        return buyerCompany;
    }

    /**
     * 设置：买方联系人
     */
    public void setBuyerLinkman(String buyerLinkman) {
        this.buyerLinkman = buyerLinkman;
    }

    /**
     * 获取：买方联系人
     */
    public String getBuyerLinkman() {
        return buyerLinkman;
    }

    /**
     * 设置：买方电话
     */
    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    /**
     * 获取：买方电话
     */
    public String getBuyerPhone() {
        return buyerPhone;
    }

    /**
     * 设置：买方邮箱
     */
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    /**
     * 获取：买方邮箱
     */
    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**
     * 设置：卖方id
     */
    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    /**
     * 获取：卖方id
     */
    public Integer getProviderId() {
        return providerId;
    }

    /**
     * 设置：卖方公司
     */
    public void setProviderCompany(String providerCompany) {
        this.providerCompany = providerCompany;
    }

    /**
     * 获取：卖方公司
     */
    public String getProviderCompany() {
        return providerCompany;
    }

    /**
     * 设置：卖方联系人
     */
    public void setProviderLinkman(String providerLinkman) {
        this.providerLinkman = providerLinkman;
    }

    /**
     * 获取：卖方联系人
     */
    public String getProviderLinkman() {
        return providerLinkman;
    }

    /**
     * 设置：卖方电话
     */
    public void setProviderPhone(String providerPhone) {
        this.providerPhone = providerPhone;
    }

    /**
     * 获取：卖方电话
     */
    public String getProviderPhone() {
        return providerPhone;
    }

    /**
     * 设置：卖方邮箱
     */
    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    /**
     * 获取：卖方邮箱
     */
    public String getProviderEmail() {
        return providerEmail;
    }

    /**
     * 设置：商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置：报价单id
     */
    public void setQuotationId(Integer quotationId) {
        this.quotationId = quotationId;
    }

    /**
     * 获取：报价单id
     */
    public Integer getQuotationId() {
        return quotationId;
    }

    /**
     * 设置：商品名
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取：商品名
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置：商品类型
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 获取：商品类型
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * 设置：贸易条款
     */
    public void setTradeClause(String tradeClause) {
        this.tradeClause = tradeClause;
    }

    /**
     * 获取：贸易条款
     */
    public String getTradeClause() {
        return tradeClause;
    }

    /**
     * 设置：信用方式
     */
    public void setPaymentBy(String paymentBy) {
        this.paymentBy = paymentBy;
    }

    /**
     * 获取：信用方式
     */
    public String getPaymentBy() {
        return paymentBy;
    }

    /**
     * 设置：商品单价
     */
    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取：商品单价
     */
    public String getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置：最终报价，浮动价格确认后填入
     */
    public void setFinalQuotation(String finalQuotation) {
        this.finalQuotation = finalQuotation;
    }

    /**
     * 获取：最终报价，浮动价格确认后填入
     */
    public String getFinalQuotation() {
        return finalQuotation;
    }

    /**
     * 设置：计价基准
     */
    public void setPricingManner(String pricingManner) {
        this.pricingManner = pricingManner;
    }

    /**
     * 获取：计价基准
     */
    public String getPricingManner() {
        return pricingManner;
    }

    /**
     * 设置：计价日期
     */
    public void setContractualValueDate(Date contractualValueDate) {
        this.contractualValueDate = contractualValueDate;
    }

    /**
     * 获取：计价日期
     */
    public Date getContractualValueDate() {
        return contractualValueDate;
    }

    /**
     * 设置：升贴水
     */
    public void setPremiumsDiscounts(String premiumsDiscounts) {
        this.premiumsDiscounts = premiumsDiscounts;
    }

    /**
     * 获取：升贴水
     */
    public String getPremiumsDiscounts() {
        return premiumsDiscounts;
    }

    /**
     * 设置：支付日
     */
    public void setPayDay(String payDay) {
        this.payDay = payDay;
    }

    /**
     * 获取：支付日
     */
    public String getPayDay() {
        return payDay;
    }

    /**
     * 设置：买方备注
     */
    public void setBuyerRemark(String buyerRemark) {
        this.buyerRemark = buyerRemark;
    }

    /**
     * 获取：买方备注
     */
    public String getBuyerRemark() {
        return buyerRemark;
    }

    /**
     * 设置：油产地
     */
    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    /**
     * 获取：油产地
     */
    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    /**
     * 设置：油种类
     */
    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    /**
     * 获取：油种类
     */
    public String getOilType() {
        return oilType;
    }

    /**
     * 设置：api度
     */
    public void setApi(String api) {
        this.api = api;
    }

    /**
     * 获取：api度
     */
    public String getApi() {
        return api;
    }

    /**
     * 设置：含硫量%
     */
    public void setSulphurContent(String sulphurContent) {
        this.sulphurContent = sulphurContent;
    }

    /**
     * 获取：含硫量%
     */
    public String getSulphurContent() {
        return sulphurContent;
    }

    /**
     * 设置：酸值mgkoh/g
     */
    public void setAcidValue(String acidValue) {
        this.acidValue = acidValue;
    }

    /**
     * 获取：酸值mgkoh/g
     */
    public String getAcidValue() {
        return acidValue;
    }

    /**
     * 设置：残炭%
     */
    public void setCarbonResidue(String carbonResidue) {
        this.carbonResidue = carbonResidue;
    }

    /**
     * 获取：残炭%
     */
    public String getCarbonResidue() {
        return carbonResidue;
    }

    /**
     * 设置：镍ppm
     */
    public void setNickel(String nickel) {
        this.nickel = nickel;
    }

    /**
     * 获取：镍ppm
     */
    public String getNickel() {
        return nickel;
    }

    /**
     * 设置：钒ppm
     */
    public void setVanadium(String vanadium) {
        this.vanadium = vanadium;
    }

    /**
     * 获取：钒ppm
     */
    public String getVanadium() {
        return vanadium;
    }

    /**
     * 设置：>350摄氏度质量收缩率（%）
     */
    public void setShrink(String shrink) {
        this.shrink = shrink;
    }

    /**
     * 获取：>350摄氏度质量收缩率（%）
     */
    public String getShrink() {
        return shrink;
    }

    /**
     * 设置：订单状态0为初始化
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：订单状态0为初始化
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置：修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
}
