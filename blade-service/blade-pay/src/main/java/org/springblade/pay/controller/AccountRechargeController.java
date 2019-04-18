package org.springblade.pay.controller;

import org.springblade.pay.mapper.AccountRechargeDao;
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
@RequestMapping("/a")
public class AccountRechargeController {
    @Resource
    private AccountRechargeDao accountRechargeDao;
    

}