package org.springblade.bgadmin.common.config;

import org.springblade.bgadmin.common.jackson.OverrideMappingApiJackson2HttpMessageConverter;
import org.springblade.core.tool.utils.Charsets;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * WebMvc配置
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.0.0 2018-01-25
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    SessionInterceptor sessionInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //   registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        //    registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/templates/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").
                excludePathPatterns(Arrays.asList("/swagger/**",
                        "/sys/login",
                        "/captcha.jpg",
                        "/favicon.ico",
                        "/login.html",
                        "/",
                        "/static/**",
                        "/swagger/**",
                        "/v2/api-docs",
                        "/v2/api-docs-ext/**",
                        "/v2/**",
                        "/webjars/**",
                        "/statics/**",
                        "/**/*.jpg", "/*.jpg",
                        "/**/*.png", "/*.png",
                        "/**/*.jpeg", "/*.jpeg"));
    }

    //    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjemctMapper();
//
//        //生成json时，将所有Long转换成String
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
//
//        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
//        converters.add(0, jackson2HttpMessageConverter);
//    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(x -> x instanceof StringHttpMessageConverter || x instanceof AbstractJackson2HttpMessageConverter);
        converters.add(new StringHttpMessageConverter(Charsets.UTF_8));
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new ResourceRegionHttpMessageConverter());
        converters.add(new OverrideMappingApiJackson2HttpMessageConverter());
    }

}
