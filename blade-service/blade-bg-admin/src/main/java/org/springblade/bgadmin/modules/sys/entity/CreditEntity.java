package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 授信表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_credit")
public class CreditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 借款申请表
     */
    private String loanApplication;
    /**
     * 财务报表
     */
    private String financialStatement;
    /**
     * 缴税证明
     */
    private String taxCertification;
    /**
     * 银行流水
     */
    private String accountStatement;
    /**
     * 授信额度证明
     */
    private String certificateOfCreditLine;
    /**
     * 其他证明
     */
    private String orther;

    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

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
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：借款申请表
     */
    public void setLoanApplication(String loanApplication) {
        this.loanApplication = loanApplication;
    }

    /**
     * 获取：借款申请表
     */
    public String getLoanApplication() {
        return loanApplication;
    }

    /**
     * 设置：财务报表
     */
    public void setFinancialStatement(String financialStatement) {
        this.financialStatement = financialStatement;
    }

    /**
     * 获取：财务报表
     */
    public String getFinancialStatement() {
        return financialStatement;
    }

    /**
     * 设置：缴税证明
     */
    public void setTaxCertification(String taxCertification) {
        this.taxCertification = taxCertification;
    }

    /**
     * 获取：缴税证明
     */
    public String getTaxCertification() {
        return taxCertification;
    }

    /**
     * 设置：银行流水
     */
    public void setAccountStatement(String accountStatement) {
        this.accountStatement = accountStatement;
    }

    /**
     * 获取：银行流水
     */
    public String getAccountStatement() {
        return accountStatement;
    }

    /**
     * 设置：授信额度证明
     */
    public void setCertificateOfCreditLine(String certificateOfCreditLine) {
        this.certificateOfCreditLine = certificateOfCreditLine;
    }

    /**
     * 获取：授信额度证明
     */
    public String getCertificateOfCreditLine() {
        return certificateOfCreditLine;
    }

    /**
     * 设置：其他证明
     */
    public void setOrther(String orther) {
        this.orther = orther;
    }

    /**
     * 获取：其他证明
     */
    public String getOrther() {
        return orther;
    }

}
