package org.springblade.pay.controller;

import org.springblade.common.form.FinancingWarehouseStepForm;
import org.springblade.common.utils.R;
import org.springblade.pay.service.FinancingWarehouseService;
import org.springblade.pay.service.FinancingWarehouseStepService;
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
@RequestMapping("/financingWarehouseStep")
public class FinancingWarehouseStepController {


    @Resource
    FinancingWarehouseStepService financingWarehouseStepService;

    @RequestMapping("payBack")
    public R returnMoney(@RequestBody FinancingWarehouseStepForm financingWarehouseStepForm){
        return R.ok().put("result",financingWarehouseStepService.returnMoney(financingWarehouseStepForm.getId()));
    }
    
}