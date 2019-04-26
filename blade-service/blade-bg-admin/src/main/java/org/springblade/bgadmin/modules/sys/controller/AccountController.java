package org.springblade.bgadmin.modules.sys.controller;

import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.entity.AccountEntity;
import io.finepetro.modules.sys.service.AccountService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 余额表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:account:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = accountService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:account:info")
    public R info(@PathVariable("id") Integer id) {
            AccountEntity account = accountService.selectById(id);

        return R.ok().put("account", account);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:account:save")
    public R save(@RequestBody AccountEntity account) {
            accountService.insert(account);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:account:update")
    public R update(@RequestBody AccountEntity account) {
        ValidatorUtils.validateEntity(account);
            accountService.updateAllColumnById(account);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:account:delete")
    public R delete(@RequestBody Integer[] ids) {
            accountService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
