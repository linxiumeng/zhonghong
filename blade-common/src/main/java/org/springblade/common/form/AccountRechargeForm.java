package org.springblade.common.form;


import lombok.Data;

/**
 * 充值记录表(AccountRecharge) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-18 11:51:09
 */
@Data
public class AccountRechargeForm {

        private Long id;
        /***/
        private Long userId;
        /**充值金额*/
        private Double account;
        /**备注*/
        private String remark;
        /**审核人*/
        private String verifier;
        /**审核凭据*/
        private String proof;
}