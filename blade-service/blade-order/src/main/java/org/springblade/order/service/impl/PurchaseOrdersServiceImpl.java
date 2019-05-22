package org.springblade.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.*;
import org.springblade.common.enums.ErrorEnum;
import org.springblade.common.enums.OrdersEnum;
import org.springblade.common.exception.RRException;
import org.springblade.common.respond.ProviderPurchaseOrderStatisticsResp;
import org.springblade.core.tool.api.R;
import org.springblade.order.feign.GoodsServiceFeign;
import org.springblade.order.feign.UserServiceFeign;
import org.springblade.order.mapper.PurchaseOrdersDao;
import org.springblade.order.service.PurchaseOrdersService;
import org.springblade.order.service.QuotationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * (PurchaseOrders)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-07 17:27:06
 */
@Service("purchaseOrdersService")
public class PurchaseOrdersServiceImpl extends ServiceImpl<PurchaseOrdersDao, PurchaseOrders> implements PurchaseOrdersService {
    @Resource
    private PurchaseOrdersDao purchaseOrdersDao;

    @Resource
    private GoodsServiceFeign goodsService;

    @Resource
    UserServiceFeign userService;

    @Resource
    QuotationService quotationService;

    @Override
    public ProviderPurchaseOrderStatisticsResp getStatistics(long userId) {
        return purchaseOrdersDao.getStatistics(userId);
    }

