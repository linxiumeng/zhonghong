package org.springblade.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.common.entity.AccountRepaymentStep;

/**
 * 分期还款详情表(AccountRepaymentStep)表数据库访问层
 *
 * @author linxiumeng
 * @since 2019-03-13 13:33:47
 */
@Mapper
public interface AccountRepaymentStepDao extends BaseMapper<AccountRepaymentStep> {

}