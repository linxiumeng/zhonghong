package org.springblade.bgadmin.modules.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.bgadmin.modules.sys.entity.FinancingWarehouseEntity;

import java.util.List;

/**
 * 融通仓
 *
 * @author hanbin
 */
@Mapper
public interface FinancingWarehouseDao extends BaseMapper<FinancingWarehouseEntity> {

    /**
     * 获取单体
     * @param id
     * @return
     */
    FinancingWarehouseEntity getFinancingWarehouseById(Long id);


    /**
     * 获取列表
     * @param page
     * @param wrapper
     * @param overDateStatusList
     * @return
     */
    List<FinancingWarehouseEntity> listFinancingWarehouseById(IPage page, @Param("ew") Wrapper wrapper,@Param("overDateStatusList")List<Integer> overDateStatusList);


}