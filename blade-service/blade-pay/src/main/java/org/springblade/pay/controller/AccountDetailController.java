package org.springblade.pay.controller;

import org.springblade.common.entity.AccountDetail;
import org.springblade.core.tool.api.R;
import org.springblade.pay.mapper.AccountDetailDao;
import org.springblade.pay.service.AccountDetailService;
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
@RequestMapping("/accountDetail")
public class AccountDetailController {
    @Resource
    private AccountDetailService accountDetailService;

    @PostMapping("saveAccountDetail")
    public R save(@RequestBody AccountDetail accountDetail){
        accountDetailService.save(accountDetail);
        R r = R.status(true);
        r.setData(true);
        return r;
    }
    
}