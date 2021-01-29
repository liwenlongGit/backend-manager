package com.lwl.backendmanager.authority.controller;

import com.alibaba.fastjson.JSON;
import com.lwl.backendmanager.authority.bean.DO.MRole;
import com.lwl.backendmanager.authority.bean.DO.MUser;
import com.lwl.backendmanager.authority.bean.ResponseData;
import com.lwl.backendmanager.authority.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * 角色操作
 * @author Administrator
 */
@RequestMapping(value = "/role")
@RestController
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "find")
    public String findRole(MRole mRole){
        List<MRole> roleServiceRole = roleService.findRole(mRole);
        ResponseData data = new ResponseData();
        data.setCode(0);
        data.setData(roleServiceRole);
        data.setMsg("success");
        data.setCount(100);

        return JSON.toJSONString(data);
    }

    @PostMapping(value = "/add")
    public String addRole(@RequestBody MRole mRole, HttpServletRequest request){
        HttpSession session = request.getSession();
        MUser user = (MUser) session.getAttribute("user");
        mRole.setRoleId(UUID.randomUUID().toString().replace("-",""));
        mRole.setCreater(user.getUserId());
        int addRole = roleService.addRole(mRole);
        ResponseData data  =new ResponseData();
        if (addRole == 1){
            data.setCode(200);
            data.setMsg("succces");
        }else{
            data.setCode(500);
            data.setMsg("fail");
        }
        return JSON.toJSONString(data);
    }

    @PostMapping(value = "/update")
    public String updateRole(@RequestBody MRole mRole){

        int updateRole = roleService.updateRole(mRole);
        ResponseData data  =new ResponseData();
        if (updateRole == 1){
            data.setCode(200);
            data.setMsg("succces");
        }else{
            data.setCode(500);
            data.setMsg("fail");
        }
        return JSON.toJSONString(data);
    }

    @GetMapping(value = "/delete")
    public String deleteRole(String roleId){
        MRole mRole = new MRole();
        mRole.setRoleId(roleId);
        mRole.setStatus(true);
        ResponseData data  =new ResponseData();
        int updateRole = roleService.updateRole(mRole);
        if (updateRole == 1){
            data.setCode(200);
            data.setMsg("succces");
        }else{
            data.setCode(500);
            data.setMsg("fail");
        }
        return JSON.toJSONString(data);
    }

    @PostMapping(value = "/batchDel")
    public String batchDelete(@RequestBody List<MRole> roles){
        System.out.println(roles);
        int count = roleService.batchDelete(roles);
        ResponseData data = new ResponseData();
        if (count == 0){
            data.setCode(500);
            data.setMsg("fail");
        }else{
            data.setCode(200);
            data.setMsg("success");
        }
        return JSON.toJSONString(data);
    }
}
