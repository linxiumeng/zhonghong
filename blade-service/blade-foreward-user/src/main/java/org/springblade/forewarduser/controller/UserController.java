package org.springblade.forewarduser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.HasPermission;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.enums.PermissionCodeEnum;
import org.springblade.common.form.ChangePassword;
import org.springblade.common.form.UserForm;
import org.springblade.common.respond.UserDto;
import org.springblade.common.utils.R;
import org.springblade.forewarduser.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linxiumeng
 * @since 2019-02-13 14:02:00
 */
@Api(tags = "用户表(TbFuser)表操作控制器", description = " * @author linxiumeng")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

 //   @Resource(name = "userBeanCopier")
 //   private BeanCopier beanCopier;

    /**
     * 查询用户实体
     *
     * @param userForm 用户表单
     * @return 用户传输数据
     */
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "userForm", value = "用户表单", paramType = "query", dataType = "string",dataTypeClass = UserForm.class)
    })*/
    @ApiOperation(value = "查询用户实体", notes = "" )
    @PostMapping("selectbyid")
    @Login
    @HasPermission(needVerifyUser = true)
    public R selectOne(@RequestBody UserForm userForm) {
        UserEntity userEntity = this.userService.selectByIdFromCache(userForm.getUserId());
        UserDto userDto = null;
        if (userEntity != null) {
            userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
        }
        return R.ok().put("user", userDto);
    }

    /**
     * 获取用户状态
     *
     * @param currentUser 当前用户
     * @return 状态
     */
   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "UserEntity", value = "获取用户状态", paramType = "query", dataType = "string",dataTypeClass = UserEntity.class)
    })*/
    @ApiOperation(value = "判断用户状态", notes = "" )
    @PostMapping("selectStatus")
    @Login
    public R selectStatus(@LoginUser UserEntity currentUser) {
        return R.ok().put("status", currentUser.getStatus());
    }

    /**
     *
     *
     * @param pw          新密码
     * @param currentUser 当前用户
     * @return 成功
     */
   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "UserEntity", value = "", paramType = "query", dataType = "string",dataTypeClass = UserEntity.class)
    })*/
    @ApiOperation(value = "修改用户密码", notes = "" )
    @PostMapping("changepw")
    @Login
    public R changepw(@RequestBody ChangePassword pw, @LoginUser UserEntity currentUser) {
        this.userService.changePw(pw, currentUser);
        return R.ok();
    }


    /**
     * 这里增加了缓存
     *
     * @param userForm    用户修改的实体
     * @param currentUser 登录用户
     * @return
     */
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "UserForm", value = "这里增加了缓存", paramType = "query", dataType = "string",dataTypeClass = UserForm.class)
    })*/
    @ApiOperation(value = "修改用户表(user)数据", notes = "" )
    @PostMapping("updateUser")
    @Login
    public R update(@RequestBody UserForm userForm, @LoginUser UserEntity currentUser) {
        UserEntity param2 = new UserEntity();
        //设置修改参数
        param2.setUserId(currentUser.getUserId());
        param2.setContacts(userForm.getContacts());
        param2.setContactNumber(userForm.getContactNumber());
        param2.setContactAddress(userForm.getContactAddress());
        param2.setMail(userForm.getMail());
        //修改
        return R.ok().put("row", this.userService.updateByIdWithCache(param2));
    }

    /**
     * 判断页面跳转
     * @param currentUser
     * @return
     */
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "UserEntity", value = "判断页面跳转", paramType = "query", dataType = "string",dataTypeClass = UserEntity.class)
    })*/
    @ApiOperation(value = "判断页面跳转", notes = "" )
    @PostMapping("userValidation")
    @Login
    public R userValidation(@LoginUser UserEntity currentUser){
        Integer status = currentUser.getStatus();
        Integer creditStatus = currentUser.getCreditStatus();
        if(status== 0){
            return R.ok().put("result", PermissionCodeEnum.ZERE.getStatus()).put("msg",PermissionCodeEnum.ZERE.getDesc());
        }else if(status== 1){
            return R.ok().put("result",PermissionCodeEnum.ONE.getStatus()).put("msg",PermissionCodeEnum.ONE.getDesc());
        }else if(status== 2){
            return R.ok().put("result",PermissionCodeEnum.TWO.getStatus()).put("msg",PermissionCodeEnum.TWO.getDesc());
        }else if(status== 3) {
            if (creditStatus == 0) {

                return R.ok().put("result", PermissionCodeEnum.FOUR.getStatus()).put("msg", PermissionCodeEnum.FOUR.getDesc());
            } else if (creditStatus == 1) {
                return R.ok().put("result", PermissionCodeEnum.FIVE.getStatus()).put("msg", PermissionCodeEnum.FIVE.getDesc());
            } else if (creditStatus == 2) {
                return R.ok().put("result", PermissionCodeEnum.SIX.getStatus()).put("msg", PermissionCodeEnum.SIX.getDesc());
            } else {
                return R.ok().put("result", PermissionCodeEnum.SEVER.getStatus()).put("msg", PermissionCodeEnum.SEVER.getDesc());
            }
        }
        return R.error("参数错误");
    }



}