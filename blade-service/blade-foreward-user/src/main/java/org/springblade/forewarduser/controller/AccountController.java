package org.springblade.forewarduser.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.*;
import org.springblade.common.exception.RRException;
import org.springblade.common.form.AccountExtractForm;
import org.springblade.common.form.AccountFinancingPayForm;
import org.springblade.common.form.AccountPayForm;
import org.springblade.common.form.AccountRechargeForm;
import org.springblade.common.respond.AccountDto;
import org.springblade.common.utils.R;
import org.springblade.common.validation.group.InsertGroup;
import org.springblade.forewarduser.feign.AccountRechargeServiceFeign;
import org.springblade.forewarduser.feign.AccountWithdrawServiceFeign;
import org.springblade.forewarduser.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author linxiumeng
 * @since 2019-02-14 09:51:31
 */
@Api(tags = "余额表操作控制器", description = " * @author linxiumeng")
@RestController
@RequestMapping("api/user/account")
public class AccountController {
    private AccountService accountService;

    @Resource
    private AccountWithdrawServiceFeign accountWithdrawService;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Resource
    private AccountRechargeServiceFeign accountRechargeService;

    @PostMapping("query")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "query", dataType = "integer")
    })*/
    @ApiOperation(value = "查询余额", notes = "传入token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
  /*  @ApiOperation("查询余额")*/
    @Login
    public R query(@LoginUser UserEntity user) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getUserId());
        Account account = accountService.getOne(wrapper);
        AccountDto accountDto = null;
        if (account != null) {
            accountDto = new AccountDto();
            BeanUtils.copyProperties(account, accountDto);
            //  beanCopier.copy(account,accountDto,null);
        }
        return R.ok().put("result", accountDto);
    }

    @PostMapping("recharge")
   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "query", dataType = "integer")
    })*/
    @ApiOperation(value = "充值余额", notes = "传入token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
    /*@ApiOperation("充值余额")*/
    @Login
    public R recharge(@RequestBody @Validated(InsertGroup.class) AccountRechargeForm form, @LoginUser UserEntity user) {
        AccountRecharge accountRecharge = new AccountRecharge();
        accountRecharge.setUserId(user.getUserId());
        BeanUtils.copyProperties(form, accountRecharge);
        accountRecharge.setType(0);

        org.springblade.core.tool.api.R r = accountRechargeService.save(accountRecharge);
        if(r.getCode() == FeignResultCodeConstant.EXCEPTION_CODE){
            throw new RRException(r.getMsg());
        }
        return R.ok();
    }

    @PostMapping("extract")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "query", dataType = "integer")
    })*/
    @ApiOperation(value = "余额提现", notes = "传入token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
    /*@ApiOperation("余额提现")*/
    @Login
    public R extract(@RequestBody @Validated AccountExtractForm form, @LoginUser UserEntity user) {

        String password = DigestUtils.sha256Hex(form.getPassword());

        if (!password.equals(user.getPassword())) {
            return R.error("密码错误");
        }


        BigDecimal amount = new BigDecimal(String.valueOf(form.getAccount()));

        AccountWithdraw accountWithdraw = new AccountWithdraw();
        accountWithdraw.setAmount(String.valueOf(form.getAccount()));
        accountWithdraw.setWithdrawDate(new Date());
        accountWithdraw.setUserId(user.getUserId().intValue());
        accountWithdraw.setStatus(0);

        Account account = accountService.getOne(Wrappers.<Account>query().eq("user_id",user.getUserId()));

        if(account != null){
            BigDecimal finalFreezeAmount = account.getFreezeAmount();
            BigDecimal canUseAccount = account.getAccount();
            canUseAccount = canUseAccount.subtract(amount);
            if(finalFreezeAmount != null && canUseAccount != null) {
                finalFreezeAmount = finalFreezeAmount.add(amount);
                finalFreezeAmount = finalFreezeAmount.setScale(2);
                account.setFreezeAmount(finalFreezeAmount);
                canUseAccount = canUseAccount.setScale(2);
                account.setAccount(canUseAccount);
                accountService.updateById(account);
            }

        }

        //todo 做校验
        accountWithdrawService.save(accountWithdraw);

        return R.ok();
    }


    @GetMapping("test")
    public R testFeign(){
        PurchaseOrders order = new PurchaseOrders();
        order.setFinalQuotation("111.00");
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(23L);
        accountService.pay(order,userEntity);
        return R.ok();
    }

    /**
     * 支付接口
     * @param accountPayForm
     * @return
     */
    @PostMapping("pay")
    @ApiOperation(value = "付款", notes = "")
    public org.springblade.core.tool.api.R pay(@RequestBody AccountPayForm accountPayForm){
        org.springblade.core.tool.api.R r = org.springblade.core.tool.api.R.status(true);
        try {
            boolean flag = accountService.pay(accountPayForm.getPurchaseOrders(), accountPayForm.getUser());
            if(!flag){
                throw new RRException("付款失败");
            }
        }catch (RRException e){
            r.setCode(FeignResultCodeConstant.EXCEPTION_CODE);
            r.setMsg(e.getMessage());
        }
        return r;
    }

    /**
     * 融资付款
     * @param accountFinancingPayForm
     * @return
     */
    @PostMapping("financingPay")
    @ApiOperation(value = "融资付款", notes = "")
    public org.springblade.core.tool.api.R financingPay(@RequestBody AccountFinancingPayForm accountFinancingPayForm){
        org.springblade.core.tool.api.R r = org.springblade.core.tool.api.R.status(true);
        try {

            boolean flag = accountService.financingPay(accountFinancingPayForm.getPurchaseOrders(), accountFinancingPayForm.getUser(),accountFinancingPayForm.getPayForm());
            if(!flag){
                throw new RRException("融资付款失败");
            }
        }catch (RRException e){
            r.setCode(FeignResultCodeConstant.EXCEPTION_CODE);
            r.setMsg(e.getMessage());
        }
        return r;
    }

    /**
     * 获取用户实体
     * @param userId
     * @return
     */
    @GetMapping("getByUserId")
    @ApiOperation(value = "获取用户实体", notes = "")
    public org.springblade.core.tool.api.R getAccountByUserId(@RequestParam("userId")Long userId){
        org.springblade.core.tool.api.R r = org.springblade.core.tool.api.R.status(true);
        Wrapper wrapper = new QueryWrapper<>().eq("user_id",userId);
        Account account = accountService.getOne(wrapper);
        if(account != null){
            r.setData(account);
        } else{
            r.setCode(FeignResultCodeConstant.ENTITY_NOT_EXISTS);
        }

        return r;
    }

    /**
     * 修改意向
     * @param account
     * @return
     */
    @GetMapping("updateBySelective")
    @ApiOperation(value = "修改意向", notes = "")
    public org.springblade.core.tool.api.R getAccountByUserId(@RequestBody Account account){
        org.springblade.core.tool.api.R r = org.springblade.core.tool.api.R.status(true);
        r.setData(accountService.updateById(account));
        return r;
    }

}