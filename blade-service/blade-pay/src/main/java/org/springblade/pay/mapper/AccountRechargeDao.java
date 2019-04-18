package org.springblade.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.common.entity.AccountRecharge;

/**
 * 充值记录表(AccountRecharge)表数据库访问层
 *
 * @author linxiumeng
 * @since 2019-02-18 11:51:09
 */
 @Mapper
public interface AccountRechargeDao  extends BaseMapper<AccountRecharge> {

}