package org.springblade.bgadmin.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "仓储表", description = " * @author jinzeze")
public class RepertoryController {
    @Autowired
    private RepertoryService repertoryService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
    //@RequiresPermissions("sys:repertory:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = repertoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "")
    //@RequiresPermissions("sys:repertory:info")
    public R info(@PathVariable("id") Integer id) {
            RepertoryEntity repertory = repertoryService.getById(id);

        return R.ok().put("repertory", repertory);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
   // @RequiresPermissions("sys:repertory:save")
    public R save(@RequestBody RepertoryEntity repertory) {
            repertoryService.save(repertory);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:repertory:update")
    public R update(@RequestBody RepertoryEntity repertory) {
        //ValidatorUtils.validateEntity(repertory);
            repertoryService.updateById(repertory);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:repertory:delete")
    public R delete(@RequestBody Integer[] ids) {
            repertoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
