package org.springblade.bgadmin.modules.sys.controller;

import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.entity.BankEntity;
import io.finepetro.modules.sys.service.BankService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 银行表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/bank")
public class BankController {
    @Autowired
    private BankService bankService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:bank:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = bankService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:bank:info")
    public R info(@PathVariable("id") Integer id) {
            BankEntity bank = bankService.selectById(id);

        return R.ok().put("bank", bank);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:bank:save")
    public R save(@RequestBody BankEntity bank) {
            bankService.insert(bank);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:bank:update")
    public R update(@RequestBody BankEntity bank) {
        ValidatorUtils.validateEntity(bank);
            bankService.updateAllColumnById(bank);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:bank:delete")
    public R delete(@RequestBody Integer[] ids) {
            bankService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
