package org.springblade.bgadmin.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.sys.entity.ProviderEntity;
import org.springblade.bgadmin.modules.sys.service.ProviderService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 用户表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/provider")
@Api(tags = "用户表", description = " * @author jinzeze")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
    //@RequiresPermissions("sys:provider:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = providerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{userId}")
    @ApiOperation(value = "信息", notes = "")
    //@RequiresPermissions("sys:provider:info")
    public R info(@PathVariable("userId") Integer userId) {
            ProviderEntity provider = providerService.getById(userId);

        return R.ok().put("provider", provider);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
    //@RequiresPermissions("sys:provider:save")
    public R save(@RequestBody ProviderEntity provider) {
            providerService.save(provider);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:provider:update")
    public R update(@RequestBody ProviderEntity provider) {
       // ValidatorUtils.validateEntity(provider);
            providerService.updateById(provider);//全部更新

        return R.ok();
    }

    /**
     *
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:provider:delete")
    public R delete(@RequestBody Integer[] userIds) {
            providerService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
