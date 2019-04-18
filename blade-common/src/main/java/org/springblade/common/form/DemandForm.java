package org.springblade.common.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springblade.common.entity.Demand;

import java.util.Date;

/**
 * 需求提交表单类
 * @author hanbin
 */
@Data
public class DemandForm extends Demand {

    /**
     * 需求单状态
     */
    private Integer demandStatus;

    /**
     * 对应的订单状态
     */
    private Integer orderStatus;

    /**
     * 开始发布日期 todo 确认
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startPublishDate;

    /**
     * 结束发布日期 todo 确认
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endPublishDate;

    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startDate;

    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endDate;

    /**
     * 关键字 todo 确认需不需要
     */
    private String keywords;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页大小
     */
    private int size;

}
