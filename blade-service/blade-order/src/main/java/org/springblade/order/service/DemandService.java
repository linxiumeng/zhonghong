package org.springblade.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.Demand;
import org.springblade.common.respond.DemandResp;

import java.util.Map;


/**
 * (Demand)表服务接口
 *
 * @author linxiumeng
 * @since 2019-03-07 10:57:43
 */
public interface DemandService extends IService<Demand> {


    // Page<DemandResp> selectDemandListWithQuotationList(Page page, Wrapper wrapper);

    /**
     *
     * @param page
     * @param id
     * @return
     */
    Page<DemandResp> listOwnDemandPage(Page page, Long id);

    /**
     * 修改自己的需求单
     * @param demand
     * @param userId
     * @return
     */
    boolean updateDemandByMyself(Demand demand, Long userId);

    /**
     * 获取自己 需求单详情和报价列表
     * @param demandId
     * @param userId
     * @return
     */
    Map<String,Object> getDemandInfoAndQuotationListInfo(Long demandId, Long userId);

    /**
     * 返回可报价的需求单列表
     * @param page
     * @param userId
     * @return
     */
    IPage<Demand> listCanQuotationDemand(Page page, Long userId);

}