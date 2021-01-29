package com.lwl.backendmanager.authority.controller;

import com.alibaba.fastjson.JSON;
import com.lwl.backendmanager.authority.bean.DO.MResource;
import com.lwl.backendmanager.authority.bean.DO.MUser;
import com.lwl.backendmanager.authority.bean.ResponseData;
import com.lwl.backendmanager.authority.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * 操作资源数据
 * @author Administrator
 */
@RequestMapping(value = "/resource")
@RestController
public class ResourceController {

    @Autowired
    private ResourceService service;

    @GetMapping(value = "/find")
    public ResponseData findResource(MResource resource){
        List<MResource> response = service.findResource(resource);
        ResponseData data = new ResponseData();
        data.setCode(0);
        data.setMsg("success");
        data.setData(response);
        data.setCount(100);
        return data;
    }

    @PostMapping(value = "/add")
    public String addResource(@RequestBody MResource resource, HttpServletRequest request){
        System.out.println(resource);
        HttpSession session = request.getSession();
        MUser user = (MUser) session.getAttribute("user");
        resource.setCreater(user.getUserId());
        resource.setResourceId(UUID.randomUUID().toString().replace("-",""));

        int addResource = service.addResource(resource);
        ResponseData data = new ResponseData();
        if (addResource == 0){
            data.setCode(500);
            data.setMsg("fail");
        }else{
            data.setCode(200);
            data.setMsg("success");
            data.setCount(addResource);
        }
        return JSON.toJSONString(data);
    }

    @PostMapping(value = "/update")
    public String updateResource(@RequestBody MResource resource){
        int updateResource = service.updateResource(resource);
        ResponseData data = new ResponseData();
        if (updateResource == 1){
            data.setCode(200);
            data.setMsg("success");
            data.setCount(updateResource);
        }else{
            data.setCount(500);
            data.setMsg("fail");
            data.setCount(0);
        }
        return JSON.toJSONString(data);
    }

    @GetMapping(value = "/delete")
    public String deleteResource(@RequestParam String resourceId){
        System.out.println(resourceId);
        MResource resource = new MResource();
        resource.setResourceId(resourceId);
        resource.setStatus(true);
        int deleteResource = service.deleteResource(resource);
        ResponseData data = new ResponseData();
        if (deleteResource == 1){
            data.setCode(200);
            data.setMsg("success");
            data.setCount(deleteResource);
        }else{
            data.setCode(500);
            data.setMsg("fail");
            data.setCount(0);
        }

        return JSON.toJSONString(data);
    }

    @PostMapping(value = "/batch")
    public String batchDelete(@RequestBody List<MResource> resources){
        int batchDelete = service.batchDelete(resources);
        ResponseData data = new ResponseData();
        if (batchDelete >= 1){
            data.setCode(200);
            data.setMsg("succes");
        }else{
            data.setCode(500);
            data.setMsg("fail");
        }
        return JSON.toJSONString(data);
    }
}
