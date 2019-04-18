package org.springblade.pay.controller;

import org.springblade.pay.mapper.AccountRepaymentDao;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 分歧还款表(TbAccountRepayment)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-02-18 11:59:29
 */
@RestController
@RequestMapping("/b")
public class AccountRepaymentController {
    @Resource
    private AccountRepaymentDao accountRepaymentDao;

}