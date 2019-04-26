package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 余额表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_account")
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *
     */
    private Integer userId;
    /**
     * 可用余额
     */
    private BigDecimal account;
    /**
     * 账户总资产
     */
    private BigDecimal total;
    /**
     * 冻结金额
     */
    private BigDecimal freezeAmount;
    /**
     * 保证金
     */
    private BigDecimal cashFund;
    /**
     * 审批额度
     */
    private String creditLimit;
    /**
     * 单笔可用额度
     */
    private String creditUnit;
    /**
     * 最高可用额度
     */
    private String creditHigh;

    /**
     * 授信评级
     */
    private String creditLevel;

    /**
     *
     */
    private Date createDate;
    /**
     *
     */
    private Date updateDate;

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
     * 设置：
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：可用余额
     */
    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    /**
     * 获取：可用余额
     */
    public BigDecimal getAccount() {
        return account;
    }

    /**
     * 设置：账户总资产
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * 获取：账户总资产
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 设置：冻结金额
     */
    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    /**
     * 获取：冻结金额
     */
    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    /**
     * 设置：保证金
     */
    public void setCashFund(BigDecimal cashFund) {
        this.cashFund = cashFund;
    }

    /**
     * 获取：保证金
     */
    public BigDecimal getCashFund() {
        return cashFund;
    }

    /**
     * 设置：审批额度
     */
    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     * 获取：审批额度
     */
    public String getCreditLimit() {
        return creditLimit;
    }

    /**
     * 设置：单笔可用额度
     */
    public void setCreditUnit(String creditUnit) {
        this.creditUnit = creditUnit;
    }

    /**
     * 获取：单笔可用额度
     */
    public String getCreditUnit() {
        return creditUnit;
    }

    /**
     * 设置：最高可用额度
     */
    public void setCreditHigh(String creditHigh) {
        this.creditHigh = creditHigh;
    }

    /**
     * 获取：最高可用额度
     */
    public String getCreditHigh() {
        return creditHigh;
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

    public String getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel;
    }
}
