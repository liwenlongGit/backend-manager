package com.lwl.backendmanager.authority.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final MyInterceptor myInterceptor;
    private final static List<String> EXCLUDE_PATH = Arrays
            .asList("/","/**.html","/login","/register","/loginout","/authCode","/img/**","/layui/**","/jquery/**");

    private static String LOGIN = "backend/login";
    /**
     * 初始化，获取Bean容器中查找MyInterceptor的对象，并赋值该变量
     * @param myInterceptor
     */
    @Autowired
    public WebConfig(MyInterceptor myInterceptor) {
        this.myInterceptor = myInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATH);
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName(LOGIN);
    }
}
