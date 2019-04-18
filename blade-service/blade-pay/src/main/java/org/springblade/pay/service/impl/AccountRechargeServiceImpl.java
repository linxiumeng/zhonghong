package org.springblade.pay.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.AccountRecharge;
import org.springblade.pay.mapper.AccountRechargeDao;
import org.springblade.pay.service.AccountRechargeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 充值记录表(TbAccountRecharge)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-02-18 11:51:09
 */
@Service("accountRechargeService")
public class AccountRechargeServiceImpl extends ServiceImpl<AccountRechargeDao, AccountRecharge> implements AccountRechargeService {
    @Resource
    private AccountRechargeDao accountRechargeDao;
    

}