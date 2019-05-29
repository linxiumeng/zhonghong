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
import org.springblade.bgadmin.modules.sys.form.AccountRechargeForm;
import org.springblade.bgadmin.modules.sys.service.AccountRechargeService;
import org.springblade.bgadmin.modules.sys.service.AccountService;
import org.springblade.common.utils.R;
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
@Api(tags = "充值记录表", description = " * @author jinzeze")
public class AccountRechargeController {
    @Autowired
    private AccountRechargeService accountRechargeService;

    @Autowired
    private AccountService accountService;

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
            wrapper.eq("recharge.status",accountRechargeForm.getStatus());
        }

        if(accountRechargeForm.getStartDate() != null){
            wrapper.gt("recharge.create_date",accountRechargeForm.getStartDate());
        }

        if(accountRechargeForm.getEndDate() != null){
            wrapper.lt("recharge.create_date",accountRechargeForm.getEndDate());
        }

        if(StringUtils.isNotBlank(accountRechargeForm.getKeywords())){
            //wrapper.andNew().eq("users.company_name",accountRechargeForm.getKeywords()).or().eq("users.mobile",accountRechargeForm.getKeywords());
            wrapper.and(i->i.eq("users.company_name",accountRechargeForm.getKeywords()).or().eq("users.mobile",accountRechargeForm.getKeywords()));
        }

        return R.ok().put("result",accountRechargeService.listAccountRecharge(page,wrapper));
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "")
    //@RequiresPermissions("sys:accountrecharge:info")
    public R info(@PathVariable("id") Integer id) {
            AccountRechargeEntity accountRecharge = accountRechargeService.getById(id);

        return R.ok().put("accountRecharge", accountRecharge);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
    //@RequiresPermissions("sys:accountrecharge:save")
    public R save(@RequestBody AccountRechargeEntity accountRecharge) {
            accountRechargeService.save(accountRecharge);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:accountrecharge:update")
    public R update(@RequestBody AccountRechargeEntity accountRecharge) {
        //ValidatorUtils.validateEntity(accountRecharge);
            accountRechargeService.updateById(accountRecharge);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:accountrecharge:delete")
    public R delete(@RequestBody Integer[] ids) {
        accountRechargeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    @PostMapping("review")
    @ApiOperation(value = "检验", notes = "")
    public R reviewRecharge(@RequestBody AccountRechargeForm accountRechargeForm){

        if(accountRechargeForm.getStatus() == null || accountRechargeForm.getId() == null){
            return R.error("参数错误");
        }

        AccountRechargeEntity rechargeEntity = accountRechargeService.getById(accountRechargeForm.getId());
        if(rechargeEntity != null) {
            if (rechargeEntity.getStatus() == 1) {
                return R.ok();
            }
            BigDecimal accountDecimal = new BigDecimal(accountRechargeForm.getRechargeAmount());
            rechargeEntity.setId(accountRechargeForm.getId());
            rechargeEntity.setAccount(accountDecimal);
            rechargeEntity.setStatus(1);
            accountRechargeService.updateById(rechargeEntity);

            AccountEntity accountEntity = accountService.getOne(Wrappers.<AccountEntity>query().eq("user_id",rechargeEntity.getUserId()));
            if(accountEntity != null){
                BigDecimal bigDecimal = accountEntity.getAccount();
                bigDecimal = bigDecimal.add(accountDecimal);
                accountEntity.setAccount(bigDecimal);
                accountService.updateById(accountEntity);
            }
        }
        return R.ok();
    }


}
