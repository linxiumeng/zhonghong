package org.springblade.forewarduser.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
        ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().createXmlMapper(false).build();
        //设置地点为中国
        objectMapper.setLocale(Locale.CHINA);
        //去掉默认的时间戳格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //设置为中国上海时区
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        //序列化时，日期的统一格式
        objectMapper.setDateFormat(new SimpleDateFormat(DateUtil.PATTERN_DATETIME, Locale.CHINA));
        //序列化处理
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        objectMapper.findAndRegisterModules();
        //失败处理
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //单引号处理
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //反序列化时，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //日期格式化
        objectMapper.registerModule(new BladeJavaTimeModule());
        objectMapper.findAndRegisterModules();


        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        R r = R.status(true);
        String text = objectMapper.writeValueAsString(r);
        System.out.println(text);
        R r1 = objectMapper.readValue(text,R.class);
        System.out.println(r1);
    }

}
