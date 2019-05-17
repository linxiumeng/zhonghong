package org.springblade.information.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.entity.FinancePrice;
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
    @PostMapping("/listFinancePriceById")
    @ApiOperation(value="查询一条信息")
    @ApiImplicitParam(name="id",value="自增Id",dataType="Long", paramType = "query")
    public R selectFinancePriceById(@RequestParam("id") Long id){
        FinancePrice financePrice = financePriceService.getById(id);
        return R.ok().put("result",financePrice);
    }
    @PostMapping("entityFinancePriceCode")
    @ApiOperation(value="根据code查询最新的一条实体")
    public R listFinancePriceCode(@RequestParam("code" ) String code){
        FinancePrice price = financePriceService.selectFinancePriceCode(code);
        return R.ok().put("result",price);
    }

}
