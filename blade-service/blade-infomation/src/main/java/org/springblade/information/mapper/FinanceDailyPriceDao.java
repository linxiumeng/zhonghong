package org.springblade.information.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springblade.common.entity.FinanceDailyPrice;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.form.FinanceDailyPriceForm;

import java.util.List;

/**
 * 走势图价格访问层
 */
@Mapper
public interface FinanceDailyPriceDao extends BaseMapper<FinanceDailyPrice> {

    /**
     * 周k列表
     * @param financeDailyPriceForm
     * @return
     */
    @Select("select a.* from tb_finance_daily_k_price a \n" +
            "where a.`code`='hf_CL' and create_date &gt; #{startDate} and create_date &lt; #{endDate} and DATEDIFF(CURRENT_DATE,a.create_date) % 5 = 0")
    List<FinanceDailyPrice> listWeekFinancePrice(FinanceDailyPriceForm financeDailyPriceForm);

    /**
     * 月k列表
     * @param financeDailyPriceForm
     * @return
     */
    @Select("")
    List<FinanceDailyPrice> listMonthFinancePrice(FinanceDailyPriceForm financeDailyPriceForm);

    /**
     * 年k列表
     * @param financeDailyPriceForm
     * @return
     */
    @Select("")
    List<FinanceDailyPrice> listYearFinancePrice(FinanceDailyPriceForm financeDailyPriceForm);

}
