package org.springblade.bgadmin.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.bgadmin.modules.sys.entity.FaqEntity;

/**
 * @author 常见问题操作层
 */
@Mapper
public interface FaqDao extends BaseMapper<FaqEntity> {
}
