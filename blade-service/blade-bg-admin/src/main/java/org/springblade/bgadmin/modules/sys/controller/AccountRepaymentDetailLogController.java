package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.finepetro.common.utils.R;
import io.finepetro.modules.sys.form.AccountRepaymentDetailLogForm;
import io.finepetro.modules.sys.service.AccountRepaymentDetailLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountRepaymentDetailLogController {
    @Autowired
    private AccountRepaymentDetailLogService accountRepaymentDetailLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("sys:accountrepayment:list")
    public R list(@RequestBody AccountRepaymentDetailLogForm accountRepaymentDetailLogForm) {

        Page page = new Page(accountRepaymentDetailLogForm.getPage(),accountRepaymentDetailLogForm.getSize());
        EntityWrapper wrapper = new EntityWrapper();

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
            wrapper.andNew().eq("users.company_name",accountRepaymentDetailLogForm.getKeywords()).or().eq("users.mobile",accountRepaymentDetailLogForm.getKeywords());
        }



        return R.ok().put("result",accountRepaymentDetailLogService.listAccountRepaymentDetailLog(page,wrapper));
    }



}
