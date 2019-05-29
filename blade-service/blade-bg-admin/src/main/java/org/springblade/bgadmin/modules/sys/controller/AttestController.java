package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.sys.entity.AttestEntity;
import org.springblade.bgadmin.modules.sys.service.AttestService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 企业认证信息表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/attest")
@Api(tags = "企业认证信息类 ", description = " * @author jinzeze")
public class AttestController {
    @Autowired
    private AttestService attestService;

    /**
     * 列表
     */
    @PostMapping("/list")
    //@RequiresPermissions("sys:attest:list")
    @ApiOperation(value = "列表", notes = "")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attestService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/detail")
  //  @RequiresPermissions("sys:attest:info")
    @ApiOperation(value = "信息", notes = "")
    public R info(@RequestParam("userId") Integer id) {
        AttestEntity attest = attestService.list(new QueryWrapper<AttestEntity>().eq("user_id",id).orderBy(true,false,"creat_time")).get(0);
        return R.ok().put("attest", attest);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("sys:attest:save")
    @ApiOperation(value = "保存", notes = "")
    public R save(@RequestBody AttestEntity attest) {
        attestService.save(attest);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:attest:update")
    public R update(@RequestBody AttestEntity attest) {
        //ValidatorUtils.validateEntity(attest);
            attestService.updateById(attest);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:attest:delete")
    public R delete(@RequestBody Integer[] ids) {
            attestService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
