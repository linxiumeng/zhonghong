package org.springblade.bgadmin.modules.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import org.springblade.bgadmin.modules.sys.entity.AccountDetailEntity;
import org.springblade.bgadmin.modules.sys.entity.AccountDetailWithUserEntity;

import java.util.List;

/**
 * 余额详情表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountDetailDao extends BaseMapper<AccountDetailEntity> {

    List<AccountDetailWithUserEntity> selectAccountDetailWithUserList(IPage iPage, @Param("ew") Wrapper wrapper);

}
