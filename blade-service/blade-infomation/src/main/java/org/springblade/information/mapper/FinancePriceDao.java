package org.springblade.information.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.common.entity.FinancePrice;

import java.util.List;

/**
 * 走势图价格访问层
 */
@Mapper
public interface FinancePriceDao extends BaseMapper<FinancePrice> {
    /**
     * 根据code查询实体
     * @param code
     * @return
     */
   FinancePrice selectFinancePriceCode(String code);
}
