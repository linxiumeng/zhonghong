package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentStepEntity;
import org.springblade.bgadmin.modules.sys.form.mybatis.AccountRepaymentStepDaoCondition;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 分期还款详情表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountRepaymentStepService extends IService<AccountRepaymentStepEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage listUserAccountRepaymentStep(IPage page, AccountRepaymentStepDaoCondition condition);
}

