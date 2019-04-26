package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 报价单表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
@TableName("tb_quotation")
public class QuotationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 关联需求单id
     */
    private Integer demandId;
    /**
     * 报价用户id
     */
    private Integer userId;
    /**
     * 单价，0为浮动价
     */
    private BigDecimal price;
    /**
     * 产品类型
     */
    private String fType;
    /**
     * 产品名称
     */
    private String fName;
    /**
     * 产品单位
     */
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
    /**
     *
     */
    private Date createDate;
    /**
     *
     */
    private Date updateDate;
    /**
     * 提貨地址
     */
    private String shipAddress;
    /**
     * 裝在時間
     */
    private String loadDate;
    /**
     * 其他説明
     */
    private String otherDesc;

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
     * 设置：关联需求单id
     */
    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    /**
     * 获取：关联需求单id
     */
    public Integer getDemandId() {
        return demandId;
    }

    /**
     * 设置：报价用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：报价用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：单价，0为浮动价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：单价，0为浮动价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置：产品类型
     */
    public void setFType(String fType) {
        this.fType = fType;
    }

    /**
     * 获取：产品类型
     */
    public String getFType() {
        return fType;
    }

    /**
     * 设置：产品名称
     */
    public void setFName(String fName) {
        this.fName = fName;
    }

    /**
     * 获取：产品名称
     */
    public String getFName() {
        return fName;
    }

    /**
     * 设置：产品单位
     */
    public void setFUnit(String fUnit) {
        this.fUnit = fUnit;
    }

    /**
     * 获取：产品单位
     */
    public String getFUnit() {
        return fUnit;
    }

    /**
     * 设置：交易数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取：交易数量
     */
    public Integer getNum() {
        return num;
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
    public void setContractualValueDate(String contractualValueDate) {
        this.contractualValueDate = contractualValueDate;
    }

    /**
     * 获取：计价日期
     */
    public String getContractualValueDate() {
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
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
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
     * 设置：报价单状态0为初始
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取：报价单状态0为初始
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置：
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取：
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置：
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取：
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置：提貨地址
     */
    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    /**
     * 获取：提貨地址
     */
    public String getShipAddress() {
        return shipAddress;
    }

    /**
     * 设置：裝在時間
     */
    public void setLoadDate(String loadDate) {
        this.loadDate = loadDate;
    }

    /**
     * 获取：裝在時間
     */
    public String getLoadDate() {
        return loadDate;
    }

    /**
     * 设置：其他説明
     */
    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }

    /**
     * 获取：其他説明
     */
    public String getOtherDesc() {
        return otherDesc;
    }
}
