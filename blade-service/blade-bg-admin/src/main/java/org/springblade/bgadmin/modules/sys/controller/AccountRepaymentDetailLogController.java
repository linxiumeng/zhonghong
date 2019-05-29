package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springblade.bgadmin.modules.sys.form.AccountRepaymentDetailLogForm;
import org.springblade.bgadmin.modules.sys.service.AccountRepaymentDetailLogService;
import org.springblade.common.entity.AccountRepayment;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 分期还款表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/accountrepaymentdetaillog")
@Api(tags = "余额详情表", description = " * @author jinzeze")
public class AccountRepaymentDetailLogController {
    @Autowired
    private AccountRepaymentDetailLogService accountRepaymentDetailLogService;

    /**
     * 列表
     */
    @PostMapping("/list")
   // @RequiresPermissions("sys:accountrepayment:list")
    @ApiOperation(value = "列表", notes = "")
    public R list(@RequestBody AccountRepaymentDetailLogForm accountRepaymentDetailLogForm) {

        IPage page = new Page(accountRepaymentDetailLogForm.getPage(),accountRepaymentDetailLogForm.getSize());
        QueryWrapper<AccountRepayment> wrapper = new QueryWrapper();

        if(accountRepaymentDetailLogForm.getStatus() != null){
            wrapper.eq("log.status",accountRepaymentDetailLogForm.getStatus());
        }

        if(accountRepaymentDetailLogForm.getStartDate() != null){
            wrapper.gt("log.create_date",accountRepaymentDetailLogForm.getStartDate());
        }

        if(accountRepaymentDetailLogForm.getEndDate() != null){
            wrapper.lt("log.create_date",accountRepaymentDetailLogForm.getEndDate());
        }

        if(StringUtils.isNotBlank(accountRepaymentDetailLogForm.getKeywords())){
            //wrapper.andNew().eq("users.company_name",accountRepaymentDetailLogForm.getKeywords()).or().eq("users.mobile",accountRepaymentDetailLogForm.getKeywords());
            wrapper.and(i->i.eq("users.company_name",accountRepaymentDetailLogForm.getKeywords()).or().eq("users.mobile",accountRepaymentDetailLogForm.getKeywords()));
        }



        return R.ok().put("result",accountRepaymentDetailLogService.listAccountRepaymentDetailLog(page,wrapper));
    }



}
