package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 分期还款详情表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_account_repayment_step")
public class AccountRepaymentStepEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *
     */
    private Integer repaymentId;
    /**
     * 金额
     */
    private BigDecimal account;
    /**
     * 利息
     */
    private BigDecimal interest;
    /**
     * 已还金额
     */
    private BigDecimal paidAccount;
    /**
     * 已还利息
     */
    private BigDecimal paidInterest;
    /**
     * 状态 0:未还款 1:已还款 2:已逾期
     */
    private Integer status;
    /**
     * 期数
     */
    private Integer preiod;
    /**
     * 还款日期
     */
    private Date preiodDate;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
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
    public void setRepaymentId(Integer repaymentId) {
        this.repaymentId = repaymentId;
    }

    /**
     * 获取：
     */
    public Integer getRepaymentId() {
        return repaymentId;
    }

    /**
     * 设置：金额
     */
    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    /**
     * 获取：金额
     */
    public BigDecimal getAccount() {
        return account;
    }

    /**
     * 设置：利息
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * 获取：利息
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * 设置：已还金额
     */
    public void setPaidAccount(BigDecimal paidAccount) {
        this.paidAccount = paidAccount;
    }

    /**
     * 获取：已还金额
     */
    public BigDecimal getPaidAccount() {
        return paidAccount;
    }

    /**
     * 设置：已还利息
     */
    public void setPaidInterest(BigDecimal paidInterest) {
        this.paidInterest = paidInterest;
    }

    /**
     * 获取：已还利息
     */
    public BigDecimal getPaidInterest() {
        return paidInterest;
    }

    /**
     * 设置：状态 0:未还款 1:已还款 2:已逾期
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态 0:未还款 1:已还款 2:已逾期
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：期数
     */
    public void setPreiod(Integer preiod) {
        this.preiod = preiod;
    }

    /**
     * 获取：期数
     */
    public Integer getPreiod() {
        return preiod;
    }

    /**
     * 设置：还款日期
     */
    public void setPreiodDate(Date preiodDate) {
        this.preiodDate = preiodDate;
    }

    /**
     * 获取：还款日期
     */
    public Date getPreiodDate() {
        return preiodDate;
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
     * 设置：更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取：更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }
}
