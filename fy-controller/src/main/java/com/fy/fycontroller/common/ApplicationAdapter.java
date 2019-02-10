package com.fy.fycontroller.common;

import com.fy.fycontroller.api.BaseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description: 静态资源配置
 * @author: cnc
 * @date: 2019-01-14 20:12
 */
@Configuration
public class ApplicationAdapter extends WebMvcConfigurationSupport {

//    @Autowired
//    private AuthorizationInterceptor authorizationInterceptor;

//    private String[] excludePathPatterns = new String[] {
//            "/v1/partner/register",
//            "/v1/partner/toRegister",
//            "/v1/partner/toLogin",
//            "/v1/partner/getAreaCode",
//            "/v1/partner/login"
//    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**").excludePathPatterns(excludePathPatterns);
//    }
}
