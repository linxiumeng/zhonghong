package org.springblade.bgadmin.modules.sys.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 基本表单
 */
@Data
public class BaseForm {


    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每夜条数
     */
    private int size;

    /**
     * 关键字
     */
    private String keywords;

}
