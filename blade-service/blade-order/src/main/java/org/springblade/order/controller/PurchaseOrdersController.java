package org.springblade.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.PurchaseOrders;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.enums.OrdersEnum;
import org.springblade.common.exception.RRException;
import org.springblade.common.form.AccountFinancingPayForm;
import org.springblade.common.form.AccountPayForm;
import org.springblade.common.form.PageForm;
import org.springblade.common.form.PayForm;
import org.springblade.common.utils.R;
import org.springblade.order.feign.AccountDetailServiceFeign;
import org.springblade.order.feign.AccountServiceFeign;
import org.springblade.order.service.PurchaseOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author linxiumeng
 * @since 2019-03-07 17:27:06
 */
@Api(tags = "(PurchaseOrders)表操作控制器", description = " * @author linxiumeng")
@RestController
@RequestMapping("api/purchaseOrders")
public class PurchaseOrdersController {

    @Resource
    private PurchaseOrdersService purchaseOrdersService;


    @ApiOperation(value = "商品下单接口")
    @PostMapping("insert")
    @Login
    @Transactional(rollbackFor = Exception.class)
    public R insert(@RequestBody PurchaseOrders param, @LoginUser UserEntity user) {
        //直接减库存 生成订单
        return R.ok().put("row", purchaseOrdersService.putPurchaseOrderByGoods(user, param));
    }

    @ApiOperation(value = "商品生成采购单接口 ， 仅前端展示用")
    @PostMapping("create")
    @Login
    public R create(@RequestBody PurchaseOrders param, @LoginUser UserEntity user) {
        PurchaseOrders result = purchaseOrdersService.generatePurchaseOrderModelByGoods(user, param);
        return R.ok().put("row", result);
    }

    /***************************需求单   个人认为是报价单 下单接口*/
    @ApiOperation(value = "需求单下单接口")
    @PostMapping("demandinsert")
    @Login
    public R demandinsert(@RequestBody PurchaseOrders param, @LoginUser UserEntity user) {
        boolean flag = purchaseOrdersService.putPurchaseOrderByQuotation(user, param);
        return R.ok().put("row", flag);
    }

    @ApiOperation(value = "需求单生成采购单接口")
    @PostMapping("demandcreate")
    @Login
    public R demandcreate(@RequestBody PurchaseOrders param, @LoginUser UserEntity user) {

        return R.ok().put("row", purchaseOrdersService.generatePurchaseOrderModelByQuotation(user, param));
    }

