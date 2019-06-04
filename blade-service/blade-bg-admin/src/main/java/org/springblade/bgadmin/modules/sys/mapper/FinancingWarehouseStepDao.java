package org.springblade.bgadmin.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.bgadmin.modules.sys.entity.FinancingWarehouseStepEntity;
import org.springblade.common.entity.FinancingWarehouseStep;

import java.util.List;

/**
 * 融通仓分期
 *
 * @author hanbin
 */
@Mapper
public interface FinancingWarehouseStepDao extends BaseMapper<FinancingWarehouseStepEntity> {

    List<FinancingWarehouseStep> listFinancingWarehouseByFinancingId(Long financingId);

    int countFinancingWarehouseOverDateByFinancingId(Long financingId);

    int countFinancingWarehouseAlreadyPayByFinancingId(Long financingId);
}