package org.springblade.bgadmin.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.sys.entity.BankEntity;
import org.springblade.bgadmin.modules.sys.service.BankService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
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
@Api(tags = "银行表", description = " * @author jinzeze")
public class BankController {
    @Autowired
    private BankService bankService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
    //@RequiresPermissions("sys:bank:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = bankService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    //@RequiresPermissions("sys:bank:info")
    @ApiOperation(value = "信息", notes = "")
    public R info(@PathVariable("id") Integer id) {
            BankEntity bank = bankService.getById(id);

        return R.ok().put("bank", bank);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
    //@RequiresPermissions("sys:bank:save")
    public R save(@RequestBody BankEntity bank) {
            bankService.save(bank);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:bank:update")
    public R update(@RequestBody BankEntity bank) {
       //ValidatorUtils.validateEntity(bank);
            bankService.updateById(bank);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:bank:delete")
    public R delete(@RequestBody Integer[] ids) {
            bankService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
