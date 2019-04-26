package org.springblade.bgadmin.modules.sys.form.mybatis;

import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 提货单mybatis条件查询
 */
@Data
public class LoadBillCondition {


    private String keywords;

    private Date startTime;

    private Date endTime;

}
