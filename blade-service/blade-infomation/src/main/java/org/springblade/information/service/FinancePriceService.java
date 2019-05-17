package org.springblade.information.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.utils.PageUtils;

import java.util.List;
import java.util.Map;


/**
 * FinancePrice服务接口
 */
public interface FinancePriceService extends IService<FinancePrice> {
    /**
     * 根据code查询实体
     * @param code
     * @return
     */
    FinancePrice selectFinancePriceCode(String code);
}
