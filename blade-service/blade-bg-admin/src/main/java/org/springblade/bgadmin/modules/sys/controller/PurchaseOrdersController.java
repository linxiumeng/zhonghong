package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.CheckBGListUtils;
import io.finepetro.modules.sys.entity.AccountRepaymentWithStepEntity;
import io.finepetro.modules.sys.entity.PurchaseOrdersEntity;
import io.finepetro.modules.sys.entity.PurchaseOrdersRepaymentEntity;
import io.finepetro.modules.sys.form.PurchaseOrderForm;
import io.finepetro.modules.sys.service.PurchaseOrdersService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;


/**
 * 采购单表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
@RestController
@RequestMapping("sys/purchaseorders")
public class PurchaseOrdersController {
    @Autowired
    private PurchaseOrdersService purchaseOrdersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //   @RequiresPermissions("sys:purchaseorders:list")
    public R list(@RequestBody PurchaseOrderForm purchaseOrderForm) {
        Page page = new Page(purchaseOrderForm.getPage(),purchaseOrderForm.getSize());

        EntityWrapper wrapper = new EntityWrapper();

        if(purchaseOrderForm.getOrdersStatus()!=null){
            wrapper.eq("status",purchaseOrderForm.getOrdersStatus());
        }

        CheckBGListUtils.check(wrapper,purchaseOrderForm,"creat_time","buyer_company","provider_company");

        //todo 问题

        return R.ok().put("page", purchaseOrdersService.selectPage(page,wrapper));
    }

    @RequestMapping("/order_repayment_list")
    //   @RequiresPermissions("sys:purchaseorders:list")
    public R orderRepaymentList(@RequestBody PurchaseOrderForm purchaseOrderForm) {
        Page page = new Page(purchaseOrderForm.getPage(),purchaseOrderForm.getSize());

        EntityWrapper wrapper = new EntityWrapper();

        wrapper.in("status",purchaseOrderForm.getOrderStatusArr());

        CheckBGListUtils.check(wrapper,purchaseOrderForm,"creat_time","buyer_company","provider_company");

        return R.ok().put("page", purchaseOrdersService.listOrderWithRepayment(page,wrapper));
    }


    /**
     * 信息
     */
    @RequestMapping("/detail")
    //   @RequiresPermissions("sys:purchaseorders:info")
    public R info(@RequestBody PurchaseOrderForm purchaseOrderForm) {
        if(purchaseOrderForm.getId() == null){
            return R.error();
        }

        PurchaseOrdersRepaymentEntity purchaseOrders = purchaseOrdersService.getOrderWithRepayment(purchaseOrderForm.getId());

        if(purchaseOrders != null) {
            String finalQuotationString = purchaseOrders.getFinalQuotation();
            AccountRepaymentWithStepEntity accountRepaymentWithStepEntity = purchaseOrders.getAccountRepaymentEntity();
            BigDecimal totalAmount = null;
            if (accountRepaymentWithStepEntity == null || accountRepaymentWithStepEntity.getTotalAmount() == null) {
                totalAmount = BigDecimal.ZERO;
            } else {
                totalAmount = accountRepaymentWithStepEntity.getTotalAmount();
            }
            if (StringUtils.isNotBlank(finalQuotationString)) {
                BigDecimal finalQuotation = new BigDecimal(finalQuotationString);
                purchaseOrders.setFirstPaidAccount(finalQuotation.subtract(totalAmount));
            } else {
                return R.error("订单的报价金额出错");
            }
        }

        return R.ok().put("result", purchaseOrders);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:purchaseorders:save")
    public R save(@RequestBody PurchaseOrdersEntity purchaseOrders) {
        purchaseOrdersService.insert(purchaseOrders);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:purchaseorders:update")
    public R update(@RequestBody PurchaseOrdersEntity purchaseOrders) {
        ValidatorUtils.validateEntity(purchaseOrders);
        purchaseOrdersService.updateAllColumnById(purchaseOrders);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:purchaseorders:delete")
    public R delete(@RequestBody Integer[] ids) {
        purchaseOrdersService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 融资审批
     *
     * @return
     */
    @PostMapping("review_repayment")
    public R reviewRepayment(@RequestBody PurchaseOrderForm purchaseOrderForm) {


        if (purchaseOrderForm.getId() == null || purchaseOrderForm.getStatus() == null) {
            return R.error();
        }

        if(purchaseOrderForm.getStatus() != 7 && purchaseOrderForm.getStatus() != 8){
            return R.error("审核状态错误");
        }

        PurchaseOrdersEntity ordersEntity = new PurchaseOrdersEntity();

        ordersEntity.setId(purchaseOrderForm.getId());
        ordersEntity.setStatus(purchaseOrderForm.getStatus());

        boolean flag = purchaseOrdersService.updateById(ordersEntity);

        if (flag) {
            return R.ok();
        }
        return R.error();
    }
}
