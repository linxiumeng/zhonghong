package org.springblade.pay.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.entity.AccountWithdraw;
import org.springblade.common.entity.FinancingWarehouse;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.FinancingWarehouseForm;
import org.springblade.common.utils.R;
import org.springblade.common.validation.group.UpdateGroup;
import org.springblade.pay.service.AccountWithdrawService;
import org.springblade.pay.service.FinancingWarehouseService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;


/**
 * 余额详情表(AccountDetail)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-12 11:48:32
 */
@RestController
@RequestMapping("/financingWarehouse")
public class FinancingWarehouseController {

    @Resource
    FinancingWarehouseService financingWarehouseService;


    @PostMapping("/create")
    @Login
    public R createFinancingWarehouse(@RequestBody @Validated FinancingWarehouseForm form, @LoginUser UserEntity userEntity){

        FinancingWarehouse financingWarehouse = new FinancingWarehouse();

        //复制属性
        BeanUtils.copyProperties(form,financingWarehouse);
        financingWarehouse.setStatus(0);
        financingWarehouse.setCurrentPeriod(0);
        financingWarehouse.setId(null);
        financingWarehouse.setUserId(userEntity.getUserId());
        financingWarehouseService.save(financingWarehouse);

        return R.ok();
    }

    @PostMapping("/detail")
    @Login
    public R showFinancingWarehouse(@RequestBody @Validated(UpdateGroup.class) FinancingWarehouseForm form, @LoginUser UserEntity user){

        FinancingWarehouse financingWarehouse = null;



        return R.ok().put("result",financingWarehouse);
    }

    @PostMapping("/list")
    @Login
    public R listFinancingWarehouse(@RequestBody FinancingWarehouseForm form,@LoginUser UserEntity userEntity){

        IPage page = financingWarehouseService.listFinancingWarehouse(userEntity.getUserId(),new Page(form.getPage(),form.getSize()));

        return R.ok().put("result",page);
    }

    
}