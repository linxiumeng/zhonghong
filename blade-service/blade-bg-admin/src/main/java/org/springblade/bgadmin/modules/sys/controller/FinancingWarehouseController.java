package org.springblade.bgadmin.modules.sys.controller;

import org.springblade.bgadmin.modules.sys.entity.FinancingWarehouseEntity;
import org.springblade.bgadmin.modules.sys.entity.FinancingWarehouseStepEntity;
import org.springblade.bgadmin.modules.sys.form.FinancingWarehouseForm;
import org.springblade.bgadmin.modules.sys.service.FinancingWarehouseService;
import org.springblade.bgadmin.modules.sys.service.FinancingWarehouseStepService;
import org.springblade.common.utils.CalendarUtils;
import org.springblade.common.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 *
 *
 * @author linxiumeng
 * @since 2019-03-12 11:48:32
 */
@RestController
@RequestMapping("/sys/financingWarehouse")
public class FinancingWarehouseController {

    @Resource
    FinancingWarehouseService financingWarehouseService;

    @Resource
    FinancingWarehouseStepService financingWarehouseStepService;

    @RequestMapping("/review")
    public R createFinancingWarehouse(@RequestBody FinancingWarehouseForm form) {

        if (form.getStatus() != null) {

            FinancingWarehouseEntity financingWarehouseEntity = financingWarehouseService.getById(form.getId());
            financingWarehouseEntity.setId(form.getId());
            if (form.getStatus() == 1) {
                //拒绝
                financingWarehouseEntity.setStatus(1);
                financingWarehouseService.updateById(financingWarehouseEntity);
                return R.ok();

            } else if (form.getStatus() == 2) {
                //拆分分期
                Date nowDate = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(nowDate);
                financingWarehouseEntity.setStatus(2);
                financingWarehouseEntity.setVerifyAccount(form.getVerifyAccount());
                financingWarehouseEntity.setAnnualInterestRate(form.getAnnualInterestRate());
                boolean flag = financingWarehouseService.updateById(financingWarehouseEntity);

                if(c.get(Calendar.DAY_OF_MONTH) > 27){
                    c.set(Calendar.DAY_OF_MONTH,27);
                }

                if(flag){
                    List<FinancingWarehouseStepEntity> list = new ArrayList<>();
                    int currentMonth = c.get(Calendar.MONTH);
                    int period = financingWarehouseEntity.getPeriods();
                    BigDecimal everyBaseMonthMoney = form.getVerifyAccount().divide(new BigDecimal(period),2,RoundingMode.HALF_DOWN);
                    BigDecimal everyBaseMonthInterestRate = form.getAnnualInterestRate().divide(new BigDecimal(period),3,RoundingMode.HALF_DOWN);
                    for(int i = 1 ; i <= period ; i++){
                        FinancingWarehouseStepEntity financingWarehouseStepEntity = new FinancingWarehouseStepEntity();
                        financingWarehouseStepEntity.setAccount(everyBaseMonthMoney);
                        financingWarehouseStepEntity.setFinancingId(financingWarehouseEntity.getId());
                        financingWarehouseStepEntity.setInterestRate(everyBaseMonthInterestRate);
                        financingWarehouseStepEntity.setPeriod(i);
                        financingWarehouseStepEntity.setStatus(0);
                        //处理时间
                        c.set(Calendar.MONTH,++currentMonth);
                        financingWarehouseStepEntity.setPeriodDate(c.getTime());
                        list.add(financingWarehouseStepEntity);

                    }

                    return R.ok().put("result",financingWarehouseStepService.saveBatch(list));

                }

            }

        }

        return R.ok();
    }

    @RequestMapping("/detail")
    public R showFinancingWarehouse(@RequestBody FinancingWarehouseForm form) {

        return R.ok().put("result", financingWarehouseService.getFinancingWarehouseById(form.getId()));
    }

    @RequestMapping("/list")
    public R listFinancingWarehouse(@RequestBody FinancingWarehouseForm form) {

        return R.ok().put("result", financingWarehouseService.listFinancingWarehouse(form));
    }



}