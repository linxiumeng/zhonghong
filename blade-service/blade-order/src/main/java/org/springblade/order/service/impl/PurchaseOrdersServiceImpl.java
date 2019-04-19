package org.springblade.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.Goods;
import org.springblade.common.entity.PurchaseOrders;
import org.springblade.common.entity.Quotation;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.enums.ErrorEnum;
import org.springblade.common.enums.OrdersEnum;
import org.springblade.common.exception.RRException;
import org.springblade.common.respond.ProviderPurchaseOrderStatisticsResp;
import org.springblade.order.feign.GoodsServiceFeign;
import org.springblade.order.feign.UserServiceFeign;
import org.springblade.order.mapper.PurchaseOrdersDao;
import org.springblade.order.service.PurchaseOrdersService;
import org.springblade.order.service.QuotationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


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
        UserEntity provider = userService.getUserById(userId).getData();
        setContact(param, buyer, provider);
        //复制属性
        BeanUtils.copyProperties(goods, param);
        copyGoodsPropertiesToOrders(goods, param);
        param.setGoodsPic(goods.getPic());
        param.setCreatTime(null);
        param.setUpdateTime(null);
        return this.save(param);

    }

    @Override
    public PurchaseOrders generatePurchaseOrderModelByGoods(UserEntity buyer, PurchaseOrders param) {

        if(buyer.getStatus()!=3) {
            throw new RRException(ErrorEnum.用户未认证.getDesc());
        }
        Goods goods = goodsService.getGoodsById(param.getGoodsId().longValue()).getData();
        Long userId =goods.getUserId();
        UserEntity provider = userService.getUserById(userId).getData();
        setContact(param, buyer, provider);
        BeanUtils.copyProperties(goods,param);
        return param;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean putPurchaseOrderByQuotation(UserEntity buyer, PurchaseOrders param) {

        if(buyer.getStatus()!= 3) {
            throw new RRException(ErrorEnum.用户未认证.getDesc());
        }
        Quotation quotation = quotationService.getById(param.getQuotationId());
        if(quotation == null){
            throw new RRException("报价单不存在");
        }
        Long userId = quotation.getUserId();
        UserEntity provider = userService.getUserById(userId).getData();
        setContact(param, buyer, provider);
        BeanUtils.copyProperties(quotation,param);
        copyQuotationPropertiestToOrders(quotation,param);
        return this.save(param);

    }

    @Override
    public PurchaseOrders generatePurchaseOrderModelByQuotation(UserEntity buyer, PurchaseOrders param) {
        if(buyer.getStatus()!=3) {
            throw new RRException(ErrorEnum.用户未认证.getDesc());
        }
        Quotation quotation = quotationService.getById(param.getQuotationId());
        Long userId = quotation.getUserId();
        UserEntity provider = userService.getUserById(userId).getData();
        setContact(param, buyer, provider);
        BeanUtils.copyProperties(quotation,param);
        return param;
    }

    @Override
    public boolean confirmPurchaseOrder(PurchaseOrders purchaseOrders,Long userId) {

        QueryWrapper<PurchaseOrders> wrapper = new QueryWrapper<>();
        wrapper.eq("id", purchaseOrders.getId()).eq("provider_id", userId);
        PurchaseOrders po = this.getOne(wrapper);
        if (po.getStatus() == OrdersEnum.ZERO.getStatus()) {
            PurchaseOrders purchaseOrdersUpdate = new PurchaseOrders();
            purchaseOrdersUpdate.setId(purchaseOrders.getId());

            //对状态做验证
            Integer status = purchaseOrders.getStatus();
            if (status == null || (status != 1 && status != 2)) {
                throw new RRException("状态值出错");
            }

            purchaseOrdersUpdate.setStatus(purchaseOrders.getStatus());
            return this.updateById(purchaseOrders);

        }

        return false;
    }

    @Override
    public IPage<PurchaseOrders> listPurchaseOrdersUseForPurchaser(Page page, Long userId) {
        QueryWrapper<PurchaseOrders> wrapper = new QueryWrapper<>();
        wrapper.eq("buyer_id", userId).orderBy(true,false,"creat_time");
        IPage<PurchaseOrders> po = this.page(page, wrapper);
        return po;
    }

    @Override
    public IPage<PurchaseOrders> listPurchaseOrderUseForProvider(Page page, Long userId) {
        QueryWrapper<PurchaseOrders> wrapper = new QueryWrapper<>();
        wrapper.eq("provider_id", userId).orderBy(true,false,"creat_time");
        IPage<PurchaseOrders> po = this.page(page, wrapper);
        return po;
    }


    private void setContact(PurchaseOrders param, UserEntity buyer, UserEntity provider) {
        param.setBuyerCompany(buyer.getCompanyName());
        param.setBuyerPhone(buyer.getContactNumber());
        param.setBuyerId(buyer.getUserId());
        param.setBuyerLinkman(buyer.getContacts());
        param.setBuyerAddress(buyer.getContactAddress());
        param.setProviderCompany(provider.getCompanyName());
        param.setProviderPhone(provider.getContactNumber());
        param.setProviderId(provider.getUserId());
        param.setProviderLinkman(provider.getContacts());
    }

    private void copyGoodsPropertiesToOrders(Goods goods, PurchaseOrders purchaseOrders) {
        purchaseOrders.setGoodsPrice(String.valueOf(goods.getGoodsPrice()));
        purchaseOrders.setGoodsName(goods.getGoodsName());
        purchaseOrders.setGoodsId(goods.getId().intValue());

        purchaseOrders.setGoodsType(goods.getGoodsType());
        purchaseOrders.setGoodsUnit(goods.getGoodsUnit());
    }

    private void copyQuotationPropertiestToOrders(Quotation quotation,PurchaseOrders purchaseOrders){
        purchaseOrders.setGoodsName(quotation.getFName());
        purchaseOrders.setGoodsType(quotation.getFType());
        purchaseOrders.setGoodsUnit(quotation.getFUnit());
    }
}