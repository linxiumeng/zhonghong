package org.springblade.order.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.PurchaseOrders;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.enums.OrdersEnum;
import org.springblade.common.respond.ProviderPurchaseOrderStatisticsResp;

/**
 * (PurchaseOrders)表服务接口
 *
 * @author linxiumeng
 * @since 2019-03-07 17:27:06
 */
public interface PurchaseOrdersService extends IService<PurchaseOrders> {

    ProviderPurchaseOrderStatisticsResp getStatistics(long userId);

    /**
     * 根据商品 下单接口
     * @return
     */
    boolean putPurchaseOrderByGoods(UserEntity buyer, PurchaseOrders param);

    /**
     * 生成下单实体，返回给前端
     * @param buyer
     * @param param
     * @return
     */
    PurchaseOrders generatePurchaseOrderModelByGoods(UserEntity buyer, PurchaseOrders param);

    /**
     * 根据需求单 下单service
     * @param buyer
     * @param param
     * @return
     */
    boolean putPurchaseOrderByQuotation(UserEntity buyer, PurchaseOrders param);

    /**
     * 根据报价单下单
     * @param buyer
     * @param param
     * @return
     */
    PurchaseOrders generatePurchaseOrderModelByQuotation(UserEntity buyer, PurchaseOrders param);


    /**
     * 确认订单
     * @param purchaseOrders
     * @param userId
     * @return
     */
    boolean confirmPurchaseOrder(PurchaseOrders purchaseOrders, Long userId);


    /**
     * 为采购商查询订单列表
     * @param page
     * @param userId
     * @return
     */
    IPage<PurchaseOrders> listPurchaseOrdersUseForPurchaser(Page page, Long userId, String key, OrdersEnum ordersEnum);

    /**
     * 为供应商查询订单列表
     * @param page
     * @param userId
     * @return
     */
    IPage<PurchaseOrders> listPurchaseOrderUseForProvider(Page page, Long userId,String key,OrdersEnum ordersEnum);
    
}