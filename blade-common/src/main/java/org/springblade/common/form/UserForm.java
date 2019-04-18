package org.springblade.common.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springblade.common.entity.UserEntity;

import java.util.Date;

/**
 * @author hanbin
 * 拓展UserEntityz增加分页功能
 */
@Data
public class UserForm extends UserEntity {

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startDate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endDate;

    /**
     * 用于查询的关键字
     */
    private String keywords;

    /**
     * 状态：用于分别身份类型
     */
    private Integer status;

    /**
     * 授信状态
     */
    private Integer creditStatus;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页条数
     */
    private int size;



}
