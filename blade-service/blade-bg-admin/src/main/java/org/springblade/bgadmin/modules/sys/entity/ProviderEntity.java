package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_provider")
public class ProviderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */
                @TableId
            private Integer userId;
            /**
         * 注册手机号
         */
            private String mobile;
            /**
         * 账号
         */
            private String userName;
            /**
         * 密码
         */
            private String password;
            /**
         * 统一社会编码
         */
            private String unifiedSocialCode;
            /**
         * 法人
         */
            private String legalPerson;
            /**
         * 身份证正面
         */
            private String card1;
            /**
         * 身份证反面
         */
            private String card2;
            /**
         * 营业执照
         */
            private String businessLicence;
            /**
         * 联系人
         */
            private String contacts;
            /**
         * 联系电话
         */
            private String contactNumber;
            /**
         * 联系地址
         */
            private String contactAddress;
            /**
         * 授信状态
         */
            private Integer creditStatus;
            /**
         * 账号状态0为初始化;1为认证中;2为认证通过;3为认证失败
         */
            private Integer status;
            /**
         * 注册时间
         */
            private Date registerDate;
            /**
         * 用户类型：1：采购商，2：供应商
         */
            private Integer type;
            /**
         * 企业名称
         */
            private String companyName;
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
         * 设置：注册手机号
         */
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        /**
         * 获取：注册手机号
         */
        public String getMobile() {
            return mobile;
        }
            /**
         * 设置：账号
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        /**
         * 获取：账号
         */
        public String getUserName() {
            return userName;
        }
            /**
         * 设置：密码
         */
        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * 获取：密码
         */
        public String getPassword() {
            return password;
        }
            /**
         * 设置：统一社会编码
         */
        public void setUnifiedSocialCode(String unifiedSocialCode) {
            this.unifiedSocialCode = unifiedSocialCode;
        }

        /**
         * 获取：统一社会编码
         */
        public String getUnifiedSocialCode() {
            return unifiedSocialCode;
        }
            /**
         * 设置：法人
         */
        public void setLegalPerson(String legalPerson) {
            this.legalPerson = legalPerson;
        }

        /**
         * 获取：法人
         */
        public String getLegalPerson() {
            return legalPerson;
        }
            /**
         * 设置：身份证正面
         */
        public void setCard1(String card1) {
            this.card1 = card1;
        }

        /**
         * 获取：身份证正面
         */
        public String getCard1() {
            return card1;
        }
            /**
         * 设置：身份证反面
         */
        public void setCard2(String card2) {
            this.card2 = card2;
        }

        /**
         * 获取：身份证反面
         */
        public String getCard2() {
            return card2;
        }
            /**
         * 设置：营业执照
         */
        public void setBusinessLicence(String businessLicence) {
            this.businessLicence = businessLicence;
        }

        /**
         * 获取：营业执照
         */
        public String getBusinessLicence() {
            return businessLicence;
        }
            /**
         * 设置：联系人
         */
        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        /**
         * 获取：联系人
         */
        public String getContacts() {
            return contacts;
        }
            /**
         * 设置：联系电话
         */
        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        /**
         * 获取：联系电话
         */
        public String getContactNumber() {
            return contactNumber;
        }
            /**
         * 设置：联系地址
         */
        public void setContactAddress(String contactAddress) {
            this.contactAddress = contactAddress;
        }

        /**
         * 获取：联系地址
         */
        public String getContactAddress() {
            return contactAddress;
        }
            /**
         * 设置：授信状态
         */
        public void setCreditStatus(Integer creditStatus) {
            this.creditStatus = creditStatus;
        }

        /**
         * 获取：授信状态
         */
        public Integer getCreditStatus() {
            return creditStatus;
        }
            /**
         * 设置：账号状态0为初始化;1为认证中;2为认证通过;3为认证失败
         */
        public void setStatus(Integer status) {
            this.status = status;
        }

        /**
         * 获取：账号状态0为初始化;1为认证中;2为认证通过;3为认证失败
         */
        public Integer getStatus() {
            return status;
        }
            /**
         * 设置：注册时间
         */
        public void setRegisterDate(Date registerDate) {
            this.registerDate = registerDate;
        }

        /**
         * 获取：注册时间
         */
        public Date getRegisterDate() {
            return registerDate;
        }
            /**
         * 设置：用户类型：1：采购商，2：供应商
         */
        public void setType(Integer type) {
            this.type = type;
        }

        /**
         * 获取：用户类型：1：采购商，2：供应商
         */
        public Integer getType() {
            return type;
        }
            /**
         * 设置：企业名称
         */
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        /**
         * 获取：企业名称
         */
        public String getCompanyName() {
            return companyName;
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
