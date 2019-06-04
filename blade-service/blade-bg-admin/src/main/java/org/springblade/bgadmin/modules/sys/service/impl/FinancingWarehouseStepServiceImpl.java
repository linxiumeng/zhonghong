package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.modules.sys.entity.FinancingWarehouseStepEntity;
import org.springblade.bgadmin.modules.sys.mapper.FinancingWarehouseStepDao;
import org.springblade.bgadmin.modules.sys.service.FinancingWarehouseService;
import org.springblade.bgadmin.modules.sys.service.FinancingWarehouseStepService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 *
 * @author linxiumeng
 * @since 2019-03-12 11:48:32
 */
@Service
public class FinancingWarehouseStepServiceImpl extends ServiceImpl<FinancingWarehouseStepDao, FinancingWarehouseStepEntity> implements FinancingWarehouseStepService {
    @Resource
    private FinancingWarehouseService financingWarehouseService;


}