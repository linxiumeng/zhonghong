package org.springblade.pay.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.*;
import org.springblade.common.exception.RRException;
import org.springblade.common.utils.R;
import org.springblade.pay.feign.AccountServiceFeign;
import org.springblade.pay.feign.PurchaseOrdersServiceFeign;
import org.springblade.pay.service.AccountRepaymentService;
import org.springblade.pay.service.AccountRepaymentStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author linxiumeng
 * @since 2019-02-18 11:59:29
 */
@Api(tags = "分期还款表(TbAccountRepayment)表操作控制器", description = " * @author linxiumeng")
@RestController
@RequestMapping("/api/AccountRepayment")
public class AccountRepaymentController {
    private AccountRepaymentService accountRepaymentService;
    @Autowired
    private AccountServiceFeign accountService;

    @Autowired
    private AccountRepaymentStepService accountRepaymentStepService;

    @Autowired
    private PurchaseOrdersServiceFeign purchaseOrdersService;

    @Autowired
    public AccountRepaymentController(AccountRepaymentService accountRepaymentService) {
        this.accountRepaymentService = accountRepaymentService;
    }


    @PostMapping("getRepaymentbyorder")
    @ApiOperation("根据订单id查询分期")
    @Login
    public R recharge(@RequestBody AccountRepayment param, @LoginUser UserEntity user) {
     //   PurchaseOrders order = purchaseOrdersService.getPurchaseOrdersDetail(param.getOrderId().longValue()).getData();

        org.springblade.core.tool.api.R<PurchaseOrders> r = purchaseOrdersService.getPurchaseOrdersDetail(param.getOrderId().longValue());

        PurchaseOrders order = null;

        if(r.getCode() == FeignResultCodeConstant.ENTITY_NOT_EXISTS){
            order = null;
            throw new RRException("订单不存在，请找管理员确认");
        }else{
            order = r.getData();
        }

        //要确认是否本人订单
        if(order.getBuyerId().intValue() != user.getUserId().intValue()){
            return R.error("您无法查看别人的分期详情");
        }

        QueryWrapper<AccountRepayment> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", order.getId());
        //查询分期情况
        AccountRepayment accountRepayment = accountRepaymentService.getOne(wrapper);

        List<AccountRepaymentStep> accountRepaymentSteps = null;

        if(accountRepayment != null) {

            //查询具体分期
            QueryWrapper<AccountRepaymentStep> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("repayment_id", accountRepayment.getId());
            accountRepaymentSteps = accountRepaymentStepService.list(wrapper1);
        }
        return R.ok().put("result", accountRepaymentSteps).put("AccountRepayment", accountRepayment);
    }

    @PostMapping("payRepayment")
    @ApiOperation("还款每期")
    @Login
    @Transactional(rollbackFor = Exception.class)
    public R payRepayment(@RequestBody AccountRepaymentStep param, @LoginUser UserEntity user) {

      //  Account account = accountService.getAccountByUserId(user.getUserId()).getData();
        org.springblade.core.tool.api.R<Account> r = accountService.getAccountByUserId(user.getUserId());
        if(r.getCode() == FeignResultCodeConstant.ENTITY_NOT_EXISTS){
            throw new RRException("账户为空，请联系管理员");
        }
        Account account = r.getData();
        //todo 要确认是否本人订单

        //扣款改本期还款状态
        AccountRepaymentStep accountRepaymentStep = accountRepaymentStepService.getById(param.getId());
        //校验一下 防止出现负数
        BigDecimal afterCaoZuo = account.getAccount().subtract(accountRepaymentStep.getAccount());
        if(afterCaoZuo.signum() == -1){
            return R.error("还款失败，账户余额不足");
        }
        account.setAccount(afterCaoZuo);
        accountRepaymentStep.setStatus(1);
        accountService.updateAccountById(account);
        accountRepaymentStepService.updateById(accountRepaymentStep);
        return R.ok();
    }

}