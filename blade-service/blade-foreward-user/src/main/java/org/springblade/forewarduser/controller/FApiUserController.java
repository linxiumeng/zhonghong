package org.springblade.forewarduser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.ChangePassworld;
import org.springblade.common.form.UserForm;
import org.springblade.common.respond.UserDto;
import org.springblade.core.tool.api.R;
import org.springblade.forewarduser.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
        UserEntity userEntity = userService.getById(userId);
        R r = R.status(true);
        if(userEntity != null) {
            r.setData(userEntity);
        }else{
            r.setCode(FeignResultCodeConstant.ENTITY_NOT_EXISTS);
        }

        return r;
    }

    @GetMapping("batchGetByIds")
    public org.springblade.core.tool.api.R batchGetWithApi(@RequestParam("userIds") List<Long> userids){
        Collection<UserEntity> userEntities = userService.listByIds(userids);
        R r = R.status(true);
        r.setData(userEntities);
        return r;
    }

}