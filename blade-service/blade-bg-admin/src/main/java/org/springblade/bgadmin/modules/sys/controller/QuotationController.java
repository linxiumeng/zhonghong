package org.springblade.bgadmin.modules.sys.controller;

import org.springblade.bgadmin.modules.sys.entity.QuotationEntity;
import org.springblade.bgadmin.modules.sys.service.QuotationService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 报价单表

 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
@RestController
@RequestMapping("sys/quotation")
public class QuotationController {
    @Autowired
    private QuotationService quotationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:quotation:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = quotationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
   // @RequiresPermissions("sys:quotation:info")
    public R info(@PathVariable("id") Integer id) {
            QuotationEntity quotation = quotationService.getById(id);

        return R.ok().put("quotation", quotation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:quotation:save")
    public R save(@RequestBody QuotationEntity quotation) {
            quotationService.save(quotation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:quotation:update")
    public R update(@RequestBody QuotationEntity quotation) {
        //ValidatorUtils.validateEntity(quotation);
            quotationService.updateById(quotation);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:quotation:delete")
    public R delete(@RequestBody Integer[] ids) {
            quotationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
