package org.springblade.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    
}