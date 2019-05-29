package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.sys.entity.DemandEntity;
import org.springblade.bgadmin.modules.sys.form.DemandForm;
import org.springblade.bgadmin.modules.sys.form.mybatis.DemandCondition;
import org.springblade.bgadmin.modules.sys.service.DemandService;
import org.springblade.common.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 需求表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
@RestController
@RequestMapping("sys/demand")
@Api(tags = "需求表", description = " * @author jinzeze")
public class DemandController {
    @Autowired
    private DemandService demandService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
 //   @RequiresPermissions("sys:demand:list")
    public R list(@RequestBody DemandForm demandForm) {
      //  PageUtils page = demandService.queryPage(params);

        IPage page = new Page(demandForm.getPage(),demandForm.getSize());
        DemandCondition demandCondition = new DemandCondition();
        BeanUtils.copyProperties(demandForm,demandCondition);
        return R.ok().put("result", demandService.listDemandOrderUsers(page,demandCondition));
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "")
    //@RequiresPermissions("sys:demand:info")
    public R info(@PathVariable("id") Integer id) {
            DemandEntity demand = demandService.getById(id);

        return R.ok().put("demand", demand);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
    //@RequiresPermissions("sys:demand:save")
    public R save(@RequestBody DemandEntity demand) {
            demandService.save(demand);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:demand:update")
    public R update(@RequestBody DemandEntity demand) {
        //ValidatorUtils.validateEntity(demand);
            demandService.updateById(demand);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:demand:delete")
    public R delete(@RequestBody Integer[] ids) {
            demandService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
