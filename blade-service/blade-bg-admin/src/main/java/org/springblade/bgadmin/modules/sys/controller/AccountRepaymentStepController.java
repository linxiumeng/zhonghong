package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentStepEntity;
import org.springblade.bgadmin.modules.sys.form.AccountRepaymentStepForm;
import org.springblade.bgadmin.modules.sys.form.mybatis.AccountRepaymentStepDaoCondition;
import org.springblade.bgadmin.modules.sys.service.AccountRepaymentStepService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 分期还款详情表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/accountrepaymentstep")
@Api(tags = "分期还款详情表", description = " * @author jinzeze")
public class AccountRepaymentStepController {
    @Autowired
    private AccountRepaymentStepService accountRepaymentStepService;

    /**
     * 列表
     */
    @PostMapping("/list")
   // @RequiresPermissions("sys:accountrepaymentstep:list")
    @ApiOperation(value = "列表", notes = "")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = accountRepaymentStepService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "")
    //@RequiresPermissions("sys:accountrepaymentstep:info")
    public R info(@PathVariable("id") Integer id) {
            AccountRepaymentStepEntity accountRepaymentStep = accountRepaymentStepService.getById(id);

        return R.ok().put("accountRepaymentStep", accountRepaymentStep);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
    //@RequiresPermissions("sys:accountrepaymentstep:save")
    public R save(@RequestBody AccountRepaymentStepEntity accountRepaymentStep) {
            accountRepaymentStepService.save(accountRepaymentStep);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:accountrepaymentstep:update")
    public R update(@RequestBody AccountRepaymentStepEntity accountRepaymentStep) {
        //ValidatorUtils.validateEntity(accountRepaymentStep);
            accountRepaymentStepService.updateById(accountRepaymentStep);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:accountrepaymentstep:delete")
    public R delete(@RequestBody Integer[] ids) {
            accountRepaymentStepService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("step_list")
    @ApiOperation(value = "列表", notes = "")
    public R listPaymentSteps(@RequestBody AccountRepaymentStepForm form){
        IPage page = new Page(form.getPage(),form.getSize());
        AccountRepaymentStepDaoCondition condition = new AccountRepaymentStepDaoCondition();
        BeanUtils.copyProperties(form,condition);
        return R.ok().put("result",accountRepaymentStepService.listUserAccountRepaymentStep(page,condition));
    }

}
