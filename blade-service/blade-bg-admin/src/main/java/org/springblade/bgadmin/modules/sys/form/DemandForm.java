package org.springblade.bgadmin.modules.sys.form;

import lombok.Data;
import org.springblade.bgadmin.modules.sys.enums.DemandStatusEnum;
import org.springblade.bgadmin.modules.sys.enums.OrdersFormEnum;

import java.util.Date;

/**
 * @author hanbin
 * 用户表单
 */
@Data
public class DemandForm extends BaseForm{

    /**
     * 订单状态
     */
    private OrdersFormEnum ordersFormStatus;

    /**
     * 需求单状态
     */
    private DemandStatusEnum demandStatus;

    /**
     * 需求单开始的时间
     */
    private Date demandStartDate;

    /**
     * 需求单结束的时间
     */
    private Date demandEndDate;

    /**
     * 订单的开始时间
     */
    private Date demandDeadLineStartDate;

    /**
     * 订单的结束时间
     */
    private Date demandDeadLineEndDate;


}

