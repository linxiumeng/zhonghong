package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springblade.bgadmin.modules.sys.entity.AccountDetailEntity;
import org.springblade.bgadmin.modules.sys.form.AccountDetailForm;
import org.springblade.bgadmin.modules.sys.service.AccountDetailService;
import org.springblade.common.entity.AccountDetail;
import org.springblade.common.utils.R;
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
        IPage page = new Page(accountDetailForm.getPage(),accountDetailForm.getSize());
        QueryWrapper <AccountDetailEntity> wrapper = new QueryWrapper();

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
            //wrapper.andNew().eq("users.company_name",accountDetailForm.getKeywords()).or().eq("users.mobile",accountDetailForm.getKeywords());
            wrapper.and(i->i.eq("users.company_name",accountDetailForm.getKeywords()).or().eq("users.mobile",accountDetailForm.getKeywords()));
        }

        return R.ok().put("result", accountDetailService.listAccountDetailWithUser(page,wrapper));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:accountdetail:info")
    public R info(@PathVariable("id") Integer id) {
            AccountDetailEntity accountDetail = accountDetailService.getById(id);

        return R.ok().put("accountDetail", accountDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:accountdetail:save")
    public R save(@RequestBody AccountDetailEntity accountDetail) {
            accountDetailService.save(accountDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:accountdetail:update")
    public R update(@RequestBody AccountDetailEntity accountDetail) {
        //ValidatorUtils.validateEntity(accountDetail);
            accountDetailService.updateById(accountDetail);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:accountdetail:delete")
    public R delete(@RequestBody Integer[] ids) {
            accountDetailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
