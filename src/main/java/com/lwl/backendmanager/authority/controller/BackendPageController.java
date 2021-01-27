package com.lwl.backendmanager.authority.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 页面请求
 * @author Administrator
 */
@Controller
public class BackendPageController {

    @GetMapping(value = "/index")
    public ModelAndView indexPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("backend/index");
        HttpSession session = request.getSession();
        modelAndView.addObject("user",session.getAttribute("user"));
        return modelAndView;
    }

    @GetMapping(value = "/user.html")
    public ModelAndView userPage(){
        ModelAndView modelAndView = new ModelAndView("backend/User");
        return modelAndView;
    }

    /**
     * 修改用户密码和头像的页面
     * @param request
     * @return
     */
    @GetMapping(value = "update_page")
    public ModelAndView updatePage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (Objects.isNull(user)){
            modelAndView.setViewName("redirect:/login.html");
            return modelAndView;
        }
        modelAndView.setViewName("backend/userUpdate");
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    /**
     * 添加用户信息页面
     * @param request
     * @return
     */
    @GetMapping(value = "/user_page.html")
    public ModelAndView addUserPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("backend/addUser");

        return modelAndView;
    }

    @GetMapping(value = "/role.html")
    public ModelAndView rolePage(){
        ModelAndView modelAndView = new ModelAndView("backend/Role");
        return modelAndView;
    }
    @GetMapping(value = "/resource.html")
    public ModelAndView resourcePage(){
        ModelAndView modelAndView = new ModelAndView("backend/Resource");
        return modelAndView;
    }

    @GetMapping(value = "/login.html")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("backend/login");
        return modelAndView;
    }

}
