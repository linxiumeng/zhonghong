package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.dao.AccountRepaymentDao;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentEntity;
import org.springblade.bgadmin.modules.sys.service.AccountRepaymentService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("accountRepaymentService")
public class AccountRepaymentServiceImpl extends ServiceImpl<AccountRepaymentDao, AccountRepaymentEntity> implements AccountRepaymentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountRepaymentEntity> page = this.page(
                new Query<AccountRepaymentEntity>(params).getPage(),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage listAccountRepayment(IPage page, Wrapper wrapper) {
        page.setRecords(baseMapper.selectByOrderIdWithStepList(page,wrapper));
        return page;
    }

}
