package org.springblade.forewarduser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.CreditForm;
import org.springblade.common.form.UserForm;
import org.springblade.common.utils.R;
import org.springblade.forewarduser.service.CreditService;
import org.springblade.forewarduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author linxiumeng
 * @since 2019-03-07 16:15:37
 */
@Api(tags = "授信表(Credit)表操作控制器", description = " * @author linxiumeng")
@RestController
@RequestMapping("api/credit")
public class CreditController {
    private CreditService creditService;

    private UserService userService;

    @Autowired
    public CreditController(CreditService creditService, UserService userService) {
        this.creditService = creditService;
        this.userService = userService;
    }

    /*@ApiOperation(value = "采购商申请授信接口")*/
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "采购商申请授信", paramType = "query", dataType = "string",dataTypeClass = CreditForm.class)
    })*/
    @ApiOperation(value = "采购商申请授信接口", notes = "传入param" )
    @Login
    @PostMapping("creat")
    @Transactional(rollbackFor = Exception.class)
    public R creat(@RequestBody CreditForm param, @LoginUser UserEntity user) {
        boolean flag = creditService.createCredit(param, user);
        if (flag) {
            return R.ok();
        } else {
            return R.error("申请提交失败");
        }
    }
}