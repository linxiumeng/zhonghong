package org.springblade.information.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.form.FinancePriceForm;
import org.springblade.common.utils.PageUtils;

import java.util.List;
import java.util.Map;


/**
 * FinancePrice服务接口
 */
public interface FinancePriceService extends IService<FinancePrice> {
    /**
     * 根据code分组查询实体
     * @return
     */
    List<FinancePrice> groupFinancePriceCode( );

    /**
     * 根据code查询出他的分时数据
     *
     * @param
     * @return
     */
    List<FinancePrice> listCreateTime(FinancePrice financePrice);

    void upsert(FinancePrice financePrice);
}
