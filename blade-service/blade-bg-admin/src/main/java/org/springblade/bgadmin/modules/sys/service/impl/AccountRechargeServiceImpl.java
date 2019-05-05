package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.mapper.AccountRechargeDao;
import org.springblade.bgadmin.modules.sys.entity.AccountRechargeEntity;
import org.springblade.bgadmin.modules.sys.service.AccountRechargeService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("accountRechargeService")
public class AccountRechargeServiceImpl extends ServiceImpl<AccountRechargeDao, AccountRechargeEntity> implements AccountRechargeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountRechargeEntity> page = this.page(
                new Query<AccountRechargeEntity>(params).getPage(),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage listAccountRecharge(IPage page, Wrapper wrapper) {
       // wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectAccountRechargeList(page,wrapper));
        return page;
    }

}
