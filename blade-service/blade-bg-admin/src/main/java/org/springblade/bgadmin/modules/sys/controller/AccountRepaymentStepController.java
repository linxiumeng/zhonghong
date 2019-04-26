package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.entity.AccountRepaymentStepEntity;
import io.finepetro.modules.sys.form.AccountRepaymentStepForm;
import io.finepetro.modules.sys.form.mybatis.AccountRepaymentStepDaoCondition;
import io.finepetro.modules.sys.service.AccountRepaymentStepService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
public class AccountRepaymentStepController {
    @Autowired
    private AccountRepaymentStepService accountRepaymentStepService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:accountrepaymentstep:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = accountRepaymentStepService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:accountrepaymentstep:info")
    public R info(@PathVariable("id") Integer id) {
            AccountRepaymentStepEntity accountRepaymentStep = accountRepaymentStepService.selectById(id);

        return R.ok().put("accountRepaymentStep", accountRepaymentStep);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:accountrepaymentstep:save")
    public R save(@RequestBody AccountRepaymentStepEntity accountRepaymentStep) {
            accountRepaymentStepService.insert(accountRepaymentStep);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:accountrepaymentstep:update")
    public R update(@RequestBody AccountRepaymentStepEntity accountRepaymentStep) {
        ValidatorUtils.validateEntity(accountRepaymentStep);
            accountRepaymentStepService.updateAllColumnById(accountRepaymentStep);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:accountrepaymentstep:delete")
    public R delete(@RequestBody Integer[] ids) {
            accountRepaymentStepService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("step_list")
    public R listPaymentSteps(@RequestBody AccountRepaymentStepForm form){
        Page page = new Page(form.getPage(),form.getSize());
        AccountRepaymentStepDaoCondition condition = new AccountRepaymentStepDaoCondition();
        BeanUtils.copyProperties(form,condition);
        return R.ok().put("result",accountRepaymentStepService.listUserAccountRepaymentStep(page,condition));
    }

}
