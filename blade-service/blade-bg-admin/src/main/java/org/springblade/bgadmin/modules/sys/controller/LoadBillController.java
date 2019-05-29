package org.springblade.bgadmin.modules.sys.controller;

import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.oss.cloud.CloudStorageConfig;
import org.springblade.bgadmin.modules.sys.entity.LoadBill;
import org.springblade.bgadmin.modules.sys.entity.PurchaseOrdersEntity;
import org.springblade.bgadmin.modules.sys.form.LoadBillForm;
import org.springblade.bgadmin.modules.sys.form.mybatis.LoadBillCondition;
import org.springblade.bgadmin.modules.sys.service.LoadBillService;
import org.springblade.bgadmin.modules.sys.service.PurchaseOrdersService;
import org.springblade.common.utils.R;
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
@Api(tags = "余额详情表", description = " * @author jinzeze")
public class LoadBillController {

    @Resource
    LoadBillService loadBillService;

    @Resource
    PurchaseOrdersService purchaseOrdersService;

    private CloudStorageConfig config = new CloudStorageConfig();
    OSSClient ossClient = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
            config.getAliyunAccessKeySecret());


    @PostMapping("list")
    @ApiOperation(value = "列表", notes = "")
    public R listLoadBills(@RequestBody LoadBillForm loadBillForm) {
        LoadBillCondition condition = new LoadBillCondition();
        BeanUtils.copyProperties(loadBillForm, condition);
        return R.ok().put("result", loadBillService.listLoadBillWithOrder(new Page(loadBillForm.getPage(), loadBillForm.getSize()), condition));
    }

    @PostMapping("save")
    @ApiOperation(value = "保存", notes = "")
    public R saveLoadBill(@RequestBody LoadBillForm loadBillForm) {

        LoadBill loadBill = new LoadBill();

        Integer orderId = loadBillForm.getOrderId();

        if(orderId == null){
            return R.error("订单id为空");
        }

        PurchaseOrdersEntity purchaseOrdersEntity = purchaseOrdersService.getById(orderId);
        if(purchaseOrdersEntity == null){
            return R.error("订单不存在");
        }

        BeanUtils.copyProperties(loadBillForm,loadBill);

        boolean flag = loadBillService.save(loadBill);
        if (flag) {
            return R.ok();
        }

        return R.error();

    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //   @RequiresPermissions("sys:purchaseorders:delete")
    public R delete(@RequestBody Integer[] ids) {
        loadBillService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


}
