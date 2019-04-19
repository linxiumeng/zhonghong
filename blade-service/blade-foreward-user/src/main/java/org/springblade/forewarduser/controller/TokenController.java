package org.springblade.forewarduser.controller;

import org.springblade.common.entity.TokenEntity;
import org.springblade.core.tool.api.R;
import org.springblade.forewarduser.service.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/token")
public class TokenController {

    @Resource
    TokenService tokenService;

    /**
     * 获取用户详情
     * @param token
     * @return
     */
    @GetMapping("/detail")
    R<TokenEntity> getTokenEntityByUserId(@RequestParam("token") String token){
        R r = R.status(true);
        r.setData(tokenService.queryByToken(token));
        return r;

    }

}
