package org.springblade.bgadmin.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.springblade.bgadmin.modules.sys.entity.AccountEntity;

/**
 * 余额表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountDao extends BaseMapper<AccountEntity> {

    /**
     * 根据用户id查询账户实体
     * @param userId
     * @return
     */
    AccountEntity selectByUserId(Integer userId);

}
