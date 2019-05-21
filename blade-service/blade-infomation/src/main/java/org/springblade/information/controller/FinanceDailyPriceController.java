package org.springblade.information.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.entity.FinanceDailyPrice;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.enums.PriceDateEnum;
import org.springblade.common.form.FinanceDailyPriceForm;
import org.springblade.common.utils.R;
import org.springblade.information.service.FinanceDailyPriceService;
import org.springblade.information.service.FinancePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
        List<FinanceDailyPrice> resultList = Collections.EMPTY_LIST;
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
