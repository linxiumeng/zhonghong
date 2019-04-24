package org.springblade.common.respond;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 用户传输类
 */
@Data
public class UserDto {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 账号
     */
    private String mobile;

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
     * 账号状态
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

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 供应商认证状态
     */
    private Integer providerStatus;
}
