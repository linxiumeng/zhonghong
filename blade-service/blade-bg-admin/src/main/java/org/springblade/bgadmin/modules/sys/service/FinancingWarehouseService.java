package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.FinancingWarehouseEntity;
import org.springblade.bgadmin.modules.sys.form.FinancingWarehouseForm;

/**
 * 融通仓实体
 *
 * @author hanbin
 */
public interface FinancingWarehouseService extends IService<FinancingWarehouseEntity> {

    /**
     * 分页查询
     * @param financingWarehouseForm
     * @return
     */
    IPage listFinancingWarehouse(FinancingWarehouseForm financingWarehouseForm);

    /**
     * 查询实体详情
     * @param id
     * @return
     */
    FinancingWarehouseEntity getFinancingWarehouseById(Long id);


}