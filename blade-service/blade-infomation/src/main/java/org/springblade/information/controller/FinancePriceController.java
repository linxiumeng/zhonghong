package org.springblade.information.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.entity.FinancePrice;
import org.springblade.common.form.FinancePriceForm;
import org.springblade.common.utils.R;
import org.springblade.information.service.FinancePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 泽
 */
@Api(tags = "(FinancePrice)表操作控制器", description = " * @author zeze")
@RestController
@RequestMapping("api/FinancePrice")
public class FinancePriceController {
    @Autowired
    private FinancePriceService financePriceService;

    /**
     * 根据id查询一条信息
     *
     * @param id
     * @return
     */
    @PostMapping("/listFinancePriceById")
    @ApiOperation(value="查询一条信息")
    @ApiImplicitParam(name="id",value="自增Id",dataType="Long", paramType = "query")
    public R selectFinancePriceById(@RequestParam("id") Long id){
        FinancePrice financePrice = financePriceService.getById(id);
        return R.ok().put("result",financePrice);
    }

    /**
     * 根据code分组查询最新的一条实体
     *
     * @return
     */
    @PostMapping("entityFinancePriceCode")
    @ApiOperation(value="根据code分组查询每组最新的一条实体")
    public R listFinancePriceCode(){
        List<FinancePrice> price = financePriceService.groupFinancePriceCode();
        return R.ok().put("result",price);
    }

    /*@PostMapping("listCreateTime")
    @ApiOperation(value = "查询code一天内的分时数据")
    public R listCreateTime(@RequestBody FinancePrice financePrice){
        List<FinancePrice> price = financePriceService.listCreateTime(financePrice);
        return R.ok().put("result",price);
    }*/
    /**
     * 根据Hour查询分时数据
     *
     * @return
     */
    @PostMapping("groupFinancePriceCreateHour")
    @ApiOperation(value="根据Hour查询分时数据")
    public R groupFinancePriceCreateHour(@RequestBody FinancePrice financePrice){
        List<FinancePrice> price = financePriceService.groupFinancePriceCreateHour(financePrice);
        return R.ok().put("result",price);
    }
}
