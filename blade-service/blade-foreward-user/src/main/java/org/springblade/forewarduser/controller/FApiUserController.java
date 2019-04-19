package org.springblade.forewarduser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.ChangePassworld;
import org.springblade.common.form.UserForm;
import org.springblade.common.respond.UserDto;
import org.springblade.core.tool.api.R;
import org.springblade.forewarduser.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linxiumeng
 * @since 2019-02-13 14:02:00
 */
@Api(tags = "用户表(TbFuser)表操作控制器", description = " * @author linxiumeng")
@RestController
@RequestMapping("/api/fuser")
public class FApiUserController {

    @Resource
    private UserService userService;


    @GetMapping("detail")
    public org.springblade.core.tool.api.R getUserWithApi(@RequestParam("userId")Long userId){
        R r = R.status(true);
        r.setData(userService.getById(userId));
        return r;
    }

}