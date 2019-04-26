package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 需求表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
@TableName("tb_demand")
public class DemandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 创建用户id
     */
    private String creatUserid;
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
    private String deadline;
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
     * 需求单状态0为初始化，1为招标中，2为暂停招标，3为停止招标
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
     * 商品备注
     */
    private String fRemark;

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
     * 设置：创建用户id
     */
    public void setCreatUserid(String creatUserid) {
        this.creatUserid = creatUserid;
    }

    /**
     * 获取：创建用户id
     */
    public String getCreatUserid() {
        return creatUserid;
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
     * 设置：采购数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取：采购数量
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
     * 设置：截止时间
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * 获取：截止时间
     */
    public String getDeadline() {
        return deadline;
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
     * 设置：需求单状态0为初始化，1为招标中，2为暂停招标，3为停止招标
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：需求单状态0为初始化，1为招标中，2为暂停招标，3为停止招标
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

    /**
     * 设置：商品备注
     */
    public void setFRemark(String fRemark) {
        this.fRemark = fRemark;
    }

    /**
     * 获取：商品备注
     */
    public String getFRemark() {
        return fRemark;
    }
}
