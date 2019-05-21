package org.springblade.information.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springblade.common.entity.FinancePrice;

import java.util.List;

/**
 * 走势图价格访问层
 */
@Mapper
public interface FinancePriceDao extends BaseMapper<FinancePrice> {
    /**
     * 根据code分组查询实体
     * @return
     */
    @Select("select * from (select * from tb_finance_price ORDER BY create_time desc)f GROUP  BY code")
   List<FinancePrice> groupFinancePriceCode( );
}
