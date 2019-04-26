package org.springblade.bgadmin.modules.sys.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import org.springblade.bgadmin.modules.sys.entity.AccountRechargeEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 充值记录表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountRechargeService extends IService<AccountRechargeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage listAccountRecharge(IPage page, Wrapper wrapper);
}

