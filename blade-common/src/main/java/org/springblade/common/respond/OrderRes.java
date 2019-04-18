package org.springblade.common.respond;

import lombok.Data;
import org.springblade.common.entity.Order;

/**
 * 订单表(Order) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-14 10:51:48
 */
@Data
public class OrderRes extends Order {
        /**用户余额*/
        private Double account;
}