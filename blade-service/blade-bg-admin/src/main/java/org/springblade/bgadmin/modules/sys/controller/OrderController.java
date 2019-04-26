package org.springblade.bgadmin.modules.sys.controller;

import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.entity.OrderEntity;
import io.finepetro.modules.sys.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 采购单表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:order:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:order:info")
    public R info(@PathVariable("id") Integer id) {
            OrderEntity order = orderService.selectById(id);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:order:save")
    public R save(@RequestBody OrderEntity order) {
            orderService.insert(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:order:update")
    public R update(@RequestBody OrderEntity order) {
        ValidatorUtils.validateEntity(order);
            orderService.updateAllColumnById(order);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:order:delete")
    public R delete(@RequestBody Integer[] ids) {
            orderService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
