package org.springblade.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springblade.common.entity.Demand;
import org.springblade.common.entity.Quotation;
import org.springblade.common.respond.QuotationResp;
import org.springblade.order.mapper.QuotationDao;
import org.springblade.order.service.DemandService;
import org.springblade.order.service.QuotationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


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

    @Override
    public List<QuotationResp> selectQuotationRespList(Wrapper wrapper) {
        return quotationDao.selectQuotationRespList(wrapper);
    }

    @Override
    public Page<Quotation> listQuotationsWithDemand(Page<Quotation> page, Long userid) {
        QueryWrapper wrapper = new QueryWrapper();
     //   wrapper = SqlHelper.fillWrapper(page, wrapper);

        List<Quotation> quotations = this.baseMapper.selectQuotationListByDemand(page, wrapper, userid);
        for (Quotation quotation : quotations) {
            Demand demand = demandService.getById(quotation.getDemandId());
            quotation.setDemand(demand);
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