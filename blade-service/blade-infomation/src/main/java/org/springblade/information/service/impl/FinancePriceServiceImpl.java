package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springblade.common.entity.FinancePrice;

import org.springblade.information.mapper.FinancePriceDao;
import org.springblade.information.service.FinancePriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    public List<FinancePrice> groupFinancePriceCode( ){
        /*QueryWrapper wrapper = Wrappers.query();
        wrapper.groupBy("code");
        wrapper.orderByDesc("create_time");*/
        return financePriceDao.groupFinancePriceCode();
    }

    /**
     * 根据code查询出他当天的分时数据
     *
     * @param
     * @return
     */
    @Override
    public List<FinancePrice> listCreateTime(FinancePrice financePrice) {
        QueryWrapper wrapper = Wrappers.query();
        wrapper.eq("code",financePrice.getCode());
        long currentDate = new Date().getTime();
        Date currentDate1 = new Date(currentDate);
        long now = System.currentTimeMillis() / 1000l;
        long daySecond = 60 * 60 * 24;
        long dayTime = now - (now + 8 * 3600) % daySecond;
        Date dayTime1 = new Date(dayTime);
        wrapper.ge("create_time",dayTime1);
        wrapper.le("create_time",currentDate1);
        List<FinancePrice> financePrices = financePriceService.list(wrapper);
        return financePrices;
    }
}

