package org.springblade.common.form;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * 充值记录表(AccountRecharge) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-18 11:51:09
 */
@Data
@TableName("tb_account_recharge")
@ApiModel("充值记录表")
public class AccountExtractForm {
    /**
     * 提现金额
     */
    @ApiModelProperty(value = "提现金额",example = "5000")
    @NotNull(message = "提现金额不能为空")
    @Min(message = "最小值为0",value = 0)
    @Max(message = "最大值为100000000",value = 100000000)
    private Double account;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名字",example = "林秀栋")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 开户银行
     */
    @ApiModelProperty(value = "开户银行",example = "林秀栋暴力银行")
    @NotBlank(message = "开户银行不能为空")
    private String bankName;

    /**
     * 银行卡号
     */
    @ApiModelProperty(value = "银行卡号",example = "11111111")
    @NotBlank(message = "银行卡号不能为空")
    private String cardNo;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "12138",example = "1111111")
    private String password;
}