package org.springblade.pay.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.AccountWithdraw;
import org.springblade.pay.mapper.AccountWithdrawDao;
import org.springblade.pay.service.AccountWithdrawService;
import org.springframework.stereotype.Service;

@Service
public class AccountWithdrawServiceImpl extends ServiceImpl<AccountWithdrawDao, AccountWithdraw> implements AccountWithdrawService {

}