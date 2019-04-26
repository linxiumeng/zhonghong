package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.finepetro.common.utils.R;
import io.finepetro.modules.sys.entity.AccountWithdrawEntity;
import io.finepetro.modules.sys.form.AccountRechargeForm;
import io.finepetro.modules.sys.form.AccountWithdrawForm;
import io.finepetro.modules.sys.service.AccountWithdrawService;
import org.apache.commons.lang.StringUtils;
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
        Page page = new Page(accountRechargeForm.getPage(),accountRechargeForm.getSize());
        EntityWrapper wrapper = new EntityWrapper();

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
            wrapper.andNew().eq("users.company_name",accountRechargeForm.getKeywords()).or().eq("users.mobile",accountRechargeForm.getKeywords());
        }

        return R.ok().put("result",accountWithdrawService.listAccountWithdraw(page,wrapper));
    }


    @PostMapping("review")
    public R reviewRecharge(@RequestBody AccountWithdrawForm accountRechargeForm){

        if(accountRechargeForm.getStatus() == null || accountRechargeForm.getId() == null){
            return R.error("参数错误");
        }

        AccountWithdrawEntity withdrawEntity = accountWithdrawService.selectById(accountRechargeForm.getId());
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
