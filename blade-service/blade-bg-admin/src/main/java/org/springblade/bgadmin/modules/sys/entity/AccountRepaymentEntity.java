package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 分期还款表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_account_repayment")
public class AccountRepaymentEntity implements Serializable {
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
     * 订单id
     */
    private Integer orderId;
    /**
     * 总金额
     */
    private BigDecimal totalAmount;
    /**
     * 未还金额
     */
    private BigDecimal waitAmount;
    /**
     * 已还金额
     */
    private BigDecimal paidAmount;
    /**
     * 当前期数
     */
    private Integer currentPeriod;
    /**
     * 总利息
     */
    private BigDecimal totalInterest;
    /**
     * 待付利息
     */
    private BigDecimal waitInterest;
    /**
     * 已支付利息
     */
    private BigDecimal paidInterest;
    /**
     * 总期数
     */
    private Integer periods;
    /**
     * 最近还款日期
     */
    private Date recentRepaymentDate;
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
     * 设置：订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置：总金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取：总金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置：未还金额
     */
    public void setWaitAmount(BigDecimal waitAmount) {
        this.waitAmount = waitAmount;
    }

    /**
     * 获取：未还金额
     */
    public BigDecimal getWaitAmount() {
        return waitAmount;
    }

    /**
     * 设置：已还金额
     */
    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    /**
     * 获取：已还金额
     */
    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    /**
     * 设置：当前期数
     */
    public void setCurrentPeriod(Integer currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    /**
     * 获取：当前期数
     */
    public Integer getCurrentPeriod() {
        return currentPeriod;
    }

    /**
     * 设置：总利息
     */
    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    /**
     * 获取：总利息
     */
    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    /**
     * 设置：待付利息
     */
    public void setWaitInterest(BigDecimal waitInterest) {
        this.waitInterest = waitInterest;
    }

    /**
     * 获取：待付利息
     */
    public BigDecimal getWaitInterest() {
        return waitInterest;
    }

    /**
     * 设置：已支付利息
     */
    public void setPaidInterest(BigDecimal paidInterest) {
        this.paidInterest = paidInterest;
    }

    /**
     * 获取：已支付利息
     */
    public BigDecimal getPaidInterest() {
        return paidInterest;
    }

    /**
     * 设置：总期数
     */
    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    /**
     * 获取：总期数
     */
    public Integer getPeriods() {
        return periods;
    }

    /**
     * 设置：最近还款日期
     */
    public void setRecentRepaymentDate(Date recentRepaymentDate) {
        this.recentRepaymentDate = recentRepaymentDate;
    }

    /**
     * 获取：最近还款日期
     */
    public Date getRecentRepaymentDate() {
        return recentRepaymentDate;
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
}
