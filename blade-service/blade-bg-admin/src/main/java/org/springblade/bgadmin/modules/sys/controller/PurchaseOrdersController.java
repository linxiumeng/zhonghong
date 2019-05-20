package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springblade.bgadmin.modules.sys.CheckBGListUtils;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentWithStepEntity;
import org.springblade.bgadmin.modules.sys.entity.PurchaseOrdersEntity;
import org.springblade.bgadmin.modules.sys.entity.PurchaseOrdersRepaymentEntity;
import org.springblade.bgadmin.modules.sys.form.PurchaseOrderForm;
import org.springblade.bgadmin.modules.sys.service.PurchaseOrdersService;
import org.springblade.common.utils.R;
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
        IPage page = new Page(purchaseOrderForm.getPage(),purchaseOrderForm.getSize());

        QueryWrapper wrapper = new QueryWrapper();

        if(purchaseOrderForm.getOrdersStatus()!=null){
            wrapper.eq("status",purchaseOrderForm.getOrdersStatus());
        }

        CheckBGListUtils.check(wrapper,purchaseOrderForm,"creat_time","buyer_company","provider_company");

        wrapper.orderByDesc("creat_time");

        //todo 问题

        return R.ok().put("page", purchaseOrdersService.page(page,wrapper));
    }

    @RequestMapping("/order_repayment_list")
    //   @RequiresPermissions("sys:purchaseorders:list")
    public R orderRepaymentList(@RequestBody PurchaseOrderForm purchaseOrderForm) {
        Page page = new Page(purchaseOrderForm.getPage(),purchaseOrderForm.getSize());

        QueryWrapper wrapper = new QueryWrapper();

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
    //@RequiresPermissions("sys:purchaseorders:save")
    public R save(@RequestBody PurchaseOrdersEntity purchaseOrders) {
        purchaseOrdersService.save(purchaseOrders);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:purchaseorders:update")
    public R update(@RequestBody PurchaseOrdersEntity purchaseOrders) {
        //ValidatorUtils.validateEntity(purchaseOrders);
        purchaseOrdersService.updateById(purchaseOrders);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:purchaseorders:delete")
    public R delete(@RequestBody Integer[] ids) {
        purchaseOrdersService.removeByIds(Arrays.asList(ids));

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

        // todo 状态14暂时先用着 以后要改掉
        if(purchaseOrderForm.getStatus() != 7 && purchaseOrderForm.getStatus() != 8 && purchaseOrderForm.getStatus() != 14){
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
