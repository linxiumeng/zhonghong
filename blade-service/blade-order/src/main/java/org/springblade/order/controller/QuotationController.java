package org.springblade.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.HasPermission;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.entity.Quotation;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.PageForm;
import org.springblade.common.form.QuotationForm;
import org.springblade.common.utils.R;
import org.springblade.common.utils.RedisUtils;
import org.springblade.common.utils.SmsCheckUtils;
import org.springblade.common.validation.group.InsertGroup;
import org.springblade.order.service.DemandService;
import org.springblade.order.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author linxiumeng
 * @since 2019-03-10 11:59:55
 */
@Api(tags = "报价单表(Quotation)表操作控制器", description = " * @author linxiumeng")
@RestController
@RequestMapping("api/quotation")
public class QuotationController {
    private QuotationService quotationService;

    @Resource
    RedisUtils redisUtils;

    @Resource
    SmsCheckUtils smsCheckUtils;

    @Resource
    DemandService demandService;

    @Autowired
    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }


    @ApiOperation(value = "提交报价单接口")
    @PostMapping("insert")
    @Login
    @HasPermission(needVerifyUser = true)
    public R insert(@RequestBody @Validated(InsertGroup.class) QuotationForm param, @LoginUser UserEntity userEntity) {

        if (!smsCheckUtils.check(userEntity.getMobile(), param.getCode())) {
            return R.error("验证码错误");
        }

        if(userEntity.getUserId().toString().equals(demandService.getById(param.getDemandId()).getCreatUserid())){
            return R.error("不能给自己的需求单报价");
        }else{
            param.setUserId(userEntity.getUserId());
            return quotationService.save((Quotation) param) ? R.ok() : R.error();
        }
    }

    @ApiOperation(value = "分页查询自己报价单列表接口")
    @PostMapping("selectPage")
    @Login
    @HasPermission(needVerifyUser = true)
    public R selectPage(@RequestBody PageForm param, @LoginUser UserEntity userEntity) {
        Page<Quotation> page = new Page<>(param.getPage(), param.getSize());
        Page<Quotation> quotationPage = quotationService.listQuotationsWithDemand(page, userEntity.getUserId());
        return R.ok().put("result", quotationPage);
    }


    @ApiOperation(value = "根据需求单id查询报价单列表接口")
    @PostMapping("selectPageByDemandId")
    @Login
    @HasPermission(needVerifyCredit = true)
    public R selectPageByDemandId(@RequestBody QuotationForm param, @LoginUser UserEntity userEntity) {
        List<Quotation> quotationList = quotationService.listQuotationsByDemandIdWithDemand(param.getDemandId().longValue(),userEntity.getUserId());
        return R.ok().put("result", quotationList);
    }

    /************采购商接口*/
    @ApiOperation(value = "分页查询供应商报价单列表接口")
    @PostMapping("demandPage")
    @Login
    @HasPermission(needVerifyCredit = true)
    public R demandPage(@RequestBody PageForm param, @LoginUser UserEntity userEntity) {
        QueryWrapper<Quotation> wrapper = new QueryWrapper<>();
        wrapper.eq("demand_id", param);
        Page<Quotation> page = new Page<>(param.getPage(), param.getSize());
        IPage<Quotation> quotationPage = quotationService.page(page, wrapper);
        return R.ok(quotationPage.toString());
    }

}