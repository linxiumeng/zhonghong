package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.bgadmin.modules.sys.CheckBGListUtils;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentEntity;
import org.springblade.bgadmin.modules.sys.enums.AccountRepaymentStatusEnum;
import org.springblade.bgadmin.modules.sys.form.AccountRepaymentForm;
import org.springblade.bgadmin.modules.sys.service.AccountRepaymentService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 分期还款表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/accountrepayment")
public class AccountRepaymentController {
    @Autowired
    private AccountRepaymentService accountRepaymentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("sys:accountrepayment:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = accountRepaymentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:accountrepayment:info")
    public R info(@PathVariable("id") Integer id) {
        AccountRepaymentEntity accountRepayment = accountRepaymentService.getById(id);

        return R.ok().put("accountRepayment", accountRepayment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:accountrepayment:save")
    public R save(@RequestBody AccountRepaymentEntity accountRepayment) {
        accountRepaymentService.save(accountRepayment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:accountrepayment:update")
    public R update(@RequestBody AccountRepaymentEntity accountRepayment) {
       // ValidatorUtils.validateEntity(accountRepayment);
        accountRepaymentService.updateById(accountRepayment);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:accountrepayment:delete")
    public R delete(@RequestBody Integer[] ids) {
        accountRepaymentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 列表
     */
    @RequestMapping("/repayment_list")
    //   @RequiresPermissions("sys:accountrepayment:list")
    public R repaymentList(@RequestBody AccountRepaymentForm repaymentForm) {
        IPage page = new Page(repaymentForm.getPage(), repaymentForm.getSize());
        QueryWrapper<AccountRepaymentEntity> entityWrapper = new QueryWrapper();

        if (repaymentForm.getStatus() != null) {
            //判断是不是逾期
            if (repaymentForm.getStatus() == AccountRepaymentStatusEnum.NOT_PAY || repaymentForm.getStatus() == AccountRepaymentStatusEnum.ALREADY_PAY) {
                entityWrapper.exists("select count(1) from tb_account_repayment_step where status = 2 and repayment_id = repayment.id");
            } else if (repaymentForm.getStatus() == AccountRepaymentStatusEnum.OVER_DEADLINE) {
                entityWrapper.notExists("select count(1) from tb_account_repayment_step where status = 2 and repayment_id = repayment.id");
            }
        }

        CheckBGListUtils.check(entityWrapper,repaymentForm,"repayment.create_date","users.company_name","users.mobile");

        return R.ok().put("result", accountRepaymentService.listAccountRepayment(page, entityWrapper));
    }

}
