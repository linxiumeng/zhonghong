package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * (Attest) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-07 16:00:29
 */
@Data
@TableName("tb_attest")
@ApiModel("企业证明实体类")
public class Attest {

    /***/
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "主键id",name="id",example = "1")
    private Integer id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",name="userId",example = "2")
    private Long userId;
    /**
     * 认证状态 0为初始化，1为认证通过，2为认证失败
     */
    @ApiModelProperty(value = "认证状态 0为初始化，1为认证通过，2为认证失败",name="status",example = "1")
    private Integer status;
    /**
     * 企业中文名
     */
    @ApiModelProperty(value = "企业中文名",name="cnName",example = "公司")
    private String cnName;
    /**
     * 英文企业名
     */
    @ApiModelProperty(value = "英文企业名",name="enName",example = "GONGSI")
    private String enName;
    /**
     * 国家地区
     */
    @ApiModelProperty(value = "国家地区",name="area",example = "杭州")
    private String area;
    /**
     * 企业法人
     */
    @ApiModelProperty(value = "企业法人",name="legalPerson",example = "帅泽泽")
    private String legalPerson;
    /**
     * 统一社会信用代码
     */
    @ApiModelProperty(value = "统一社会信用代码",name="unifiedSocialCreditCode",example = "ZZNB661")
    private String unifiedSocialCreditCode;
    /**
     * 公司固定电话
     */
    @ApiModelProperty(value = "公司固定电话",name="telephone",example = "11100")
    private String telephone;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人",name="linkman",example = "帅泽泽")
    private String linkman;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱",name="email",example = "5249@163.com")
    private String email;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话",name="phoneNum",example = "123456")
    private String phoneNum;
    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址",name="address",example = "M78星云")
    private String address;
    /**
     * 营业执照照片
     */
    @ApiModelProperty(value = "营业执照照片",name="licensePic1",example = "呐，你看")
    private String licensePic1;
    /**
     * 企业注册授权委托书
     */
    @ApiModelProperty(value = "企业注册授权委托书",name="licensePic2",example = "不知道")
    private String licensePic2;
    /**
     * 法人身份证正面照片
     */
    @ApiModelProperty(value = "法人身份证正面照片",name="idcardPic1",example = "假装有照片")
    private String idcardPic1;
    /**
     * 法人手持身份证照片
     */
    @ApiModelProperty(value = "法人手持身份证照片",name="idcardPic2",example = "假装有照片")
    private String idcardPic2;

    /**
     * 申请类型 0-采购商 1-供应商
     */
    @ApiModelProperty(value = "申请类型 0-采购商 1-供应商",name="type",example = "1")
    private Integer type;
}