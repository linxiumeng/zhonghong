package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.entity.AccountDetailEntity;
import io.finepetro.modules.sys.form.AccountDetailForm;
import io.finepetro.modules.sys.service.AccountDetailService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 余额详情表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/accountdetail")
public class AccountDetailController {
    @Autowired
    private AccountDetailService accountDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
  //  @RequiresPermissions("sys:accountdetail:list")
    public R list(@RequestBody AccountDetailForm accountDetailForm) {
        Page page = new Page(accountDetailForm.getPage(),accountDetailForm.getSize());
        EntityWrapper wrapper = new EntityWrapper();

        if(accountDetailForm.getStatus() != null){
            wrapper.eq("type",accountDetailForm.getStatus());
        }

        if(accountDetailForm.getStartDate() != null){
            wrapper.gt("account_detail.create_date",accountDetailForm.getStartDate());
        }

        if(accountDetailForm.getEndDate() != null){
            wrapper.lt("account_detail.create_date",accountDetailForm.getEndDate());
        }

        if(StringUtils.isNotBlank(accountDetailForm.getKeywords())){
            wrapper.andNew().eq("users.company_name",accountDetailForm.getKeywords()).or().eq("users.mobile",accountDetailForm.getKeywords());
        }

        return R.ok().put("result", accountDetailService.listAccountDetailWithUser(page,wrapper));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:accountdetail:info")
    public R info(@PathVariable("id") Integer id) {
            AccountDetailEntity accountDetail = accountDetailService.selectById(id);

        return R.ok().put("accountDetail", accountDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:accountdetail:save")
    public R save(@RequestBody AccountDetailEntity accountDetail) {
            accountDetailService.insert(accountDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:accountdetail:update")
    public R update(@RequestBody AccountDetailEntity accountDetail) {
        ValidatorUtils.validateEntity(accountDetail);
            accountDetailService.updateAllColumnById(accountDetail);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:accountdetail:delete")
    public R delete(@RequestBody Integer[] ids) {
            accountDetailService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
