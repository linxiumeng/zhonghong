package org.springblade.bgadmin.modules.sys.controller;

import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.plugins.Page;
import io.finepetro.common.utils.R;
import io.finepetro.modules.oss.cloud.CloudStorageConfig;
import io.finepetro.modules.sys.entity.LoadBill;
import io.finepetro.modules.sys.entity.PurchaseOrdersEntity;
import io.finepetro.modules.sys.form.LoadBillForm;
import io.finepetro.modules.sys.form.mybatis.LoadBillCondition;
import io.finepetro.modules.sys.service.LoadBillService;
import io.finepetro.modules.sys.service.PurchaseOrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author hanbin
 */
@RestController
@RequestMapping("sys/loadbill")
public class LoadBillController {

    @Resource
    LoadBillService loadBillService;

    @Resource
    PurchaseOrdersService purchaseOrdersService;

    private CloudStorageConfig config = new CloudStorageConfig();
    OSSClient ossClient = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
            config.getAliyunAccessKeySecret());


    @PostMapping("list")
    public R listLoadBills(@RequestBody LoadBillForm loadBillForm) {
        LoadBillCondition condition = new LoadBillCondition();
        BeanUtils.copyProperties(loadBillForm, condition);
        return R.ok().put("result", loadBillService.listLoadBillWithOrder(new Page(loadBillForm.getPage(), loadBillForm.getSize()), condition));
    }

    @PostMapping("save")
    public R saveLoadBill(@RequestBody LoadBillForm loadBillForm) {

        LoadBill loadBill = new LoadBill();

        Integer orderId = loadBillForm.getOrderId();

        if(orderId == null){
            return R.error("订单id为空");
        }

        PurchaseOrdersEntity purchaseOrdersEntity = purchaseOrdersService.selectById(orderId);
        if(purchaseOrdersEntity == null){
            return R.error("订单不存在");
        }

        if(loadBillService.selectByOrderId(orderId) != null){
            return R.error("该订单已有提货单");
        }


        BeanUtils.copyProperties(loadBillForm,loadBill);

        boolean flag = loadBillService.insert(loadBill);
        if (flag) {
            return R.ok();
        }

        return R.error();

    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    //   @RequiresPermissions("sys:purchaseorders:delete")
    public R delete(@RequestBody Integer[] ids) {
        loadBillService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


}
