package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值记录表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_account_recharge")
public class AccountRechargeEntity implements Serializable {
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
     * 充值金额
     */
    private BigDecimal account;
    /**
     * 充值类型0是充值，1是提现
     */
    private Integer type;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 审核人
     */
    private String verifier;
    /**
     * 审核凭据
     */
    private String proof;
    /**
     * 验证时间
     */
    private Date verifiyDate;
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
     * 设置：充值金额
     */
    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    /**
     * 获取：充值金额
     */
    public BigDecimal getAccount() {
        return account;
    }

    /**
     * 设置：充值类型0是充值，1是提现
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：充值类型0是充值，1是提现
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态
     */
    public Integer getStatus() {
        return status;
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
     * 设置：审核人
     */
    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    /**
     * 获取：审核人
     */
    public String getVerifier() {
        return verifier;
    }

    /**
     * 设置：审核凭据
     */
    public void setProof(String proof) {
        this.proof = proof;
    }

    /**
     * 获取：审核凭据
     */
    public String getProof() {
        return proof;
    }

    /**
     * 设置：验证时间
     */
    public void setVerifiyDate(Date verifiyDate) {
        this.verifiyDate = verifiyDate;
    }

    /**
     * 获取：验证时间
     */
    public Date getVerifiyDate() {
        return verifiyDate;
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
