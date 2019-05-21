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
    @Select("select * from tb_finance_daily_k_price where `code` = #{code} and create_date in (select max(create_date) from tb_finance_daily_k_price where `code` = #{code} group by DATE_FORMAT(create_date,'%Y%u')) and create_date > #{startDate} ")
    List<FinanceDailyPrice> listWeekFinancePrice(FinanceDailyPriceForm financeDailyPriceForm);

    /**
     * 月k列表
     * @param financeDailyPriceForm
     * @return
     */
    @Select("select * from tb_finance_daily_k_price where `code` = #{code} and create_date in (select max(create_date) from tb_finance_daily_k_price where `code` = #{code} group by DATE_FORMAT(create_date,'%Y%m')) and create_date > #{startDate} ")
    List<FinanceDailyPrice> listMonthFinancePrice(FinanceDailyPriceForm financeDailyPriceForm);

    /**
     * 年k列表
     * @param financeDailyPriceForm
     * @return
     */
    @Select("select * from tb_finance_daily_k_price where `code` = #{code} and create_date in (select max(create_date) from tb_finance_daily_k_price where `code` = #{code} group by DATE_FORMAT(create_date,'%Y')) and create_date > #{startDate} ")
    List<FinanceDailyPrice> listYearFinancePrice(FinanceDailyPriceForm financeDailyPriceForm);

}
