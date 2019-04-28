package org.springblade.bgadmin.modules.sys.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.bgadmin.modules.sys.entity.AccountWithdrawEntity;

import java.util.List;

/**
 * 充值记录表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountWithdrawDao extends BaseMapper<AccountWithdrawEntity> {

    List<AccountWithdrawEntity> selectAccountWithdrawList(IPage iPage, @Param("ew") Wrapper wrapper);

}
