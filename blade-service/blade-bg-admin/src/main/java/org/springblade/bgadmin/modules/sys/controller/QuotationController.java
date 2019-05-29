package org.springblade.bgadmin.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "报价单表", description = " * @author jinzeze")
public class QuotationController {
    @Autowired
    private QuotationService quotationService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
    //@RequiresPermissions("sys:quotation:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = quotationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "")
   // @RequiresPermissions("sys:quotation:info")
    public R info(@PathVariable("id") Integer id) {
            QuotationEntity quotation = quotationService.getById(id);

        return R.ok().put("quotation", quotation);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
    //@RequiresPermissions("sys:quotation:save")
    public R save(@RequestBody QuotationEntity quotation) {
            quotationService.save(quotation);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:quotation:update")
    public R update(@RequestBody QuotationEntity quotation) {
        //ValidatorUtils.validateEntity(quotation);
            quotationService.updateById(quotation);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:quotation:delete")
    public R delete(@RequestBody Integer[] ids) {
            quotationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
