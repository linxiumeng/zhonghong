package org.springblade.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.AccountDetail;
import org.springblade.common.entity.FinancingWarehouse;
import org.springblade.common.entity.FinancingWarehouseStep;
import org.springblade.pay.mapper.AccountDetailDao;
import org.springblade.pay.mapper.FinancingWarehouseDao;
import org.springblade.pay.service.AccountDetailService;
import org.springblade.pay.service.FinancingWarehouseService;
import org.springblade.pay.service.FinancingWarehouseStepService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * 余额详情表(AccountDetail)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-12 11:48:32
 */
@Service
public class FinancingWarehouseServiceImpl extends ServiceImpl<FinancingWarehouseDao, FinancingWarehouse> implements FinancingWarehouseService {

    @Resource
    FinancingWarehouseStepService financingWarehouseStepService;

    @Override
    public IPage<FinancingWarehouse> listFinancingWarehouse(Long userid, Page page) {

        QueryWrapper<FinancingWarehouse> queryWrapper = Wrappers.query();
        if(userid != null) {
            queryWrapper.eq("user_id", userid);
        }
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public FinancingWarehouse getByIdWithStepList(Long id) {
        FinancingWarehouse financingWarehouse = this.getById(id);

        QueryWrapper<FinancingWarehouseStep> stepQueryWrapper = Wrappers.query();
        stepQueryWrapper.eq("financing_id",id);
        List<FinancingWarehouseStep> stepList = financingWarehouseStepService.list(stepQueryWrapper);
        financingWarehouse.setFinancingWarehouseStepList(stepList);

        return financingWarehouse;
    }
}