package org.springblade.forewarduser.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.TokenEntity;
import org.springblade.core.tool.api.IResultCode;
import org.springblade.core.tool.api.R;
import org.springblade.forewarduser.service.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

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

        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if(token != null) {
            return R.data(tokenEntity);
        }else{
            R r = R.status(true);
            r.setCode(FeignResultCodeConstant.ENTITY_NOT_EXISTS);
            return r;
        }

    }

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
        R r = R.status(true);
        String text = objectMapper.writeValueAsString(r);
        System.out.println(text);
        R r1 = objectMapper.readValue(text,R.class);
        System.out.println(r1);
    }

}
