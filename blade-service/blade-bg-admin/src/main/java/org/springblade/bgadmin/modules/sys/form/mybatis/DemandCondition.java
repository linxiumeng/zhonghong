package org.springblade.bgadmin.modules.sys.form.mybatis;

import io.finepetro.modules.sys.enums.DemandStatusEnum;
import io.finepetro.modules.sys.enums.OrdersFormEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 用户mybatis表单
 */
@Data
public class DemandCondition {

    /**
     * 订单状态 表单的状态
     */
    private OrdersFormEnum ordersFormStatus;

    /**
     * 需求单状态
     */
    private DemandStatusEnum demandStatus;

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


    private Date demandStartDate;

    private Date demandEndDate;

    private Date demandDeadLineStartDate;

    private Date demandDeadLineEndDate;


}

