package org.springblade.common.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springblade.common.entity.PurchaseOrders;

import java.util.Date;

@Data
public class PurchaseOrderForm extends PurchaseOrders {
    private String userId;


    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 收款状态
     */
    private Integer gatheringStatus;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endDate;

    /**
     * 关键字
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private String keywords;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页条数
     */
    private int size;

}
