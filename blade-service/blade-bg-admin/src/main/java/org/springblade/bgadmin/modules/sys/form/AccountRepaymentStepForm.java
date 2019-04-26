package org.springblade.bgadmin.modules.sys.form;

import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 分期表单
 */
@Data
public class AccountRepaymentStepForm {


    /**
     * 当前页
     */
    private int page;

    /**
     * 每页大小
     */
    private int size;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 分期状态
     */
    private Integer status;



}

