package org.springblade.bgadmin.modules.sys.controller;

import org.springblade.bgadmin.modules.sys.entity.AccountEntity;
import org.springblade.bgadmin.modules.sys.service.AccountService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
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
    //@RequiresPermissions("sys:account:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = accountService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:account:info")
    public R info(@PathVariable("id") Integer id) {
            AccountEntity account = accountService.getById(id);

        return R.ok().put("account", account);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:account:save")
    public R save(@RequestBody AccountEntity account) {
            accountService.save(account);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:account:update")
    public R update(@RequestBody AccountEntity account) {
        //ValidatorUtils.validateEntity(account);
            accountService.updateById(account);//全部更新

        return R.ok();
    }

    /**
     *
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:account:delete")
    public R delete(@RequestBody Integer[] ids) {
            accountService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
