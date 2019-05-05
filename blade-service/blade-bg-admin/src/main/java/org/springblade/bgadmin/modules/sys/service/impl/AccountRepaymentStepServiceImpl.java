package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.mapper.AccountRepaymentStepDao;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentStepEntity;
import org.springblade.bgadmin.modules.sys.entity.UserAccountRepaymentStepEntity;
import org.springblade.bgadmin.modules.sys.form.mybatis.AccountRepaymentStepDaoCondition;
import org.springblade.bgadmin.modules.sys.service.AccountRepaymentStepService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("accountRepaymentStepService")
public class AccountRepaymentStepServiceImpl extends ServiceImpl<AccountRepaymentStepDao, AccountRepaymentStepEntity> implements AccountRepaymentStepService {

    @Resource
    AccountRepaymentStepDao accountRepaymentStepDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountRepaymentStepEntity> page = this.page(
                new Query<AccountRepaymentStepEntity>(params).getPage(),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage listUserAccountRepaymentStep(IPage page, AccountRepaymentStepDaoCondition condition) {
        List<UserAccountRepaymentStepEntity> list = accountRepaymentStepDao.selectUserAccountRepaymentStepsList(page, condition);
        page.setRecords(list);
        return page;
    }

}
