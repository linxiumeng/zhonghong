package org.springblade.bgadmin.modules.sys.form;

import io.finepetro.modules.sys.enums.AccountRepaymentStatusEnum;
import lombok.Data;

/**
 * @author hanbin
 * 分期表单
 */
@Data
public class AccountRepaymentForm extends BaseForm{


    /**
     * 分期状态
     */
    private AccountRepaymentStatusEnum status;



}

