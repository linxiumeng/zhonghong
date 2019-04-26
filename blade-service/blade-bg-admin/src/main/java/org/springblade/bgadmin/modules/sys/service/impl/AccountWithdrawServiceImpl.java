package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.modules.sys.dao.AccountWithdrawDao;
import org.springblade.bgadmin.modules.sys.entity.AccountWithdrawEntity;
import org.springblade.bgadmin.modules.sys.service.AccountWithdrawService;
import org.springframework.stereotype.Service;


@Service("accountWithdrawService")
public class AccountWithdrawServiceImpl extends ServiceImpl<AccountWithdrawDao, AccountWithdrawEntity> implements AccountWithdrawService {


    @Override
    public IPage listAccountWithdraw(IPage page, Wrapper wrapper) {
      //  wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectAccountWithdrawList(page,wrapper));
        return page;
    }

}
