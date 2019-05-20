package org.springblade.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.AccountRepayment;
import org.springblade.common.entity.AccountRepaymentStep;
import org.springblade.pay.mapper.AccountRepaymentStepDao;
import org.springblade.pay.service.AccountRepaymentStepService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 分期还款详情表(AccountRepaymentStep)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-13 13:33:47
 */
@Service("accountRepaymentStepService")
public class AccountRepaymentStepServiceImpl extends ServiceImpl<AccountRepaymentStepDao, AccountRepaymentStep> implements AccountRepaymentStepService {
    @Resource
    private AccountRepaymentStepDao accountRepaymentStepDao;

    @Override
    public int countNotRepayment(Long repaymentId) {
        QueryWrapper<AccountRepaymentStep> accountRepaymentQueryWrapper = Wrappers.query();
        accountRepaymentQueryWrapper.eq("repayment_id",repaymentId).eq("status",0);
        return accountRepaymentStepDao.selectCount(accountRepaymentQueryWrapper);
    }
}