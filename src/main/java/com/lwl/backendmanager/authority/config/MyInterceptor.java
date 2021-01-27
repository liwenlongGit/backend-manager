package com.lwl.backendmanager.authority.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

/**
 * 自定义拦截器
 * @author Administrator
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    private WebApplicationContext applicationContext;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1、首先获取session对象
        HttpSession session = request.getSession();
        //2、判断session中是否包含登录信息
        Object user = session.getAttribute("user");

        //3、获取所有请求接口
        RequestMappingHandlerMapping beans = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = beans.getHandlerMethods();
        if (!Objects.isNull(user)){
            System.out.println("进入拦截器1");
            return true;
        }else{
            System.out.println("进入拦截器2");
            response.sendRedirect("/login.html");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
