package org.springblade.pay.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.AccountRepaymentStep;
import org.springblade.core.tool.api.R;
import org.springblade.pay.mapper.AccountRepaymentStepDao;
import org.springblade.pay.service.AccountRepaymentStepService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 分期还款详情表(AccountRepaymentStep)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-13 13:33:47
 */
@RestController
@RequestMapping("/api/accountRepaymentStep")
public class AccountRepaymentStepController {
    @Resource
    private AccountRepaymentStepService accountRepaymentStepService;

    @PostMapping("/batchSave")
    public R batchSave(@RequestBody List<AccountRepaymentStep> accountRepaymentStepList){
        R r = R.status(true);
        accountRepaymentStepService.saveBatch(accountRepaymentStepList);
        return r;
    }
    
}