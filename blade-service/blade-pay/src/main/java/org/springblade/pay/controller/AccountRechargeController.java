package org.springblade.pay.controller;

import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.AccountRecharge;
import org.springblade.core.tool.api.R;
import org.springblade.pay.mapper.AccountRechargeDao;
import org.springblade.pay.service.AccountRechargeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 充值记录表(TbAccountRecharge)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-02-18 11:51:09
 */
@RestController
@RequestMapping("/api/accountRecharge")
public class AccountRechargeController {
    @Resource
    private AccountRechargeService accountRechargeService;
    

    @PostMapping("insert")
    public R save(@RequestBody AccountRecharge accountRecharge){
        R r = R.status(true);
        boolean flag = accountRechargeService.save(accountRecharge);
        if(flag){
            return r;
        }else{
            r.setCode(FeignResultCodeConstant.EXCEPTION_CODE);
            r.setMsg("充值失败");
            return r;
        }

    }
}