    /***********************供应商确认采购单生成订单接口*/
    @ApiOperation(value = "供应商确认采购单接口")
    @PostMapping("/provider/createorder")
    @Login
    public R createOrder(@RequestBody PurchaseOrders param, @LoginUser UserEntity user) {
        boolean flag = purchaseOrdersService.confirmPurchaseOrder(param, user.getUserId());
        if (flag) {
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "供应商发送订单金额接口")
    @PostMapping("/provider/sendprice")
    @Login
    public R sendPrice(@RequestBody PurchaseOrders param, @LoginUser UserEntity user) {
        QueryWrapper<PurchaseOrders> wrapper = new QueryWrapper<>();
        wrapper.eq("id", param.getId()).eq("provider_id", user.getUserId());
        PurchaseOrders po = purchaseOrdersService.getOne(wrapper);
        if (po.getStatus() == OrdersEnum.TWO.getStatus() || po.getStatus() == OrdersEnum.FOUR.getStatus()) {
            PurchaseOrders purchaseOrders = new PurchaseOrders();
            purchaseOrders.setId(param.getId());
            purchaseOrders.setStatus(param.getStatus());
            purchaseOrders.setFinalQuotation(param.getFinalQuotation());
            purchaseOrdersService.updateById(purchaseOrders);
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "供应商确认收款接口")
    @PostMapping("/provider/confirmreceipt")
    @Login
    public R confirmReceipt(@RequestBody PurchaseOrders param, @LoginUser UserEntity user) {
        QueryWrapper<PurchaseOrders> wrapper = new QueryWrapper<>();
        wrapper.eq("id", param.getId()).eq("provider_id", user.getUserId());
        PurchaseOrders po = purchaseOrdersService.getOne(wrapper);
        if(po == null){
            throw new RRException("采购单不存在，请确认订单号");
        }
        if (po.getStatus() == OrdersEnum.TEN.getStatus()) {
            PurchaseOrders purchaseOrders = new PurchaseOrders();
            purchaseOrders.setId(param.getId());
            purchaseOrders.setStatus(OrdersEnum.ELEVEN.getStatus());
            purchaseOrdersService.updateById(purchaseOrders);
            return R.ok();
        }
        return R.error();
    }

    /***********采购商接口*/
    @ApiOperation(value = "采购商确认订单金额接口")
    @PostMapping("/buyer/confirmprice")
    @Login
    public R confirmPrice(@RequestBody PurchaseOrders param, @LoginUser UserEntity user) {
        if (param.getStatus() == OrdersEnum.FIVE.getStatus() && param.getStatus() == OrdersEnum.FOUR.getStatus()) {
            return R.error("输入状态错误");
        }
        QueryWrapper<PurchaseOrders> wrapper = new QueryWrapper<>();
        wrapper.eq("id", param.getId()).eq("buyer_id", user.getUserId());
        PurchaseOrders po = purchaseOrdersService.getOne(wrapper);

        if(po == null){
            throw new RRException("订单不存在，请联系管理员");
        }

        if (po.getStatus() == OrdersEnum.THREE.getStatus()) {
            PurchaseOrders purchaseOrders = new PurchaseOrders();
            purchaseOrders.setId(param.getId());
            purchaseOrders.setStatus(param.getStatus());
            purchaseOrdersService.updateById(purchaseOrders);
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "采购商融资付款接口")
    @PostMapping("/buyer/financing")
    @Login
    public R financing(@RequestBody PayForm param, @LoginUser UserEntity user) {
        PurchaseOrders po = purchaseOrdersService.getById(param.getOrderNo());
        PurchaseOrders purchaseOrders = payCheck(param, user);
        AccountFinancingPayForm form = new AccountFinancingPayForm();
        form.setPayForm(param);
        form.setPurchaseOrders(po);
        form.setUser(user);
        org.springblade.core.tool.api.R r = accountService.financingPay(form);        //融资付款方法
        if(r.getCode() == FeignResultCodeConstant.EXCEPTION_CODE){
            throw new RRException(r.getMsg());
        }
        purchaseOrders.setStatus(OrdersEnum.SIX.getStatus());
        purchaseOrdersService.updateById(purchaseOrders);
        return R.ok();
    }

    @Resource
    private AccountServiceFeign accountService;
    @Resource
    private AccountDetailServiceFeign accountDetailService;


    @ApiOperation(value = "采购商直接付款接口")
    @PostMapping("/buyer/pay")
    @Login
    public R pay(@RequestBody PayForm param, @LoginUser UserEntity user) {
        //todo 这里可能要引出分布式事务
        PurchaseOrders purchaseOrders = payCheck(param, user);
        PurchaseOrders pd = purchaseOrdersService.getById(param.getOrderNo());
        AccountPayForm accountPayForm = new AccountPayForm();
        accountPayForm.setPurchaseOrders(pd);
        accountPayForm.setUser(user);
        org.springblade.core.tool.api.R r = accountService.pay(accountPayForm);//直接付款方法
        //如果出现异常 则抛出
        if(r.getCode() == FeignResultCodeConstant.EXCEPTION_CODE){
            throw new RRException(r.getMsg());
        }
        purchaseOrders.setStatus(OrdersEnum.FOURTEEN.getStatus());
        purchaseOrdersService.updateById(purchaseOrders);
        return R.ok();
    }

    @ApiOperation(value = "采购商查询采购单接口")
    @PostMapping("/buyer/listpage")
    @Login
    public R buyerlistpage(@RequestBody PageForm param, @LoginUser UserEntity user) {
        return R.ok().put("Orderpage", purchaseOrdersService.listPurchaseOrdersUseForPurchaser(new Page(param.getPage(), param.getSize()), user.getUserId()));
    }

    @ApiOperation(value = "供应商查询采购单接口")
    @PostMapping("/provider/listpage")
    @Login
    public R providerlistpage(@RequestBody PageForm param, @LoginUser UserEntity user) {
        return R.ok().put("Orderpage", purchaseOrdersService.listPurchaseOrderUseForProvider(new Page(param.getPage(), param.getSize()), user.getUserId()));
    }

    @ApiOperation(value = "订单详情")
    @PostMapping("/detail")
    @Login
    // @HasPermission(identification = UserTypeEnum.PURCHASER)
    public R getDetail(@RequestParam("id") Integer id, @LoginUser UserEntity user) {
        return R.ok().put("row", purchaseOrdersService.getById(id));
    }


    @ApiOperation(value = "供应商订单统计")
    @PostMapping("/provider/statistics")
    @Login
    public R getProviderStatistics(@LoginUser UserEntity user) {

        return R.ok().put("row", purchaseOrdersService.getStatistics(user.getUserId().longValue()));
    }


    /***
     *
     * 抽象公共方法
     * */
    private PurchaseOrders payCheck(PayForm param, UserEntity user) {
        QueryWrapper<PurchaseOrders> wrapper = new QueryWrapper<>();
        wrapper.eq("id", param.getOrderNo()).eq("buyer_id", user.getUserId());
        PurchaseOrders po = purchaseOrdersService.getOne(wrapper);
        if(po == null){
            throw new RRException("订单不存在，请联系管理员");
        }
        if (po.getStatus() != OrdersEnum.FIVE.getStatus()) {
            throw new RRException("订单状态异常", 502);
        }
        PurchaseOrders purchaseOrders = new PurchaseOrders();
        purchaseOrders.setId(param.getOrderNo());
        return purchaseOrders;
    }


}