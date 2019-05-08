package org.springblade.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.entity.Attest;
import org.springblade.common.validation.group.InsertGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author hanbin
 * 认证表单
 */
@Data
@ApiModel("认证表单")
public class AttestForm extends Attest {


    /**
     * id
     */
    @ApiModelProperty(value = "id",example = "1")
    @Null(groups = {InsertGroup.class})
    private Integer id;
    /**
     * 用户id
     */
    @NotNull(groups = {InsertGroup.class})
    @ApiModelProperty(value = "用户id",example = "1")
    private Long userId;
    /**
     * 状态 状态 认证状态0为初始化，1为认证通过，2为认证失败
     */
    @ApiModelProperty(value = "状态，暂时看到好像没有用到？？？",example = "1")
    private Integer status;
    /**
     * 企业中文名
     */
    @ApiModelProperty(value = "中文企业名字",example = "林秀栋霸气企业")
    @NotNull(groups = {InsertGroup.class})
    private String cnName;
    /**
     * 英文企业名
     */
    @ApiModelProperty(value = "企业英文名字",example = "linxiudong's company")
    @NotNull(groups = {InsertGroup.class})
    private String enName;
    /**
     * 国家地区
     */
    @ApiModelProperty(value = "地区",example = "浙江温州")
    @NotNull(groups = {InsertGroup.class})
    private String area;
    /**
     * 企业法人
     */
    @ApiModelProperty(value = "法人",example = "林秀栋")
    @NotNull(groups = {InsertGroup.class})
    private String legalPerson;
    /**
     * 统一社会信用代码
     */
    @ApiModelProperty(value = "统一社会信用代码",example = "111111")
    @NotNull(groups = {InsertGroup.class})
    private String unifiedSocialCreditCode;
    /**
     * 公司固定电话
     */
    @ApiModelProperty(value = "公司固定电话",example = "1111111")
    @NotNull(groups = {InsertGroup.class})
    private String telephone;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人",example = "林秀栋")
    @NotNull(groups = {InsertGroup.class})
    private String linkman;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱",example = "lxd@nuoee.com")
    @NotNull(groups = {InsertGroup.class})
    @Email(groups = {InsertGroup.class})
    private String email;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话",example = "110")
    @NotNull(groups = {InsertGroup.class})
    private String phoneNum;
    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址",example = "浙江温州")
    @NotNull(groups = {InsertGroup.class})
    private String address;
    /**
     * 营业执照照片
     */
    @NotNull(groups = {InsertGroup.class})
    @ApiModelProperty(value = "企业营业执照",example = "1111111")
    private String licensePic1;
    /**
     * 企业注册授权委托书
     */
    @ApiModelProperty(value = "企业注册授权委托书",example = "22222222")
    @NotNull(groups = {InsertGroup.class})
    private String licensePic2;
    /**
     * 法人身份证正面照片
     */
    @ApiModelProperty(value = "法人身份证正面照片",example = "1221212121212")
    @NotNull(groups = {InsertGroup.class})
    private String idcardPic1;
    /**
     * 法人手持身份证照片
     */
    @ApiModelProperty(value = "法人手持身份证照片",example = "12122222221")
    @NotNull(groups = {InsertGroup.class})
    private String idcardPic2;

    /**
     * 申请类型 0-采购商 1-供应商
     */
    private Integer type;
}
