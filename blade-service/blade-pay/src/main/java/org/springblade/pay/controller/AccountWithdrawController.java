package org.springblade.pay.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.entity.AccountDetail;
import org.springblade.common.entity.AccountWithdraw;
import org.springblade.core.tool.api.R;
import org.springblade.pay.service.AccountDetailService;
import org.springblade.pay.service.AccountWithdrawService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 余额详情表(AccountDetail)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-12 11:48:32
 */
@RestController
@RequestMapping("/AccountWithdraw")
public class AccountWithdrawController {
    @Resource
    private AccountWithdrawService accountWithdrawService;

    @PostMapping("createAccountWithdraw")
    public R save(@RequestBody AccountWithdraw accountWithdraw){
        accountWithdrawService.save(accountWithdraw);
        R r = R.status(true);
        r.setData(true);
        return r;
    }
    
}