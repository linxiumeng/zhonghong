package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业认证信息表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_attest")
public class AttestEntity implements Serializable {
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
     * 企业中文名
     */
    private String cnName;
    /**
     * 英文企业名
     */
    private String enName;
    /**
     * 国家地区
     */
    private String area;
    /**
     * 企业法人
     */
    private String legalPerson;
    /**
     * 统一社会信用代码
     */
    private String unifiedSocialCreditCode;
    /**
     * 公司固定电话
     */
    private String telephone;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 联系电话
     */
    private String phoneNum;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 营业执照照片
     */
    private String licensePic1;
    /**
     * 企业注册授权委托书
     */
    private String licensePic2;
    /**
     * 法人身份证正面照片
     */
    private String idcardPic1;
    /**
     * 法人手持身份证照片
     */
    private String idcardPic2;
    /**
     * 认证状态0为初始化，1为认证通过，2为认证失败
     */
    private String status;
    /**
     * 认证类型 0-采购商 1-供应商
     */
    private Integer type;
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
     * 设置：企业中文名
     */
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    /**
     * 获取：企业中文名
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * 设置：英文企业名
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * 获取：英文企业名
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 设置：国家地区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取：国家地区
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置：企业法人
     */
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    /**
     * 获取：企业法人
     */
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     * 设置：统一社会信用代码
     */
    public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
    }

    /**
     * 获取：统一社会信用代码
     */
    public String getUnifiedSocialCreditCode() {
        return unifiedSocialCreditCode;
    }

    /**
     * 设置：公司固定电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取：公司固定电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置：联系人
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    /**
     * 获取：联系人
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * 设置：邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：联系电话
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * 获取：联系电话
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置：联系地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：联系地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：营业执照照片
     */
    public void setLicensePic1(String licensePic1) {
        this.licensePic1 = licensePic1;
    }

    /**
     * 获取：营业执照照片
     */
    public String getLicensePic1() {
        return licensePic1;
    }

    /**
     * 设置：企业注册授权委托书
     */
    public void setLicensePic2(String licensePic2) {
        this.licensePic2 = licensePic2;
    }

    /**
     * 获取：企业注册授权委托书
     */
    public String getLicensePic2() {
        return licensePic2;
    }

    /**
     * 设置：法人身份证正面照片
     */
    public void setIdcardPic1(String idcardPic1) {
        this.idcardPic1 = idcardPic1;
    }

    /**
     * 获取：法人身份证正面照片
     */
    public String getIdcardPic1() {
        return idcardPic1;
    }

    /**
     * 设置：法人手持身份证照片
     */
    public void setIdcardPic2(String idcardPic2) {
        this.idcardPic2 = idcardPic2;
    }

    /**
     * 获取：法人手持身份证照片
     */
    public String getIdcardPic2() {
        return idcardPic2;
    }

    /**
     * 设置：认证状态0为初始化，1为认证通过，2为认证失败
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取：认证状态0为初始化，1为认证通过，2为认证失败
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置：认证类型 0-采购商 1-供应商
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：认证类型 0-采购商 1-供应商
     */
    public Integer getType() {
        return type;
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
