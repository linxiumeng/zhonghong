package org.springblade.forewarduser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.common.entity.Credit;

/**
 * 授信表(Credit)表数据库访问层
 *
 * @author linxiumeng
 * @since 2019-03-07 16:15:37
 */
 @Mapper
public interface CreditDao extends BaseMapper<Credit> {

}