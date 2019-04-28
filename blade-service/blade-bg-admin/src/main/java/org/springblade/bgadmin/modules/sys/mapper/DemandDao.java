package org.springblade.bgadmin.modules.sys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.bgadmin.modules.sys.entity.DemandEntity;
import org.springblade.bgadmin.modules.sys.entity.DemandOrderUserEntity;
import org.springblade.bgadmin.modules.sys.form.mybatis.DemandCondition;

import java.util.List;

/**
 * 需求表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
public interface DemandDao extends BaseMapper<DemandEntity> {

    List<DemandOrderUserEntity> selectDemandList(IPage iPage, @Param("condition") DemandCondition demandCondition);

}
