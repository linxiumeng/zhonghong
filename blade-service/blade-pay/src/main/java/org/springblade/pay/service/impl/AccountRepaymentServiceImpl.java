package org.springblade.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.AccountRepayment;
import org.springblade.pay.mapper.AccountRepaymentDao;
import org.springblade.pay.service.AccountRepaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 分歧还款表(TbAccountRepayment)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-02-18 11:59:29
 */
@Service("accountRepaymentService")
public class AccountRepaymentServiceImpl extends ServiceImpl<AccountRepaymentDao, AccountRepayment> implements AccountRepaymentService {
    @Resource
    private AccountRepaymentDao accountRepaymentDao;

    @Override
    public int insertWithId(AccountRepayment accountRepayment) {
        return accountRepaymentDao.insertWithId(accountRepayment);
    }


}