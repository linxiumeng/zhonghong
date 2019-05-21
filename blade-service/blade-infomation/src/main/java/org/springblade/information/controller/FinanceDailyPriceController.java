package org.springblade.information.controller;


import io.swagger.annotations.ApiOperation;
import org.springblade.common.entity.FinanceDailyPrice;
import org.springblade.common.enums.PriceDateEnum;
import org.springblade.common.form.FinanceDailyPriceForm;
import org.springblade.common.utils.R;
import org.springblade.information.service.FinanceDailyPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 泽
 */
@RestController
@RequestMapping("api/financeDailyPrice")
public class FinanceDailyPriceController {

    @Autowired
    private FinanceDailyPriceService financeDailyPriceService;


    @PostMapping("/list")
    @ApiOperation(value="列表")
    public R dayList(@RequestBody FinanceDailyPriceForm financeDailyPriceForm){

        PriceDateEnum type = financeDailyPriceForm.getType();
        List<FinanceDailyPrice> resultList = null;
        if(type == null){
            type = PriceDateEnum.DAY;
        }

        switch (type){
            case DAY:
                resultList = financeDailyPriceService.listDayFinancePrice(financeDailyPriceForm);
                break;
            case WEEK:
                resultList = financeDailyPriceService.listWeekFinancePrice(financeDailyPriceForm);
                break;
            case MONTH:
                resultList = financeDailyPriceService.listMonthFinancePrice(financeDailyPriceForm);
                break;
            case YEAR:
                resultList = financeDailyPriceService.listYearFinancePrice(financeDailyPriceForm);
                break;
            default:
        }

        return R.ok().put("result",resultList);
    }


}
