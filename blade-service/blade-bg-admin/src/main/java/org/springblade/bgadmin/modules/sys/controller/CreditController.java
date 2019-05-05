package org.springblade.bgadmin.modules.sys.controller;

import org.springblade.bgadmin.modules.sys.entity.CreditEntity;
import org.springblade.bgadmin.modules.sys.service.CreditService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 授信表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/credit")
public class CreditController {
    @Autowired
    private CreditService creditService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:credit:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = creditService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:credit:info")
    public R info(@PathVariable("id") Integer id) {
            CreditEntity credit = creditService.getById(id);

        return R.ok().put("credit", credit);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:credit:save")
    public R save(@RequestBody CreditEntity credit) {
            creditService.save(credit);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:credit:update")
    public R update(@RequestBody CreditEntity credit) {
        //ValidatorUtils.validateEntity(credit);
            creditService.updateById(credit);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:credit:delete")
    public R delete(@RequestBody Integer[] ids) {
            creditService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