    @Override
    public boolean putPurchaseOrderByGoods(UserEntity buyer, PurchaseOrders param) {

        PurchaseOrders purchaseOrdersClone = null;
        try {
            purchaseOrdersClone = (PurchaseOrders) param.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        if (buyer.getStatus() != 3) {
            throw new RRException(ErrorEnum.用户未认证.getDesc());
        }
        Goods goods = goodsService.getGoodsById(param.getGoodsId().longValue()).getData();

        if (goods == null) {
            throw new RRException("商品不存在");
        }
        // 删减库存
        boolean flag = goodsService.decrGoodsStock(goods.getId(), param.getGoodsAmount()).getData();

        if (!flag) {
            throw new RRException("商品库存不足");
        }

        Long userId = goods.getUserId();
        // UserEntity provider = userService.getUserById(userId).getData();

        R<UserEntity> r = userService.getUserById(userId);
        UserEntity provider = r.getData();
        if (r.getCode() == FeignResultCodeConstant.ENTITY_NOT_EXISTS) {
            provider = null;
            throw new RRException("供应商不存在");
        }

        setContact(param, buyer, provider);
        //复制属性
        BeanUtils.copyProperties(goods, param);
        copyGoodsPropertiesToOrders(goods, param,purchaseOrdersClone);
        param.setGoodsPic(goods.getPic());
        param.setCreatTime(null);
        param.setUpdateTime(null);
        param.setId(null);
        param.setStatus(OrdersEnum.ZERO);

        fixDesc(param,goods);

        boolean status = false;
        try{
            status = this.save(param);
        }catch (Exception e){
            //不确定这里一定正确执行
            //补偿性事务 将库存加回去 todo 这里为保证正确性最好加mq或入库 确保最终一致性
            goodsService.incrGoodsStock(goods.getId(), param.getGoodsAmount());
        }
        return status;

    }

    @Override
    public PurchaseOrders generatePurchaseOrderModelByGoods(UserEntity buyer, PurchaseOrders param) {
        PurchaseOrders purchaseOrdersClone = null;
        try {
            purchaseOrdersClone = (PurchaseOrders) param.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        if (buyer.getStatus() != 3) {
            throw new RRException(ErrorEnum.用户未认证.getDesc());
        }
        Goods goods = goodsService.getGoodsById(param.getGoodsId().longValue()).getData();
        Long userId = goods.getUserId();
        //  UserEntity provider = userService.getUserById(userId).getData();
        R<UserEntity> r = userService.getUserById(userId);
        UserEntity provider = r.getData();
        if (r.getCode() == FeignResultCodeConstant.ENTITY_NOT_EXISTS) {
            provider = null;
            throw new RRException("供应商不存在");
        }
        setContact(param, buyer, provider);



        BeanUtils.copyProperties(goods, param);
        copyGoodsPropertiesToOrders(goods, param,purchaseOrdersClone);

        fixDesc(param,goods);

        param.setId(null);

        List<GoodsTypeEntity> goodsTypeEntities = (List<GoodsTypeEntity>) (goodsService.batchGetGoodsType(Arrays.asList(param.getGoodsType()))).getData();
        if(!goodsTypeEntities.isEmpty()){
            param.setGoodsTypeEntity(goodsTypeEntities.get(0));
        }

        return param;
    }

    private void fixDesc(PurchaseOrders orders , Goods goods){
        orders.setProviderRemark(goods.getOtherDescription());
        orders.setGoodsRemark(goods.getGoodsDesc());
    }


    private void fixOrderWithQuotationDesc(PurchaseOrders order,Quotation quotation){
        order.setBuyerRemark(quotation.getRemark());
        order.setProviderRemark(quotation.getOtherDesc());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean putPurchaseOrderByQuotation(UserEntity buyer, PurchaseOrders param) {

        if (buyer.getStatus() != 3) {
            throw new RRException(ErrorEnum.用户未认证.getDesc());
        }
        Quotation quotation = quotationService.getById(param.getQuotationId());
        if (quotation == null) {
            throw new RRException("报价单不存在");
        }
        Long userId = quotation.getUserId();
        R<UserEntity> r = userService.getUserById(userId);
        UserEntity provider = r.getData();
        if (r.getCode() == FeignResultCodeConstant.ENTITY_NOT_EXISTS) {
            provider = null;
            throw new RRException("供应商不存在");
        }
        setContact(param, buyer, provider);
        BeanUtils.copyProperties(quotation, param);
        copyQuotationPropertiestToOrders(quotation, param);
        fixOrderWithQuotationDesc(param,quotation);
        return this.save(param);

    }

    @Override
    public PurchaseOrders generatePurchaseOrderModelByQuotation(UserEntity buyer, PurchaseOrders param) {
        if (buyer.getStatus() != 3) {
            throw new RRException(ErrorEnum.用户未认证.getDesc());
        }
        Quotation quotation = quotationService.getById(param.getQuotationId());
        Long userId = quotation.getUserId();
        R<UserEntity> r = userService.getUserById(userId);
        UserEntity provider = r.getData();
        if (r.getCode() == FeignResultCodeConstant.ENTITY_NOT_EXISTS) {
            provider = null;
            throw new RRException("供应商不存在");
        }
        setContact(param, buyer, provider);
        BeanUtils.copyProperties(quotation, param);
        fixOrderWithQuotationDesc(param,quotation);
        return param;
    }

    @Override
    public boolean confirmPurchaseOrder(PurchaseOrders purchaseOrders, Long userId) {

        QueryWrapper<PurchaseOrders> wrapper = new QueryWrapper<>();
        wrapper.eq("id", purchaseOrders.getId()).eq("provider_id", userId);
        PurchaseOrders po = this.getOne(wrapper);
        if (po.getStatus() == OrdersEnum.ZERO) {
            PurchaseOrders purchaseOrdersUpdate = new PurchaseOrders();
            purchaseOrdersUpdate.setId(purchaseOrders.getId());

            //对状态做验证
            OrdersEnum status = purchaseOrders.getStatus();
            if (status == null || (status != OrdersEnum.ONE && status != OrdersEnum.TWO)) {
                throw new RRException("状态值出错");
            }

            purchaseOrdersUpdate.setStatus(purchaseOrders.getStatus());
            return this.updateById(purchaseOrders);

        }

        return false;
    }

    @Override
    public IPage<PurchaseOrders> listPurchaseOrdersUseForPurchaser(Page page, Long userId, Integer key, OrdersEnum ordersEnum) {
        QueryWrapper<PurchaseOrders> wrapper = new QueryWrapper<>();
        buildSearchWrapper(wrapper, key, Arrays.asList(ordersEnum));
        wrapper.eq("buyer_id", userId).orderBy(true, false, "creat_time");
        IPage<PurchaseOrders> po = this.page(page, wrapper);

        setGoodsTypeInPurchaseOrdersList(po);


        return po;
    }

    @Override
    public IPage<PurchaseOrders> listPurchaseOrderUseForProvider(Page page, Long userId, Integer key, List<OrdersEnum> ordersEnum) {
        QueryWrapper<PurchaseOrders> wrapper = new QueryWrapper<>();

        buildSearchWrapper(wrapper, key, ordersEnum);

        wrapper.eq("provider_id", userId).orderBy(true, false, "creat_time");
        IPage<PurchaseOrders> po = this.page(page, wrapper);

        setGoodsTypeInPurchaseOrdersList(po);

        return po;
    }


    /**
     * 构造查询的wrapper
     *
     * @param wrapper
     * @param key
     * @param ordersEnum
     */
    private static void buildSearchWrapper(QueryWrapper<PurchaseOrders> wrapper, Integer key, List<OrdersEnum> ordersEnum) {
        if (key != null) {
            wrapper.eq("goods_type", key);
        }
        if (ordersEnum != null && ordersEnum.size() > 0) {
            ArrayList<Integer> list = new ArrayList<>();
            for(OrdersEnum subOrdersEnum : ordersEnum) {
                list.add(subOrdersEnum.getStatus());
            }
            wrapper.in("status", list.toArray());
        }
    }

    private void setGoodsTypeInPurchaseOrdersList(IPage<PurchaseOrders> pages) {
        List<PurchaseOrders> purchaseOrdersList = pages.getRecords();

        Set<Long> goodsTypeIds = new HashSet<>();
        for (PurchaseOrders po1 : purchaseOrdersList) {
            if(po1 != null) {
                goodsTypeIds.add(po1.getGoodsType());
            }
        }

        if(goodsTypeIds.isEmpty()){
            return;
        }
        //插入purchase的goodstype
        Collection<GoodsTypeEntity> goodsTypeEntities = goodsService.batchGetGoodsType(goodsTypeIds).getData();

        for (GoodsTypeEntity goodsTypeEntity : goodsTypeEntities) {
            for (PurchaseOrders purchaseOrders : purchaseOrdersList) {
                if (purchaseOrders.getGoodsType() != null && purchaseOrders.getGoodsType().longValue() == goodsTypeEntity.getId().longValue()) {
                    purchaseOrders.setGoodsTypeEntity(goodsTypeEntity);
                }
            }
        }
    }

    private void setContact(PurchaseOrders param, UserEntity buyer, UserEntity provider) {
        param.setBuyerCompany(buyer.getCompanyName());
        param.setBuyerEmail(buyer.getMail());
        param.setBuyerPhone(buyer.getContactNumber());
        param.setBuyerId(buyer.getUserId());
        param.setBuyerLinkman(buyer.getContacts());
        param.setBuyerAddress(buyer.getContactAddress());
        param.setProviderCompany(provider.getCompanyName());
        param.setProviderEmail(provider.getMail());
        param.setProviderPhone(provider.getContactNumber());
        param.setProviderId(provider.getUserId());
        param.setProviderLinkman(provider.getContacts());

    }

    private void copyGoodsPropertiesToOrders(Goods goods, PurchaseOrders purchaseOrders,PurchaseOrders purchaseOrdersClone) {
        purchaseOrders.setGoodsPrice(String.valueOf(goods.getGoodsPrice()));
        purchaseOrders.setGoodsName(goods.getGoodsName());
        purchaseOrders.setGoodsId(goods.getId().intValue());
        purchaseOrders.setFilePoint(goods.getFilePoint());
        purchaseOrders.setGoodsType(goods.getGoodsType());
        purchaseOrders.setGoodsUnit(goods.getGoodsUnit());

        if(purchaseOrdersClone != null) {

            purchaseOrders.setContractualValueDate(purchaseOrdersClone.getContractualValueDate());
            purchaseOrders.setPayDay(purchaseOrdersClone.getPayDay());
            purchaseOrders.setPaymentBy(purchaseOrdersClone.getPaymentBy());
            purchaseOrders.setPremiumsDiscounts(purchaseOrdersClone.getPremiumsDiscounts());
            purchaseOrders.setPricingManner(purchaseOrdersClone.getPricingManner());
            purchaseOrders.setTradeClause(purchaseOrdersClone.getTradeClause());
        }
    }

    private void copyQuotationPropertiestToOrders(Quotation quotation, PurchaseOrders purchaseOrders) {
        purchaseOrders.setGoodsName(quotation.getFName());
        purchaseOrders.setGoodsAmount(quotation.getNum());
        purchaseOrders.setGoodsType(quotation.getFType());
        purchaseOrders.setGoodsUnit(quotation.getFUnit());
        purchaseOrders.setGoodsPrice(String.valueOf(quotation.getPrice()));
        purchaseOrders.setFilePoint(quotation.getFilePoint());
    }

    /**
     * 查询最新的几条订单信息
     * @param page
     * @return
     */
    @Override
    public IPage<PurchaseOrders> listNewPurchaseOrders(IPage page){
        QueryWrapper wrapper = Wrappers.query();
        wrapper.orderByDesc("creat_time");
        return page(page,wrapper);
    }

    @Override
    public boolean isFinancePurchaseOrder(Long orderId) {
        return purchaseOrdersDao.countRepaymentByOrderId(orderId) > 0;
    }
}