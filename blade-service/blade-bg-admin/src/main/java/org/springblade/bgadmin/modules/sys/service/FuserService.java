package org.springblade.bgadmin.modules.sys.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.FuserAccountEntity;
import org.springblade.bgadmin.modules.sys.entity.FuserEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 用户表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
public interface FuserService extends IService<FuserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据userid获取用户账户实体
     * @param userId
     * @return
     */
    FuserAccountEntity getUserAccountByUserId(Integer userId);

    /**
     * 条件查询列表
     * @param page
     * @param wrapper
     * @return
     */
    IPage<FuserAccountEntity> listUserAccountByWrapper(IPage page, Wrapper wrapper);

    IPage<FuserAccountEntity> listWaitReviewUserAccount(IPage page, Wrapper wrapper);

    IPage<FuserAccountEntity> listWaitCreditUserAccount(IPage page, Wrapper wrapper);
}

