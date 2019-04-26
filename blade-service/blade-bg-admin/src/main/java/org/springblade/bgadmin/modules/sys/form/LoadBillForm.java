package org.springblade.bgadmin.modules.sys.form;

import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 提货单表单
 */
@Data
public class LoadBillForm {

    private String keywords;

    private Date startTime;

    private Date endTime;

    private String path;

    private Integer orderId;

    private int page;

    private int size;

}

