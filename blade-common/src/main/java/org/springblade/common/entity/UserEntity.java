package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("用户实体类")
public class UserEntity implements Serializable {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id",name="userId",example = "1")
    @TableId(type = IdType.INPUT)
    private Long userId;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号",name="mobile",example = "15723695412")
    private String mobile;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码",name="password",example = "1124121")
    @JsonIgnore
    private String password;
    /**
     * 统一社会编码
     */
    @ApiModelProperty(value = "统一社会编码",name="unifiedSocialCode",example = "12157641323578645")
    private String unifiedSocialCode;
    /**
     * 法人
     */
    @ApiModelProperty(value = "法人",name="legalPerson",example = "帅泽泽")
    private String legalPerson;
    /**
     * 身份证正面
     */
    @ApiModelProperty(value = "身份证正面",name="card1",example = "我是图片")
    private String card1;
    /**
     * 身份证反面
     */
    @ApiModelProperty(value = "身份证反面",name="card2",example = "图片+1")
    private String card2;
    /**
     * 营业执照
     */
    @ApiModelProperty(value = "营业执照",name="businessLicence",example = "图片+2")
    private String businessLicence;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人",name="contacts",example = "马云")
    private String contacts;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话",name="contactNumber",example = "15698745214")
    private String contactNumber;
    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址",name="contactAddress",example = "浙江杭州滨江")
    private String contactAddress;
    /**
     * 授信状态：初始化-0，授信中-1，拒绝-2，接收-3
     */
    @ApiModelProperty(value = "授信状态：初始化-0，授信中-1，拒绝-2，接收-3",name="creditStatus",example = "2")
    private Integer creditStatus;
    /**
     * 采购商账号状态 账号状态，0未企业认证;1为企业认证中;2为企业认证失败；3为企业认证通过，授信未提交；4为授信审核中；5为授信失败；6为授信成功
     */
    @ApiModelProperty(value = "采购商账号状态 账号状态，0未企业认证;1为企业认证中;2为企业认证失败；3为企业认证通过，授信未提交；4为授信审核中；5为授信失败；6为授信成功",name="status",example = "6")
    private Integer status;
    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间",name="registerDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date registerDate;
    /**
     * 用户类型：1：采购商，2：供应商
     */
    @ApiModelProperty(value = "用户类型：1：采购商，2：供应商",name="type",example = "1")
    private Integer type;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称",name="companyName",example = "******公司")
    private String companyName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="createDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",name="updateDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱",name="mail",example = "*****@163.com")
    private String mail;

    /**
     * 供应商认证状态 账号状态0为初始化;1为认证中;3为认证通过;2为认证失败
     */
    @ApiModelProperty(value = "供应商认证状态 账号状态0为初始化;1为认证中;3为认证通过;2为认证失败",name="providerStatus",example = "2")
    private Integer providerStatus;


    /**
     * 登录状态
     */
    @ApiModelProperty(value = "登录状态",name="loginStatus",example = "1")
    private Integer loginStatus;

}