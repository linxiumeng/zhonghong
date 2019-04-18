package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 授信表(Credit) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-07 16:15:37
 */
@Data
@TableName("tb_credit")
public class Credit{
    
        /***/
        @TableId(type = IdType.INPUT)
        private Integer id;
        /**用户id*/
            private Long userId;
        /**借款申请表*/
            private String loanApplication;
        /**财务报表*/
            private String financialStatement;
        /**缴税证明*/
            private String taxCertification;
        /**银行流水*/
            private String accountStatement;
        /**授信额度证明*/
            private String certificateOfCreditLine;
        /**其他证明*/
            private String orther;
}