package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.entity.DemandEntity;
import io.finepetro.modules.sys.form.DemandForm;
import io.finepetro.modules.sys.form.mybatis.DemandCondition;
import io.finepetro.modules.sys.service.DemandService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class DemandController {
    @Autowired
    private DemandService demandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("sys:demand:list")
    public R list(@RequestBody DemandForm demandForm) {
      //  PageUtils page = demandService.queryPage(params);

        Page page = new Page(demandForm.getPage(),demandForm.getSize());
        DemandCondition demandCondition = new DemandCondition();
        BeanUtils.copyProperties(demandForm,demandCondition);
        return R.ok().put("result", demandService.listDemandOrderUsers(page,demandCondition));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:demand:info")
    public R info(@PathVariable("id") Integer id) {
            DemandEntity demand = demandService.selectById(id);

        return R.ok().put("demand", demand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:demand:save")
    public R save(@RequestBody DemandEntity demand) {
            demandService.insert(demand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:demand:update")
    public R update(@RequestBody DemandEntity demand) {
        ValidatorUtils.validateEntity(demand);
            demandService.updateAllColumnById(demand);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:demand:delete")
    public R delete(@RequestBody Integer[] ids) {
            demandService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
