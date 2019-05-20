package org.springblade.order.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.Quotation;
import org.springblade.common.respond.QuotationResp;

import java.util.List;
import java.util.Map;

/**
 * 报价单表
(Quotation)表服务接口
 *
 * @author linxiumeng
 * @since 2019-03-10 11:59:55
 */
public interface QuotationService extends IService<Quotation> {


    /**
     * 查询报价单返回列表
     * @param wrapper
     * @return
     */
    List<QuotationResp> selectQuotationRespList(Wrapper wrapper);


    /**
     * 报价单列表 携带需求单实体
     * @param page
     * @param userId
     * @return
     */
    Page<Quotation> listQuotationsWithDemand(Page<Quotation> page, Long userId);

    /**
     * 根据需求单查询报价单列表
     * @param demandId
     * @param userId
     * @return
     */
    List<Quotation> listQuotationsByDemandIdWithDemand(Long demandId, Long userId);

    /**
     * 根据demandIds获取quotation的数量
     * @param demandIds
     * @return
     */
    List<Map<String,Object>> listCountQuotationByDemandIds(List<Long> demandIds);

    
}