package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springblade.bgadmin.modules.sys.entity.AccountRechargeEntity;
import org.springblade.bgadmin.modules.sys.entity.AccountWithdrawEntity;
import org.springblade.bgadmin.modules.sys.form.AccountRechargeForm;
import org.springblade.bgadmin.modules.sys.form.AccountWithdrawForm;
import org.springblade.bgadmin.modules.sys.service.AccountWithdrawService;
import org.springblade.common.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 充值记录表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/accountwithdraw")
public class AccountWithdrawController {

    @Resource
    AccountWithdrawService accountWithdrawService;

    /**
     * 列表
     */
    @RequestMapping("/list")
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
    public R reviewRecharge(@RequestBody AccountWithdrawForm accountRechargeForm){

        if(accountRechargeForm.getStatus() == null || accountRechargeForm.getId() == null){
            return R.error("参数错误");
        }

        AccountWithdrawEntity withdrawEntity = accountWithdrawService.getById(accountRechargeForm.getId());
        if(withdrawEntity != null) {
            if (withdrawEntity.getStatus() == 1) {
                return R.ok();
            }
            withdrawEntity.setId(accountRechargeForm.getId());
            withdrawEntity.setStatus(1);
            accountWithdrawService.updateById(withdrawEntity);
        }
        return R.ok();
    }



}
