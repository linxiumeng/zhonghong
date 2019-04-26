package org.springblade.bgadmin.modules.sys.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.AccountDetailEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 余额详情表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountDetailService extends IService<AccountDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage listAccountDetailWithUser(IPage page, Wrapper wrapper);
}

