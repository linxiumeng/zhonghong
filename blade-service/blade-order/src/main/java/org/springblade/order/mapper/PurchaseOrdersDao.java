package org.springblade.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springblade.common.entity.Order;
import org.springblade.common.entity.PurchaseOrders;
import org.springblade.common.entity.Quotation;
import org.springblade.common.respond.ProviderPurchaseOrderStatisticsResp;

import java.util.List;

/**
 * (PurchaseOrders)表数据库访问层
 *
 * @author linxiumeng
 * @since 2019-03-07 17:27:06
 */
 @Mapper
public interface PurchaseOrdersDao extends BaseMapper<PurchaseOrders> {


     @Select("select \n" +
             "SUM(case tb.status when 0 then 1 else 0 end) as `waitConfirmation`,\n" +
             "SUM(case tb.status when 2 then 1 else 0 end) as `makePriceWaiting`,\n" +
             "SUM(case tb.status when 4 then 1 else 0 end) as `makePriceRefuse`,\n" +
             "SUM(case tb.status when 10 then 1 else 0 end) as `transferMoneyConfirmation`\n" +
             "from tb_purchase_orders tb where provider_id = #{userId}")
     ProviderPurchaseOrderStatisticsResp getStatistics(@Param("userId") long userId);



}