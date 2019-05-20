package org.springblade.information.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.common.entity.FinanceDailyPrice;
import org.springblade.common.entity.FinancePrice;

/**
 * 走势图价格访问层
 */
@Mapper
public interface FinanceDailyPriceDao extends BaseMapper<FinanceDailyPrice> {
}
