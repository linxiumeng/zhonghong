package org.springblade.bgadmin.modules.sys.controller;

import org.springblade.bgadmin.modules.sys.entity.OrderEntity;
import org.springblade.bgadmin.modules.sys.service.OrderService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
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
    //@RequiresPermissions("sys:order:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
   // @RequiresPermissions("sys:order:info")
    public R info(@PathVariable("id") Integer id) {
            OrderEntity order = orderService.getById(id);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:order:save")
    public R save(@RequestBody OrderEntity order) {
            orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:order:update")
    public R update(@RequestBody OrderEntity order) {
        //ValidatorUtils.validateEntity(order);
            orderService.updateById(order);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("sys:order:delete")
    public R delete(@RequestBody Integer[] ids) {
            orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
