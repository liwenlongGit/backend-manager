package com.lwl.backendmanager.authority.service;

import com.lwl.backendmanager.authority.bean.DO.MResource;

import java.util.List;

/**
 * @author Administrator
 */
public interface ResourceService {

    List<MResource> findResource(MResource resource);

    int addResource(MResource resource);

    int updateResource(MResource resource);

    int deleteResource(MResource resource);

    int batchDelete(List<MResource> resources);

}
