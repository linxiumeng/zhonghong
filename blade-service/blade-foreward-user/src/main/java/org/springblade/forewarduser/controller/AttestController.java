package org.springblade.forewarduser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.entity.Attest;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.AttestForm;
import org.springblade.common.form.UserForm;
import org.springblade.common.utils.R;
import org.springblade.common.validation.group.InsertGroup;
import org.springblade.forewarduser.service.AttestService;
import org.springblade.forewarduser.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author linxiumeng
 * @since 2019-03-07 16:00:29
 */
@Api(tags = "(Attest)表操作控制器",description = " * @author linxiumeng")
@RestController
@RequestMapping("api/attest")
public class AttestController {
    private AttestService attestService;
    @Autowired
    private UserService userService;
    
    @Autowired
    public AttestController(AttestService attestService) {
        this.attestService = attestService;
    }

    /*@ApiOperation(value = "采购商认证接口")*/
    @Login
    @PostMapping("creat")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "采购商认证", paramType = "query", dataType = "string",dataTypeClass = AttestForm.class)
    })*/
    @ApiOperation(value = "采购商认证接口", notes = "传入userform" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
    public R creat(@RequestBody @Validated(InsertGroup.class) AttestForm param, @LoginUser UserEntity user){
        param.setUserId(user.getUserId());
        Attest attest = new Attest();
        BeanUtils.copyProperties(param,attest);
        attestService.createAttest(attest);
        return R.ok();
    }

    /*@ApiOperation(value = "供应商认证接口")
    @Login
    @PostMapping("create_provider")
    public R creatProvider(@RequestBody Attest param, @LoginUser UserEntity user){
        UserEntity param1 = this.userService.selectById(param.getUserId());
        param1.setProviderStatus(ProviderStatusEnum.INVERIFICATION.ordinal());
        this.userService.updateById(param1);
        param.setUserId(user.getUserId());
        param.setType(AttestUserTypeEnum.PROVIDER.ordinal());
        attestService.insertOrUpdate(param);
        return R.ok();
    }*/

}