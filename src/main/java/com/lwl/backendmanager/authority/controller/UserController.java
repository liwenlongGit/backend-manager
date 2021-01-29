package com.lwl.backendmanager.authority.controller;

import com.alibaba.fastjson.JSON;
import com.lwl.backendmanager.authority.bean.DO.MUser;
import com.lwl.backendmanager.authority.bean.ResponseData;
import com.lwl.backendmanager.authority.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 用户操作
 * @author Administrator
 */
@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/find" ,method = RequestMethod.GET)
    public ResponseData findAllUser(MUser user){
        List<MUser> mUsers = userService.findByParam(user);
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMsg("SUCCESS");
        responseData.setData(mUsers);
        responseData.setCount(mUsers.size());
        return responseData;
    }

    @PostMapping(value = "/add")
    public String addUser(@RequestBody MUser mUser){
        System.out.println(mUser);
        mUser.setUserId(UUID.randomUUID().toString().replace("-",""));
        mUser.setCreater("111");
        int count = userService.addUser(mUser);
        ResponseData responseData = new ResponseData();
        if (count == 0){
            responseData.setCode(500);
            responseData.setMsg("FAILS");
        }else{
            responseData.setCode(200);
            responseData.setMsg("SUCCESS");
        }
        return JSON.toJSONString(responseData);
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam String userId){
        MUser mUser = new MUser();
        mUser.setUserId(userId);
        mUser.setStatus(true);
        int updateUser = userService.updateUser(mUser);
        ResponseData responseData = new ResponseData();
        if (updateUser == 0){
            responseData.setCode(500);
            responseData.setMsg("FAILS");
        }else{
            responseData.setCode(200);
            responseData.setMsg("SUCCESS");
        }
        return JSON.toJSONString(responseData);
    }

    @RequestMapping(value = "/update")
    public String updateUser(@RequestBody MUser user){
        int updateUser = userService.updateUser(user);
        ResponseData responseData = new ResponseData();
        if (updateUser ==1){
            responseData.setCode(200);
            responseData.setMsg("success");
        }else{
            responseData.setCode(500);
            responseData.setMsg("fail");
        }
        return JSON.toJSONString(responseData);
    }

    @RequestMapping(value = "/batchDel")
    public String batchUser(@RequestBody List<MUser> user){
        int updateUser = userService.batchDel(user);
        ResponseData responseData = new ResponseData();
        if (updateUser ==1){
            responseData.setCode(200);
            responseData.setMsg("success");
        }else{
            responseData.setCode(500);
            responseData.setMsg("fail");
        }
        return JSON.toJSONString(responseData);
    }

}
