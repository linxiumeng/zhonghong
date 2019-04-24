package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(UserEntity) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-13 14:02:00
 */
@Data
@TableName("tb_fuser")
public class UserEntity implements Serializable {

    /***/
    @TableId(type = IdType.INPUT)
    private Long userId;
    /**
     * 账号
     */
    private String mobile;
    /**
     * 密码
     */
    @JsonIgnore
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
     * 授信状态：初始化-0，授信中-1，拒绝-2，接收-3
     */
    private Integer creditStatus;
    /**
     * 采购商账号状态 账号状态，0未企业认证;1为企业认证中;2为企业认证失败；3为企业认证通过，授信未提交；4为授信审核中；5为授信失败；6为授信成功
     */
    private Integer status;
    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date registerDate;
    /**
     * 用户类型：1：采购商，2：供应商
     */
    private Integer type;
    /**
     * 企业名称
     */
    private String companyName;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 供应商认证状态 账号状态0为初始化;1为认证中;3为认证通过;2为认证失败
     */
    private Integer providerStatus;


    /**
     * 登录状态
     */
    private Integer loginStatus;

}