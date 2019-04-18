package org.springblade.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.AccountDetail;
import org.springblade.pay.mapper.AccountDetailDao;
import org.springblade.pay.service.AccountDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 余额详情表(AccountDetail)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-12 11:48:32
 */
@Service("accountDetailService")
public class AccountDetailServiceImpl extends ServiceImpl<AccountDetailDao, AccountDetail> implements AccountDetailService {
    @Resource
    private AccountDetailDao accountDetailDao;
    
}