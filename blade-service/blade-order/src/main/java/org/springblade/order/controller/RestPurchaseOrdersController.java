package org.springblade.order.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.PurchaseOrders;
import org.springblade.core.tool.api.R;
import org.springblade.order.service.PurchaseOrdersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hanbin
 */
@RestController
@RequestMapping("api/purchase-order")
@Api(tags = "进货订单", description = "帅泽泽")
public class RestPurchaseOrdersController {

    @Resource
    PurchaseOrdersService purchaseOrdersService;

    @GetMapping("/detail")
    @ApiOperation(value = "获取订单详情" )
    public R getDetailById(@RequestParam("id")Long id){
        R r = R.status(true);
        PurchaseOrders purchaseOrders = purchaseOrdersService.getById(id);
        if(purchaseOrders != null) {
            r.setData(purchaseOrders);
        }else{
            r.setCode(FeignResultCodeConstant.ENTITY_NOT_EXISTS);
        }

        return r;
    }


}
