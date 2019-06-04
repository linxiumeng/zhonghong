package org.springblade.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.AccountDetail;
import org.springblade.common.entity.FinancingWarehouseStep;

/**
 * 融通仓分期
 *
 * @author hanbin
 */
public interface FinancingWarehouseStepService extends IService<FinancingWarehouseStep> {



    boolean returnMoney(Long id);


}