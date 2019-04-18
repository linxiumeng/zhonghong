package org.springblade.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.common.entity.AccountDetail;

/**
 * 余额详情表(AccountDetail)表数据库访问层
 *
 * @author linxiumeng
 * @since 2019-03-12 11:48:32
 */
 @Mapper
public interface AccountDetailDao extends BaseMapper<AccountDetail> {

}