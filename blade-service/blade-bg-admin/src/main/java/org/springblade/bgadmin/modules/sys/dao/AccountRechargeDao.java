package org.springblade.bgadmin.modules.sys.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springblade.bgadmin.modules.sys.entity.AccountRechargeEntity;

import java.util.List;

/**
 * 充值记录表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountRechargeDao extends BaseMapper<AccountRechargeEntity> {

    List<AccountRechargeEntity> selectAccountRechargeList(IPage iPage, @Param("ew") Wrapper wrapper);

}
