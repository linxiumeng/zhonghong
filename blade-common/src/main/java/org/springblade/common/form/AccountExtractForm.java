package org.springblade.common.form;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 充值记录表(AccountRecharge) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-18 11:51:09
 */
@Data
@TableName("tb_account_recharge")
public class AccountExtractForm {
        /**提现金额*/
        private Double account;

        /**
         * 用户名
         */
        private String username;

        /**
         * 开户银行
         */
        private String bankName;

        /**
         * 银行卡号
         */
        private String cardNo;

        /**
         * 密码
         */
        @NotBlank
        private String password;
}