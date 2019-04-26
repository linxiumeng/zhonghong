package org.springblade.bgadmin.modules.sys.form.mybatis;

import lombok.Data;

import java.util.Date;

/**
 * @author hanin
 * 后台分期表单条件查询
 */
@Data
public class AccountRepaymentStepDaoCondition {


    private String keywords;

    private Integer status;

    private Date startDate;

    private Date endDate;

}
