package org.springblade.pay.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.AccountDetail;
import org.springblade.common.entity.FinancingWarehouse;
import org.springblade.common.entity.FinancingWarehouseStep;
import org.springblade.pay.mapper.AccountDetailDao;
import org.springblade.pay.mapper.FinancingWarehouseStepDao;
import org.springblade.pay.service.AccountDetailService;
import org.springblade.pay.service.FinancingWarehouseService;
import org.springblade.pay.service.FinancingWarehouseStepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 *
 * @author linxiumeng
 * @since 2019-03-12 11:48:32
 */
@Service
public class FinancingWarehouseStepServiceImpl extends ServiceImpl<FinancingWarehouseStepDao, FinancingWarehouseStep> implements FinancingWarehouseStepService {
    @Resource
    private FinancingWarehouseService financingWarehouseService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean returnMoney(Long id) {

        FinancingWarehouseStep step = baseMapper.selectById(id);

        if (step != null) {
            step.setPeriodDate(new Date());
            step.setStatus(1);
            int count = baseMapper.updateById(step);
            if (count > 0) {
                int noReturnCount = baseMapper.selectCount(Wrappers.<FinancingWarehouseStep>query().eq("financing_id",step.getFinancingId()).in("status",1,0));
                if(noReturnCount <= 0){
                    FinancingWarehouse financingWarehouse = new FinancingWarehouse();
                    financingWarehouse.setId(step.getFinancingId());
                    financingWarehouse.setStatus(1);
                    financingWarehouse.setRecentRepaymentDate(new Date());
                    financingWarehouse.setCurrentPeriod(step.getPeriod());
                    return financingWarehouseService.updateById(financingWarehouse);
                }
                return true;
            }

        }


        return false;
    }
}