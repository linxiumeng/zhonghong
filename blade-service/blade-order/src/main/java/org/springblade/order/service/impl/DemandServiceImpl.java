package org.springblade.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.Demand;
import org.springblade.common.entity.Quotation;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.exception.RRException;
import org.springblade.common.respond.DemandResp;
import org.springblade.common.respond.QuotationResp;
import org.springblade.order.feign.UserServiceFeign;
import org.springblade.order.mapper.DemandDao;
import org.springblade.order.service.DemandService;
import org.springblade.order.service.QuotationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * (Demand)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-07 10:57:43
 */
@Service("demandService")
public class DemandServiceImpl extends ServiceImpl<DemandDao, Demand> implements DemandService {
    @Resource
    private DemandDao demandDao;

    @Resource
    QuotationService quotationService;

    @Resource
    UserServiceFeign userService;

    @Override
    public Page<DemandResp> listOwnDemandPage(Page page, Long id) {

        QueryWrapper<Demand> wrapper = new QueryWrapper<>();
        wrapper.eq("creat_userid", id).orderBy(true,false,"creat_time");
        IPage<Demand> demandPage = this.page(page, wrapper);
        Page<DemandResp> demandRespPage = new Page<>();

        // 经测试 连接查询性能很差。。。

        try {
            BeanUtils.copyProperties(demandPage, demandRespPage);

            List<DemandResp> demandRespList = new LinkedList<>();
            for (Demand demand : demandPage.getRecords()) {
                List<Quotation> quotationList = quotationService.list(new QueryWrapper<Quotation>().eq("demand_id", demand.getId()));
                DemandResp demandResp = new DemandResp();
                BeanUtils.copyProperties(demand, demandResp);
                demandResp.setQuotationList(quotationList);
                demandRespList.add(demandResp);
            }
            demandRespPage.setRecords(demandRespList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("查询出错");
        }

        return demandRespPage;
    }

    @Override
    public boolean updateDemandByMyself(Demand demand,Long userId) {

        QueryWrapper<Demand> wrapper = new QueryWrapper<>();
        wrapper.eq("creat_userid", userId).eq("id", demand.getId());
        return this.update(demand, wrapper);
    }

    @Override
    public Map<String, Object> getDemandInfoAndQuotationListInfo(Long demandId,Long userId) {

        Map<String,Object> resultMap = new HashMap<>();
      //  QueryWrapper<Demand> wrapper = new QueryWrapper<>();
      //  wrapper.eq("creat_userid", userId);
        Demand demand = this.getById(demandId);
        try {
            if (Long.valueOf(demand.getCreatUserid()).longValue() == userId.longValue()) {

                List<QuotationResp> quotationList = quotationService.selectQuotationRespList(new QueryWrapper<>().eq("demand_id", demand.getId()));

                //先这样做 防止分库
                UserEntity userEntity = null;

                demand.setCreateUser(userEntity);

                resultMap.put("row", demand);
                resultMap.put("quotationList", quotationList);

                return resultMap;
            }
        } catch (NumberFormatException | NullPointerException e) {
            throw new RRException("服务器出错");
        }
        throw new RRException("您无法查看别人的需求");

    }

    @Override
    public IPage<Demand> listCanQuotationDemand(Page page, Long userId) {
        QueryWrapper<Demand> wrapper = new QueryWrapper<>();

        // status 为 1 是报价中
        wrapper.eq("status", 1).orderBy(true,false,"creat_time");
        IPage<Demand> resultPage = this.page(page, wrapper);
        List<Demand> demandList = resultPage.getRecords();
        try {
            //循环遍历，获取user todo 需要优化
            for (Demand demand : demandList) {
                Long createUserid = Long.valueOf(demand.getCreatUserid());
                //调用用户服务(修改成batch)
                UserEntity userEntity = null;
                demand.setCreateUser(userEntity);
            }
        } catch (NumberFormatException e) {
            throw new RRException("系统出现错误");
        }
        return resultPage;
    }

    /*@Override
    public Page<DemandResp> selectDemandListWithQuotationList(Page page, Wrapper wrapper) {
        wrapper = SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(demandDao.selectDemandListWithQuotationList(page, wrapper));
        return page;
    }*/


}