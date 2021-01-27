package com.lwl.backendmanager.authority.service.impl;

import com.lwl.backendmanager.authority.bean.DO.MRole;
import com.lwl.backendmanager.authority.dao.MRoleDao;
import com.lwl.backendmanager.authority.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private MRoleDao mRoleDao;
    @Override
    public List<MRole> findRole(MRole mRole) {
        return mRoleDao.selectByparam(mRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addRole(MRole mRole) {
        return mRoleDao.insertSelective(mRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(MRole mRole) {
        return mRoleDao.updateByPrimaryKeySelective(mRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRole(MRole role) {
        return mRoleDao.updateByPrimaryKeySelective(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDelete(List<MRole> roles) {
        int count = roles.size();
        System.out.println(count);
        int batchDelete = mRoleDao.batchDelete(roles);
        System.out.println(batchDelete);
        if (count != 0 && batchDelete == 0){
            System.out.println("手动回滚");
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return batchDelete;
    }
}
