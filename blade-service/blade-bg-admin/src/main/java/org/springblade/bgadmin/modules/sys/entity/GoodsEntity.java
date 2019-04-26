package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
@TableName("tb_goods")
public class GoodsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 商品公司
     */
    private String goodsCompany;
    /**
     * 产品类型
     */
    private String goodsType;
    /**
     * 产品名字
     */
    private String goodsName;
    /**
     * 产品详情
     */
    private String goodsDesc;
    /**
     * 产品单价
     */
    private BigDecimal goodsPrice;
    /**
     * 产品单位
     */
    private String goodsUnit;
    /**
     * 产品状态0为未上架,1为已上架,2为已下架
     */
    private Integer goodsStatus;
    /**
     * 库存数量
     */
    private String goodsStock;
    /**
     * 图片地址
     */
    private String pic;
    /**
     * 审核状态0为默认状态,1为审核通过,2为审核失败
     */
    private Integer auditStatus;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date updateDate;
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
     * 发货港/提货点
     */
    private String portOfDispatch;
    /**
     * 装载日
     */
    private String loadingDate;
    /**
     * 支付日
     */
    private String payDay;
    /**
     * 其他说明
     */
    private String otherDescription;
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
     * 原油指标文件
     */
    private String filePoint;
    /**
     * 升贴水
     */
    private String premiumsDiscounts;
    /**
     *
     */
    private Date creatTime;
    /**
     *
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
     * 设置：用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置：商品公司
     */
    public void setGoodsCompany(String goodsCompany) {
        this.goodsCompany = goodsCompany;
    }

    /**
     * 获取：商品公司
     */
    public String getGoodsCompany() {
        return goodsCompany;
    }

    /**
     * 设置：产品类型
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 获取：产品类型
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * 设置：产品名字
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取：产品名字
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置：产品详情
     */
    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    /**
     * 获取：产品详情
     */
    public String getGoodsDesc() {
        return goodsDesc;
    }

    /**
     * 设置：产品单价
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取：产品单价
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置：产品单位
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    /**
     * 获取：产品单位
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }

    /**
     * 设置：产品状态0为未上架,1为已上架,2为已下架
     */
    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    /**
     * 获取：产品状态0为未上架,1为已上架,2为已下架
     */
    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    /**
     * 设置：库存数量
     */
    public void setGoodsStock(String goodsStock) {
        this.goodsStock = goodsStock;
    }

    /**
     * 获取：库存数量
     */
    public String getGoodsStock() {
        return goodsStock;
    }

    /**
     * 设置：图片地址
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 获取：图片地址
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置：审核状态0为默认状态,1为审核通过,2为审核失败
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取：审核状态0为默认状态,1为审核通过,2为审核失败
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置：备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取：备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置：修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
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
     * 设置：发货港/提货点
     */
    public void setPortOfDispatch(String portOfDispatch) {
        this.portOfDispatch = portOfDispatch;
    }

    /**
     * 获取：发货港/提货点
     */
    public String getPortOfDispatch() {
        return portOfDispatch;
    }

    /**
     * 设置：装载日
     */
    public void setLoadingDate(String loadingDate) {
        this.loadingDate = loadingDate;
    }

    /**
     * 获取：装载日
     */
    public String getLoadingDate() {
        return loadingDate;
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
     * 设置：其他说明
     */
    public void setOtherDescription(String otherDescription) {
        this.otherDescription = otherDescription;
    }

    /**
     * 获取：其他说明
     */
    public String getOtherDescription() {
        return otherDescription;
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
     * 设置：原油指标文件
     */
    public void setFilePoint(String filePoint) {
        this.filePoint = filePoint;
    }

    /**
     * 获取：原油指标文件
     */
    public String getFilePoint() {
        return filePoint;
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
     * 设置：
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 获取：
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置：
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：
     */
    public Date getUpdateTime() {
        return updateTime;
    }
}
