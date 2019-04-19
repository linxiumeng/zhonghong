package org.springblade.forewarduser.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.*;
import org.springblade.common.form.PayForm;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.forewarduser.feign.AccountDetailServiceFeign;
import org.springblade.forewarduser.feign.AccountRechargeServiceFeign;
import org.springblade.forewarduser.feign.AccountRepaymentServiceFeign;
import org.springblade.forewarduser.feign.AccountRepaymentStepServiceFeign;
import org.springblade.forewarduser.mapper.AccountDao;
import org.springblade.forewarduser.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 商品表(TbGoods)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-02-13 15:23:01
 */
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {


    @Resource
    AccountDetailServiceFeign accountDetailService;

    @Resource
    AccountRepaymentServiceFeign accountRepaymentService;

    @Resource
    AccountRepaymentStepServiceFeign accountRepaymentStepService;



    @Override
    public boolean pay(PurchaseOrders param, UserEntity user) {
        BigDecimal bd = new BigDecimal(param.getFinalQuotation());

        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getUserId());
        Account account = this.getOne(wrapper);
        /**
         * @TODO缺少校验
         * */
        AccountDetail ad = new AccountDetail();
        ad.setTotal(account.getTotal());
        ad.setAccount(bd);
        ad.setUserId(user.getUserId());
        accountDetailService.save(ad);

        account.setFreezeAmount(account.getFreezeAmount().add(bd));
        account.setAccount(account.getAccount().subtract(bd));
        updateById(account);


        return true;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean financingPay(PurchaseOrders param, UserEntity user, PayForm param1) {
        BigDecimal bd = new BigDecimal(param.getFinalQuotation());
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getUserId());
        Account account = getOne(wrapper);
        /**
         * @TODO缺少校验
         * */
        AccountRepayment ar = new AccountRepayment();
        ar.setUserId(user.getUserId());
        ar.setOrderId(param.getId());
        ar.setTotalAmount(param1.getFinancing());
        ar.setWaitAmount(param1.getFinancing());
        ar.setPaidAmount(new BigDecimal(0.0));
        ar.setCurrentPeriod(1);
        BigDecimal totalInterest = param1.getFinancing().multiply(new BigDecimal(0.1));
        ar.setTotalInterest(totalInterest);
        ar.setWaitAmount(totalInterest);
        ar.setPaidInterest(new BigDecimal(0.0));
        ar.setPeriods(param1.getStages());
        ar.setRecentRepaymentDate(new Date());
        accountRepaymentService.saveWithId(ar);
        account.setCreditLimit(account.getCreditLimit().subtract(bd));
        updateById(account);

        BigDecimal divide1 = totalInterest.divide(new BigDecimal(12), 3, BigDecimal.ROUND_HALF_UP);
        BigDecimal divide = param1.getFinancing().divide(new BigDecimal(12), 3, BigDecimal.ROUND_HALF_UP);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        List<AccountRepaymentStep> insertList = new LinkedList<>();
        for (int i = 1; i <= param1.getStages(); i++) {
            AccountRepaymentStep accountRepaymentStep = new AccountRepaymentStep();
            accountRepaymentStep.setRepaymentId(ar.getId());
            accountRepaymentStep.setAccount(divide);
            accountRepaymentStep.setInterest(divide1);
            accountRepaymentStep.setPreiod(i);
            accountRepaymentStep.setPreiodDate(cal.getTime());
            cal.add(Calendar.MONTH, 1);//增加一个月
            insertList.add(accountRepaymentStep);
        }

        return accountRepaymentStepService.batchSave(insertList);

    }
}