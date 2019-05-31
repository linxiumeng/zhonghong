package org.springblade.information.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.form.FinanceDailyPriceForm;

import java.util.List;

/**
 * 走势图价格访问层
 */
@Mapper
public interface FinancePriceDao extends BaseMapper<FinancePrice> {
    /**
     * 根据code分组查询实体
     *
     * @return
     */
    @Select("select * from (select * from tb_finance_price ORDER BY create_time desc)f GROUP  BY code")
    List<FinancePrice> groupFinancePriceCode();


    /**
     * 替换或新增
     * @param financePrice
     * @return
     */
    @Insert("replace into tb_finance_price(`code`," +
            "`create_time`," +
            "`current_price`," +
            "`today_highest_price`," +
            "`today_lowest_price`," +
            "`current_grow_percent`," +
            "`today_open_price`," +
            "`yesterday_close_price`) values (#{code},#{createTime},#{currentPrice},#{todayHighestPrice},#{todayLowestPrice},#{currentGrowPercent},#{todayOpenPrice},#{yesterdayClosePrice})")
    boolean replaceOrInsert(FinancePrice financePrice);

    /**
     * 根据小时获得分时数据
     *
     * @return
     */
    /*@Select("select * from tb_finance_price where `code` = #{code} and create_time in (select min(create_time) from tb_finance_price where `code` = #{code} group by DATE_FORMAT(create_time,'%Y%m%d%H')) and create_time > CURDATE()")
    List<FinancePrice> groupFinancePriceCreateHour(FinancePrice financePrice);*/

    /**
     * 获取当前24小时的数据 每条数据间隔5min
     * @param
     * @return
     */
    @Select("select * from tb_finance_price where create_time >(NOW() - interval 24 hour) and DATE_FORMAT(create_time,'%i')%5 = 0 and `code` = #{code}")
    List<FinancePrice> listDayMinute(FinanceDailyPriceForm  financeDailyPriceForm);

    /**
     * 获取当前前一周的数据 每条数据间隔1hour
     * @param
     * @return
     */
    @Select("select * from tb_finance_price where date_sub(curdate(), INTERVAL 7 DAY) <= date(`create_time`) and DATE_FORMAT(create_time,'%H')%1 = 0 and `code` = #{code}")
    List<FinancePrice> listWeekHour(FinanceDailyPriceForm  financeDailyPriceForm);

}
