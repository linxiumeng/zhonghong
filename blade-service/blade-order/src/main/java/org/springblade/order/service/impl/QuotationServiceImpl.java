package org.springblade.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.commons.lang.StringUtils;
import org.springblade.common.entity.Demand;
import org.springblade.common.entity.GoodsTypeEntity;
import org.springblade.common.entity.Quotation;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.respond.QuotationResp;
import org.springblade.order.feign.GoodsServiceFeign;
import org.springblade.order.feign.UserServiceFeign;
import org.springblade.order.mapper.QuotationDao;
import org.springblade.order.service.DemandService;
import org.springblade.order.service.QuotationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 报价单表
 * (Quotation)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-10 11:59:55
 */
@Service("quotationService")
public class QuotationServiceImpl extends ServiceImpl<QuotationDao, Quotation> implements QuotationService {
    @Resource
    private QuotationDao quotationDao;

    @Resource
    DemandService demandService;

    @Resource
    UserServiceFeign userServiceFeign;

    @Resource
    GoodsServiceFeign goodsService;

    @Override
    public List<QuotationResp> selectQuotationRespList(Wrapper wrapper) {
        return quotationDao.selectQuotationRespList(wrapper);
    }

    @Override
    public Page<Quotation> listQuotationsWithDemand(Page<Quotation> page, Long userid) {
        QueryWrapper wrapper = new QueryWrapper();
     //   wrapper = SqlHelper.fillWrapper(page, wrapper);
        List<Quotation> quotations = this.baseMapper.selectQuotationListByDemand(page, wrapper, userid);
        Set<Long> demandUserIdList = new HashSet<>();
        Set<Long> demandGoodsTypeIdList = new HashSet<>(8);
        for (Quotation quotation : quotations) {
            Demand demand = demandService.getById(quotation.getDemandId());
            if(demand != null) {
                quotation.setDemand(demand);
                //填入批量的userid
                demandUserIdList.add(Long.valueOf(demand.getCreatUserid()));
                //填入批量的goodsTypeId
                demandGoodsTypeIdList.add(demand.getFType());
            }
        }

        org.springblade.core.tool.api.R<Collection<UserEntity>> userEntityListResult = userServiceFeign.batchGetUserByIds(demandUserIdList);

        org.springblade.core.tool.api.R<Collection<GoodsTypeEntity>> goodsTypeEntityListResult = goodsService.batchGetGoodsType(demandGoodsTypeIdList);

        Collection<UserEntity> userEntityCollection = userEntityListResult.getData();

        Collection<GoodsTypeEntity> goodsTypeCollection = goodsTypeEntityListResult.getData();

        for(Quotation quotation : quotations){
            Demand demand = quotation.getDemand();
            if(demand != null) {
                for (UserEntity userEntity : userEntityCollection) {
                    if(StringUtils.isNotBlank(demand.getCreatUserid())) {
                        Long createUserId = null;
                        try{
                            createUserId = Long.valueOf(demand.getCreatUserid());
                        }catch (NumberFormatException e){

                        }
                        if (createUserId != null && createUserId.longValue() == userEntity.getUserId().longValue()) {
                            demand.setCreateUser(userEntity);
                        }
                    }
                }
            }

            if(!goodsTypeCollection.isEmpty()){

                for(GoodsTypeEntity goodsTypeEntity : goodsTypeCollection){
                    if(goodsTypeEntity != null && demand != null && quotation.getFType() != null && goodsTypeEntity.getId() != null){
                        if(goodsTypeEntity.getId().longValue() == quotation.getFType().longValue()){
                            demand.setGoodsTypeEntity(goodsTypeEntity);
                        }
                    }
                }

            }

        }

        page.setRecords(quotations);
        return page;
    }

    @Override
    public List<Quotation> listQuotationsByDemandIdWithDemand(Long demandId, Long userId) {

        QueryWrapper<Quotation> wrapper = new QueryWrapper<>();
        Demand demand = demandService.getById(demandId);
        wrapper.eq("user_id", userId).eq("demand_id", demandId).orderBy(true,false,"create_date");
        List<Quotation> quotationList = this.list(wrapper);
        for (Quotation sub : quotationList) {
            sub.setDemand(demand);
        }
        return quotationList;
    }

}