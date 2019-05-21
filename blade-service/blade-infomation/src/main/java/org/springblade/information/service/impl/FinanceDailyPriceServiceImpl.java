package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.FinanceDailyPrice;
import org.springblade.common.entity.FinancePriceType;
import org.springblade.common.enums.PriceDateEnum;
import org.springblade.common.form.FinanceDailyPriceForm;
import org.springblade.common.utils.DateUtils;
import org.springblade.information.mapper.FinanceDailyPriceDao;
import org.springblade.information.service.FinanceDailyPriceService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * 走势图实现类
 */
@Service
public class FinanceDailyPriceServiceImpl extends ServiceImpl<FinanceDailyPriceDao, FinanceDailyPrice> implements FinanceDailyPriceService {

    @Override
    public List<FinanceDailyPrice> listDayFinancePrice(FinanceDailyPriceForm financeDailyPriceForm) {
        completeForm(financeDailyPriceForm);
        QueryWrapper<FinanceDailyPrice> queryWrapper = Wrappers.query();
        queryWrapper.eq("code", financeDailyPriceForm.getCode());
        queryWrapper.le("create_date", financeDailyPriceForm.getEndDate());
        queryWrapper.ge("create_date", financeDailyPriceForm.getStartDate());
        return this.list(queryWrapper);
    }

    @Override
    public List<FinanceDailyPrice> listWeekFinancePrice(FinanceDailyPriceForm financeDailyPriceForm) {
        completeForm(financeDailyPriceForm);
        return baseMapper.listWeekFinancePrice(financeDailyPriceForm);
    }

    @Override
    public List<FinanceDailyPrice> listMonthFinancePrice(FinanceDailyPriceForm financeDailyPriceForm) {
        completeForm(financeDailyPriceForm);
        return baseMapper.listMonthFinancePrice(financeDailyPriceForm);
    }

    @Override
    public List<FinanceDailyPrice> listYearFinancePrice(FinanceDailyPriceForm financeDailyPriceForm) {
        completeForm(financeDailyPriceForm);
        return baseMapper.listYearFinancePrice(financeDailyPriceForm);
    }


    private void completeForm(FinanceDailyPriceForm financeDailyPriceForm) {

        if (financeDailyPriceForm.getPreDays() != null) {
            Integer preDays = financeDailyPriceForm.getPreDays();
            financeDailyPriceForm.setStartDate(DateUtils.addDateDays(new Date(), preDays * -1));
        }

        if (financeDailyPriceForm.getEndDate() == null) {
            // 1970年 时间戳
            financeDailyPriceForm.setEndDate(new Date(System.currentTimeMillis()));
        }
        if (financeDailyPriceForm.getCode() == null) {
            financeDailyPriceForm.setCode(FinancePriceType.HF_CL.getCode());
        }
    }
}

