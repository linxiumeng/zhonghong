package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (Attest) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-07 16:00:29
 */
@Data
@TableName("tb_attest")
public class Attest{
    
        /***/
        @TableId(type = IdType.INPUT)
        private Integer id;
        /**用户id*/
        private Long userId;
        /**用户id*/
        private Integer status;
        /**企业中文名*/
            private String cnName;
        /**英文企业名*/
            private String enName;
        /**国家地区*/
            private String area;
        /**企业法人*/
            private String legalPerson;
        /**统一社会信用代码*/
            private String unifiedSocialCreditCode;
        /**公司固定电话*/
            private String telephone;
        /**联系人*/
            private String linkman;
        /**邮箱*/
            private String email;
        /**联系电话*/
            private String phoneNum;
        /**联系地址*/
            private String address;
        /**营业执照照片*/
            private String licensePic1;
        /**企业注册授权委托书*/
            private String licensePic2;
        /**法人身份证正面照片*/
            private String idcardPic1;
        /**法人手持身份证照片*/
            private String idcardPic2;

    /**
     * 申请类型 0-采购商 1-供应商
     */
    private Integer type;
}