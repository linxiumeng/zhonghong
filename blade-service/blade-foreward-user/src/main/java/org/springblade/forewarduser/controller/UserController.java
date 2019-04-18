package org.springblade.forewarduser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.ChangePassworld;
import org.springblade.common.form.UserForm;
import org.springblade.common.respond.UserDto;
import org.springblade.common.utils.R;
import org.springblade.forewarduser.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("selectbyid")
    @Login
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
    @ApiOperation(value = "判断用户状态")
    @PostMapping("selectStatus")
    @Login
    public R selectStatus(@LoginUser UserEntity currentUser) {
        return R.ok().put("status", currentUser.getStatus());
    }

    /**
     * 修改用户密码
     *
     * @param pw          新密码
     * @param currentUser 当前用户
     * @return 成功
     */
    @ApiOperation(value = "修改用户密码")
    @PostMapping("changepw")
    @Login
    public R changepw(@RequestBody ChangePassworld pw, @LoginUser UserEntity currentUser) {
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
    @ApiOperation(value = "修改用户表(user)数据")
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

}