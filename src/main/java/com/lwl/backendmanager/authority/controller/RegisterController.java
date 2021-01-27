package com.lwl.backendmanager.authority.controller;

import com.alibaba.fastjson.JSON;
import com.lwl.backendmanager.authority.bean.DO.MUser;
import com.lwl.backendmanager.authority.bean.ResponseData;
import com.lwl.backendmanager.authority.config.GenerateCode;
import com.lwl.backendmanager.authority.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 用户注册登录
 * @author Administrator
 */
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    /**
     * 去除 l、o、I、O、0、1
     * 目的避免混淆
     */
    private static final char[] CHAR_ARRAY = "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789".toCharArray();

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
    public ModelAndView login(MUser user,HttpServletRequest request){
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        ModelAndView modelAndView = new ModelAndView();
        if (!user.getCheckCode().equalsIgnoreCase(checkCode)){
            modelAndView.setViewName("backend/login");
            modelAndView.addObject("error","验证码错误");
            modelAndView.addObject("user",user);
            return modelAndView;
        }
        List<MUser> byParam = userService.findByParam(user);
        if (byParam.isEmpty()){
            modelAndView.setViewName("backend/login");
            modelAndView.addObject("error","用户账号密码错误");
            modelAndView.addObject("user",user);
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/index");
        modelAndView.addObject("user", byParam.get(0));
        session.setAttribute("user",byParam.get(0));
        session.setMaxInactiveInterval(3600);
        return modelAndView;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping(value = "/register")
    public ModelAndView register(MUser user){
        ModelAndView modelAndView = new ModelAndView("backend/login");
        int i = userService.addUser(user);
        if (i == 1){
            modelAndView.addObject("code", 200);
            modelAndView.addObject("msg","注册成功");
        }else{
            modelAndView.addObject("code",500);
            modelAndView.addObject("msg","注册失败");
        }
        return modelAndView;
    }

    /**
     * 退出登录
     * @param request
     * @param response
     */
    @GetMapping(value = "/loginout")
    public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.setMaxInactiveInterval(0);
        ModelAndView modelAndView = new ModelAndView("redirect:/login.html");
        return modelAndView;

    }

    /**
     * 校验信息
     * @param password
     * @return
     */
    @GetMapping(value = "/vertify")
    public String verify(@RequestParam String password, HttpServletRequest request){
        HttpSession session = request.getSession();
        MUser user = (MUser) session.getAttribute("user");

        return null;
    }
    /**
     * 获取验证码
     * @param request
     * @return
     */
    @GetMapping(value = "/authCode")
    public ResponseEntity<byte[]> generator(HttpServletRequest request){
        String checkCode = RandomStringUtils.random(4, CHAR_ARRAY);
        //TODO 直接返回byte数据
        try(ByteArrayOutputStream os = (ByteArrayOutputStream) GenerateCode.generator(102, 25, checkCode);){
            byte[] bytes = os.toByteArray();
            request.getSession().setAttribute("checkCode", checkCode);
            HttpHeaders responseHeaders = initHttpHeader();
            return new ResponseEntity<>(bytes, responseHeaders, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpHeaders initHttpHeader() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.IMAGE_JPEG);
        responseHeaders.setCacheControl("no-cache");
        responseHeaders.setPragma("No-cache");
        responseHeaders.setDate("Expires", 0);
        return responseHeaders;
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public String uploadImg(@RequestPart MultipartFile file,HttpServletRequest request){
        try {
            byte[] bytes = file.getBytes();
            System.out.println(bytes.length);
            String filename = file.getOriginalFilename();
            String str_path = "D:\\project\\idea\\backend-manager\\src\\main\\resources\\static\\img";
            file.transferTo(new File(str_path,filename));
            //修改用户头像信息
            MUser user = (MUser) request.getSession().getAttribute("user");
            MUser muser = new MUser();
            muser.setUserId(user.getUserId());
            muser.setHeadPhoto("/img/"+filename);
            int updateUser = userService.updateUser(muser);
            if (updateUser ==1){
                user.setHeadPhoto("/img/"+filename);
                request.setAttribute("user",user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.setMsg("success");
        return JSON.toJSONString(responseData);
    }

    @PostMapping(value = "up_pass")
    public String updatePass(String oldPassword,HttpServletRequest request){
        //首先获取用户信息
        HttpSession session = request.getSession();
        MUser user = (MUser) session.getAttribute("user");
        MUser upUser = new MUser();
        upUser.setUserId(user.getUserId());
        upUser.setPassword(oldPassword);
        int updateUser = userService.updateUser(upUser);
        ResponseData responseData = new ResponseData();
        if (updateUser == 1){
            responseData.setCode(200);
            responseData.setMsg("success");
        }else{
            responseData.setCode(500);
            responseData.setMsg("fail");
        }
        return JSON.toJSONString(responseData);

    }
}
