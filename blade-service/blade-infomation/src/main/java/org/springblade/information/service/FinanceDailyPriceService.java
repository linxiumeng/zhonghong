package org.springblade.information.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.FinanceDailyPrice;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.form.FinanceDailyPriceForm;
import org.springblade.common.form.FinancePriceForm;

import java.util.List;


/**
 * FinancePrice服务接口
 */
public interface FinanceDailyPriceService extends IService<FinanceDailyPrice> {

    List<FinanceDailyPrice> listDayFinancePrice(FinanceDailyPriceForm financeDailyPriceForm);

    List<FinanceDailyPrice> listWeekFinancePrice(FinanceDailyPriceForm financeDailyPriceForm);

    List<FinanceDailyPrice> listMonthFinancePrice(FinanceDailyPriceForm financeDailyPriceForm);

    List<FinanceDailyPrice> listYearFinancePrice(FinanceDailyPriceForm financeDailyPriceForm);

}
