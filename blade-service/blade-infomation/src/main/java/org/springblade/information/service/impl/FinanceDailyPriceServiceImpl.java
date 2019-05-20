package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.FinanceDailyPrice;
import org.springblade.common.form.FinanceDailyPriceForm;
import org.springblade.information.mapper.FinanceDailyPriceDao;
import org.springblade.information.service.FinanceDailyPriceService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


/**
 * 走势图实现类
 */
@Service
public class FinanceDailyPriceServiceImpl extends ServiceImpl<FinanceDailyPriceDao, FinanceDailyPrice> implements FinanceDailyPriceService {

    @Override
    public List<FinanceDailyPrice> listDayFinancePrice(FinanceDailyPriceForm financeDailyPriceForm) {
        QueryWrapper<FinanceDailyPrice> queryWrapper = Wrappers.query();
        queryWrapper.eq("code",financeDailyPriceForm.getCode());
        queryWrapper.le("create_date",financeDailyPriceForm.getEndDate());
        queryWrapper.ge("create_date",financeDailyPriceForm.getStartDate());
        return this.list(queryWrapper);
    }

    /**
     * select a.*,a.today_close_price - (select today_close_price from tb_finance_daily_k_price b
     * 			where DATEDIFF(a.create_date,b.create_date) = 7 and
     * 						`code` = 'hf_CL') from tb_finance_daily_k_price a
     * 						where a.`code`='hf_CL' and create_date > '2019-01-01' and DATEDIFF(CURRENT_DATE,a.create_date) % 5 = 0
     * @param financeDailyPriceForm
     * @return
     */
    @Override
    public List<FinanceDailyPrice> listWeekFinancePrice(FinanceDailyPriceForm financeDailyPriceForm) {

        return Collections.emptyList();
    }

    @Override
    public List<FinanceDailyPrice> listMonthFinancePrice(FinanceDailyPriceForm financeDailyPriceForm) {
        return Collections.emptyList();
    }

    @Override
    public List<FinanceDailyPrice> listYearFinancePrice(FinanceDailyPriceForm financeDailyPriceForm) {
        return Collections.emptyList();
    }
}

