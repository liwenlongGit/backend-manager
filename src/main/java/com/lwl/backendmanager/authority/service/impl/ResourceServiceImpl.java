package com.lwl.backendmanager.authority.service.impl;

import com.lwl.backendmanager.authority.bean.DO.MResource;
import com.lwl.backendmanager.authority.dao.MResourceDao;
import com.lwl.backendmanager.authority.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private MResourceDao resourceDao;
    @Override
    public List<MResource> findResource(MResource resource) {
        return resourceDao.selectByPrimaryKey(resource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addResource(MResource resource) {
        return resourceDao.insertSelective(resource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateResource(MResource resource) {
        return resourceDao.updateByPrimaryKeySelective(resource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteResource(MResource resource) {
        return resourceDao.updateByPrimaryKeySelective(resource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDelete(List<MResource> resources) {
        int count = resources.size();
        int batchCount = 0;
        if (count >= 1){
            batchCount = resourceDao.batchDel(resources);
        }

        return batchCount;
    }
}
