package org.springblade.common.entity;

import lombok.Data;

/**
 * @author hanbin
 * 继承了需求类，用于连表查询返回数据
 */
@Data
public class DemandDetail extends Demand {

    /**
     * 账户
     */
    private String mobile;

    /**
     * 公司名字
     */
    private String companyName;

    /**
     * 报价次数
     */
    private Integer quoteTimes;

    /**
     * 订单状态
     */
    private Integer orderStatus;


}
