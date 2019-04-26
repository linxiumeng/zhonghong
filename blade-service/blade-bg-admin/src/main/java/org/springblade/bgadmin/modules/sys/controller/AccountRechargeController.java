package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.entity.AccountRechargeEntity;
import io.finepetro.modules.sys.form.AccountRechargeForm;
import io.finepetro.modules.sys.service.AccountRechargeService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;


/**
 * 充值记录表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/accountrecharge")
public class AccountRechargeController {
    @Autowired
    private AccountRechargeService accountRechargeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
  //  @RequiresPermissions("sys:accountrecharge:list")
    public R list(@RequestBody AccountRechargeForm accountRechargeForm) {
        Page page = new Page(accountRechargeForm.getPage(),accountRechargeForm.getSize());
        EntityWrapper wrapper = new EntityWrapper();

        if(accountRechargeForm.getStatus() != null){
            wrapper.eq("recharge.status",accountRechargeForm.getStatus());
        }

        if(accountRechargeForm.getStartDate() != null){
            wrapper.gt("recharge.create_date",accountRechargeForm.getStartDate());
        }

        if(accountRechargeForm.getEndDate() != null){
            wrapper.lt("recharge.create_date",accountRechargeForm.getEndDate());
        }

        if(StringUtils.isNotBlank(accountRechargeForm.getKeywords())){
            wrapper.andNew().eq("users.company_name",accountRechargeForm.getKeywords()).or().eq("users.mobile",accountRechargeForm.getKeywords());
        }

        return R.ok().put("result",accountRechargeService.listAccountRecharge(page,wrapper));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:accountrecharge:info")
    public R info(@PathVariable("id") Integer id) {
            AccountRechargeEntity accountRecharge = accountRechargeService.selectById(id);

        return R.ok().put("accountRecharge", accountRecharge);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:accountrecharge:save")
    public R save(@RequestBody AccountRechargeEntity accountRecharge) {
            accountRechargeService.insert(accountRecharge);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:accountrecharge:update")
    public R update(@RequestBody AccountRechargeEntity accountRecharge) {
        ValidatorUtils.validateEntity(accountRecharge);
            accountRechargeService.updateAllColumnById(accountRecharge);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:accountrecharge:delete")
    public R delete(@RequestBody Integer[] ids) {
        accountRechargeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    @PostMapping("review")
    public R reviewRecharge(@RequestBody AccountRechargeForm accountRechargeForm){

        if(accountRechargeForm.getStatus() == null || accountRechargeForm.getId() == null){
            return R.error("参数错误");
        }

        AccountRechargeEntity rechargeEntity = accountRechargeService.selectById(accountRechargeForm.getId());
        if(rechargeEntity != null) {
            if (rechargeEntity.getStatus() == 1) {
                return R.ok();
            }
            rechargeEntity.setId(accountRechargeForm.getId());
            rechargeEntity.setAccount(new BigDecimal(accountRechargeForm.getRechargeAmount()));
            rechargeEntity.setStatus(1);
            accountRechargeService.updateById(rechargeEntity);
        }
        return R.ok();
    }


}
