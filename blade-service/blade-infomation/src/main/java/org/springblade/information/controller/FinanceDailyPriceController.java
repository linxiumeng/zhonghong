package org.springblade.information.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.form.FinanceDailyPriceForm;
import org.springblade.common.utils.R;
import org.springblade.information.service.FinanceDailyPriceService;
import org.springblade.information.service.FinancePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 泽
 */
@Api(tags = "(FinancePrice)表操作控制器", description = " * @author zeze")
@RestController
@RequestMapping("api/financeDailyPrice")
public class FinanceDailyPriceController {

    @Autowired
    private FinanceDailyPriceService financeDailyPriceService;


    @PostMapping("/dailyList")
    @ApiOperation(value="列表")
    public R dayList(@RequestBody FinanceDailyPriceForm financeDailyPriceForm){




        return R.ok();
    }


    @PostMapping("/weekList")
    @ApiOperation(value="列表")
    public R weekList(@RequestBody FinanceDailyPriceForm financeDailyPriceForm){




        return R.ok();
    }


    @PostMapping("/monthList")
    @ApiOperation(value="列表")
    public R monthList(@RequestBody FinanceDailyPriceForm financeDailyPriceForm){




        return R.ok();
    }

}
