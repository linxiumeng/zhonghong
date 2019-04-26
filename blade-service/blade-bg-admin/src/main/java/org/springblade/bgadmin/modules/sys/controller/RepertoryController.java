package org.springblade.bgadmin.modules.sys.controller;

import org.springblade.bgadmin.modules.sys.entity.RepertoryEntity;
import org.springblade.bgadmin.modules.sys.service.RepertoryService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 仓储表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/repertory")
public class RepertoryController {
    @Autowired
    private RepertoryService repertoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:repertory:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = repertoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:repertory:info")
    public R info(@PathVariable("id") Integer id) {
            RepertoryEntity repertory = repertoryService.getById(id);

        return R.ok().put("repertory", repertory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("sys:repertory:save")
    public R save(@RequestBody RepertoryEntity repertory) {
            repertoryService.save(repertory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:repertory:update")
    public R update(@RequestBody RepertoryEntity repertory) {
        //ValidatorUtils.validateEntity(repertory);
            repertoryService.updateById(repertory);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:repertory:delete")
    public R delete(@RequestBody Integer[] ids) {
            repertoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
