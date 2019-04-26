package org.springblade.bgadmin.modules.sys.dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.bgadmin.modules.sys.entity.FuserAccountEntity;
import org.springblade.bgadmin.modules.sys.entity.FuserEntity;

import java.util.List;

/**
 * 用户表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
public interface FuserDao extends BaseMapper<FuserEntity> {

    /**
     * 根据userid查询用户和账户合并的信息
     * @param userId
     * @return
     */
    FuserAccountEntity selectFuserAccountByUserId(Integer userId);

    /**
     * 根据构造的条件分页查询列表
     * @param iPage
     * @param wrapper
     * @return
     */
    List<FuserAccountEntity> selectFuserAccountByWrapper(IPage iPage, @Param("ew") Wrapper wrapper);

    /**
     * 根据构造的条件分页查询列表
     * @param iPage
     * @param wrapper
     * @return
     */
    List<FuserAccountEntity> selectWaitingReviewFuserAccountByWrapper(IPage iPage, @Param("ew") Wrapper wrapper);

    List<FuserAccountEntity> selectWaitingCreditFuserAccountByWrapper(IPage iPage, @Param("ew") Wrapper wrapper);


    FuserEntity selectByUserId(Integer id);

}
