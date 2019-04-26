package org.springblade.bgadmin.modules.sys.form;

import lombok.Data;

import java.util.Date;

@Data
public class AccountWithdrawForm {

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
     * 状态
     */
    private Integer status;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每夜条数
     */
    private int size;

    private Integer id;

}
