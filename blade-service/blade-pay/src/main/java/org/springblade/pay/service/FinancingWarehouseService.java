package org.springblade.pay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.AccountDetail;
import org.springblade.common.entity.FinancingWarehouse;

/**
 * 融通仓实体
 *
 * @author hanbin
 */
public interface FinancingWarehouseService extends IService<FinancingWarehouse> {

    /**
     * 返回融通仓的分页列表
     * @param userid
     * @param page
     * @return
     */
    IPage<FinancingWarehouse> listFinancingWarehouse(Long userid, Page page);


    FinancingWarehouse getByIdWithStepList(Long id);

}