package org.springblade.bgadmin.modules.sys.form;

import lombok.Data;
import org.springblade.bgadmin.modules.sys.enums.AccountRepaymentStatusEnum;

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

