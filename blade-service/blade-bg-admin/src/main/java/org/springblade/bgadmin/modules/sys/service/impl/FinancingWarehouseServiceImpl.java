package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springblade.bgadmin.modules.sys.entity.FinancingWarehouseEntity;
import org.springblade.bgadmin.modules.sys.form.FinancingWarehouseForm;
import org.springblade.bgadmin.modules.sys.mapper.FinancingWarehouseDao;
import org.springblade.bgadmin.modules.sys.service.FinancingWarehouseService;
import org.springblade.common.entity.FinancingWarehouse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 余额详情表(AccountDetail)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-12 11:48:32
 */
@Service
public class FinancingWarehouseServiceImpl extends ServiceImpl<FinancingWarehouseDao, FinancingWarehouseEntity> implements FinancingWarehouseService {

    @Override
    public IPage listFinancingWarehouse(FinancingWarehouseForm financingWarehouseForm) {

        Page page = new Page(financingWarehouseForm.getPage(), financingWarehouseForm.getSize());
        QueryWrapper<FinancingWarehouse> queryWrapper = Wrappers.query();
        queryWrapper.eq("1", 1);

        List<Integer> overDateStatusList = null;

        if(financingWarehouseForm.getOverDateStatus() != null){
            overDateStatusList = new ArrayList<>();
            //未逾期
            if(financingWarehouseForm.getOverDateStatus() == 0){
                overDateStatusList.add(1);
                overDateStatusList.add(0);
            }else if (financingWarehouseForm.getOverDateStatus() == 1){
                overDateStatusList.add(2);
            }else if(financingWarehouseForm.getOverDateStatus() == -1){
                overDateStatusList.add(1);
                overDateStatusList.add(0);
                overDateStatusList.add(2);
            }
        }

        if(financingWarehouseForm.getPassStatus() != null){
            //未通过
            if(financingWarehouseForm.getPassStatus() == 0){
                queryWrapper.in("a.status",0,1);
            }else if (financingWarehouseForm.getPassStatus() == 1){
                queryWrapper.in("a.status",2,3);
            }
        }

        if(financingWarehouseForm.getStartDate() != null){
            queryWrapper.ge("a.create_date",financingWarehouseForm.getStartDate());
        }
        if(financingWarehouseForm.getEndDate() != null){
            queryWrapper.le("a.create_date",financingWarehouseForm.getEndDate());
        }

        if(StringUtils.isNotBlank(financingWarehouseForm.getKeywords())){
            queryWrapper.and(i->i.like("b.company_name",financingWarehouseForm.getKeywords()).or().like("b.mobile",financingWarehouseForm.getKeywords()));
        }

        List<FinancingWarehouseEntity> resultList = baseMapper.listFinancingWarehouseById(page, queryWrapper,overDateStatusList);
        page.setRecords(resultList);
        return page;
    }

    @Override
    public FinancingWarehouseEntity getFinancingWarehouseById(Long id) {
        return baseMapper.getFinancingWarehouseById(id);
    }
}