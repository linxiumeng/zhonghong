package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.dao.AccountDetailDao;
import org.springblade.bgadmin.modules.sys.entity.AccountDetailEntity;
import org.springblade.bgadmin.modules.sys.service.AccountDetailService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("accountDetailService")
public class AccountDetailServiceImpl extends ServiceImpl<AccountDetailDao, AccountDetailEntity> implements AccountDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountDetailEntity> page = this.page(
                new Query<AccountDetailEntity>(params).getPage(),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage listAccountDetailWithUser(IPage page, Wrapper wrapper) {
        page.setRecords(baseMapper.selectAccountDetailWithUserList(page,wrapper));
        return page;
    }

}
