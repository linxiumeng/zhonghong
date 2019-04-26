package org.springblade.bgadmin.modules.sys.controller;

import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.entity.TokenEntity;
import io.finepetro.modules.sys.service.TokenService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 密钥
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:token:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tokenService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:token:info")
    public R info(@PathVariable("userId") Integer userId) {
            TokenEntity token = tokenService.selectById(userId);

        return R.ok().put("token", token);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:token:save")
    public R save(@RequestBody TokenEntity token) {
            tokenService.insert(token);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:token:update")
    public R update(@RequestBody TokenEntity token) {
        ValidatorUtils.validateEntity(token);
            tokenService.updateAllColumnById(token);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:token:delete")
    public R delete(@RequestBody Integer[] userIds) {
            tokenService.deleteBatchIds(Arrays.asList(userIds));

        return R.ok();
    }

}
