package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 分期还款表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountRepaymentService extends IService<AccountRepaymentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 展示列表
     * @param page
     * @param wrapper
     * @return
     */
    Page listAccountRepayment(Page page, Wrapper wrapper);

}

