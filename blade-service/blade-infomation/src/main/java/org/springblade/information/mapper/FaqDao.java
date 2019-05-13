package org.springblade.information.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.common.entity.Faq;

/**
 * @author 常见问题操作层
 */
@Mapper
public interface FaqDao extends BaseMapper<Faq> {
}
