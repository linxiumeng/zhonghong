package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springblade.common.entity.FinancePrice;

import org.springblade.common.utils.R;
import org.springblade.information.mapper.FinancePriceDao;
import org.springblade.information.service.FinancePriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 走势图实现类
 */
@Service("FinancePriceService")
public class FinancePriceServiceImpl extends ServiceImpl<FinancePriceDao, FinancePrice> implements FinancePriceService {
    @Resource
    FinancePriceDao financePriceDao;
    @Resource
    FinancePriceService financePriceService;
    /**
     * 根据code查询实体
     */
    public FinancePrice selectFinancePriceCode(String code){
        QueryWrapper wrapper = Wrappers.query();
        wrapper.eq("code",code);
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 1");
        List<FinancePrice> prices = financePriceService.list(wrapper);
        if(!prices.isEmpty()){
            return prices.get(0);
        }
        return null;
    }
}

