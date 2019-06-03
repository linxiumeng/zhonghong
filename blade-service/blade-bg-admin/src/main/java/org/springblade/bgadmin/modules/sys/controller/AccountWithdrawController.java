package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springblade.bgadmin.modules.sys.entity.AccountEntity;
import org.springblade.bgadmin.modules.sys.entity.AccountRechargeEntity;
import org.springblade.bgadmin.modules.sys.entity.AccountWithdrawEntity;
import org.springblade.bgadmin.modules.sys.form.AccountRechargeForm;
import org.springblade.bgadmin.modules.sys.form.AccountWithdrawForm;
import org.springblade.bgadmin.modules.sys.service.AccountService;
import org.springblade.bgadmin.modules.sys.service.AccountWithdrawService;
import org.springblade.common.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * 充值记录表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/accountwithdraw")
@Api(tags = "充值记录表", description = " * @author jinzeze")
public class AccountWithdrawController {

    @Resource
    AccountWithdrawService accountWithdrawService;

    @Resource
    AccountService accountService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
  //  @RequiresPermissions("sys:accountrecharge:list")
    public R list(@RequestBody AccountRechargeForm accountRechargeForm) {
        IPage page = new Page(accountRechargeForm.getPage(),accountRechargeForm.getSize());
        QueryWrapper<AccountRechargeEntity> wrapper = new QueryWrapper();

        if(accountRechargeForm.getStatus() != null){
            wrapper.eq("withdraw.status",accountRechargeForm.getStatus());
        }

        if(accountRechargeForm.getStartDate() != null){
            wrapper.gt("withdraw.create_date",accountRechargeForm.getStartDate());
        }

        if(accountRechargeForm.getEndDate() != null){
            wrapper.lt("withdraw.create_date",accountRechargeForm.getEndDate());
        }

        if(StringUtils.isNotBlank(accountRechargeForm.getKeywords())){
            //wrapper.andNew().eq("users.company_name",accountRechargeForm.getKeywords()).or().eq("users.mobile",accountRechargeForm.getKeywords());
            wrapper.and(i->i.eq("users.company_name",accountRechargeForm.getKeywords()).or().eq("users.mobile",accountRechargeForm.getKeywords()));
        }

        return R.ok().put("result",accountWithdrawService.listAccountWithdraw(page,wrapper));
    }


    @PostMapping("review")
    @ApiOperation(value = "检查", notes = "")
    public R reviewRecharge(@RequestBody AccountWithdrawForm accountRechargeForm){

        if(accountRechargeForm.getStatus() == null || accountRechargeForm.getId() == null){
            return R.error("参数错误");
        }

        AccountWithdrawEntity withdrawEntity = accountWithdrawService.getById(accountRechargeForm.getId());
        if(withdrawEntity != null && accountRechargeForm.getStatus() != null) {
            if (withdrawEntity.getStatus() == 1) {
                return R.ok();
            }
            withdrawEntity.setId(accountRechargeForm.getId());
            withdrawEntity.setStatus(accountRechargeForm.getStatus());
            accountWithdrawService.updateById(withdrawEntity);

            BigDecimal account = new BigDecimal(withdrawEntity.getAmount());

            AccountEntity accountEntity = accountService.getOne(Wrappers.<AccountEntity>query().eq("user_id",withdrawEntity.getUserId()));

            if(accountEntity != null){
                BigDecimal finalFreezeAmount = accountEntity.getFreezeAmount();
                finalFreezeAmount = finalFreezeAmount.subtract(account);
                accountEntity.setFreezeAmount(finalFreezeAmount);
                BigDecimal totalAccount = accountEntity.getTotal();
                if(totalAccount != null){
                    totalAccount = totalAccount.subtract(account);
                    accountEntity.setTotal(totalAccount);
                }
                accountService.updateById(accountEntity);
            }



        }



        return R.ok();
    }



}
