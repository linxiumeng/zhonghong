package org.springblade.bgadmin.modules.sys.controller;

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
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:provider:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = providerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("sys:provider:info")
    public R info(@PathVariable("userId") Integer userId) {
            ProviderEntity provider = providerService.getById(userId);

        return R.ok().put("provider", provider);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:provider:save")
    public R save(@RequestBody ProviderEntity provider) {
            providerService.save(provider);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:provider:update")
    public R update(@RequestBody ProviderEntity provider) {
       // ValidatorUtils.validateEntity(provider);
            providerService.updateById(provider);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:provider:delete")
    public R delete(@RequestBody Integer[] userIds) {
            providerService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
