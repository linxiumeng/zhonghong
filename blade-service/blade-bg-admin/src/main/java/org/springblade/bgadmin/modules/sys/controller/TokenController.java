package org.springblade.bgadmin.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.sys.service.TokenService;
import org.springblade.common.entity.TokenEntity;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
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
@Api(tags = "密钥", description = " * @author jinzeze")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
    //@RequiresPermissions("sys:token:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tokenService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{userId}")
    @ApiOperation(value = "信息", notes = "")
    //@RequiresPermissions("sys:token:info")
    public R info(@PathVariable("userId") Integer userId) {
            TokenEntity token = tokenService.getById(userId);

        return R.ok().put("token", token);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
    //@RequiresPermissions("sys:token:save")
    public R save(@RequestBody TokenEntity token) {
            tokenService.save(token);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:token:update")
    public R update(@RequestBody TokenEntity token) {
       // ValidatorUtils.validateEntity(token);
            tokenService.updateById(token);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:token:delete")
    public R delete(@RequestBody Integer[] userIds) {
            tokenService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
