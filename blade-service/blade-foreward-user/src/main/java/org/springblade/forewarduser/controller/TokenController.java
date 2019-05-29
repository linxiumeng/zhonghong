package org.springblade.forewarduser.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.swagger.annotations.*;
import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.TokenEntity;
import org.springblade.core.tool.api.IResultCode;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.BladeJavaTimeModule;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.forewarduser.service.TokenService;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/user/token")
@Api(tags = "用户token")
public class TokenController {

    @Resource
    TokenService tokenService;

    /**
     * 获取用户详情
     * @param token
     * @return
     */
    @GetMapping("/detail")
   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "query", dataType = "string"),
    })*/
    @ApiOperation(value = "获取用户详情", notes = "传入token")
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


}
