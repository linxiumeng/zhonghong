package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.bgadmin.modules.sys.entity.AttestEntity;
import org.springblade.bgadmin.modules.sys.service.AttestService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class AttestController {
    @Autowired
    private AttestService attestService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:attest:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attestService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/detail")
  //  @RequiresPermissions("sys:attest:info")
    public R info(@RequestParam("userId") Integer id) {
        AttestEntity attest = attestService.getOne(new QueryWrapper<AttestEntity>().eq("user_id",id));
        return R.ok().put("attest", attest);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:attest:save")
    public R save(@RequestBody AttestEntity attest) {
            attestService.save(attest);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:attest:update")
    public R update(@RequestBody AttestEntity attest) {
        //ValidatorUtils.validateEntity(attest);
            attestService.updateById(attest);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:attest:delete")
    public R delete(@RequestBody Integer[] ids) {
            attestService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
