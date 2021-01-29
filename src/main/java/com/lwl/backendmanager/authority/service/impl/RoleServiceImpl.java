package com.lwl.backendmanager.authority.service.impl;

import com.lwl.backendmanager.authority.bean.DO.MRole;
import com.lwl.backendmanager.authority.dao.MRoleDao;
import com.lwl.backendmanager.authority.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        int insertSelective = mRoleDao.insertSelective(mRole);
        if (insertSelective > 0 && !mRole.getResources().isEmpty()){
            int saveRoleResource = mRoleDao.saveRoleResource(mRole.getResources(), mRole.getRoleId());
            if (saveRoleResource == 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                insertSelective = 0;
            }
        }
        return insertSelective;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(MRole mRole) {
        int update = mRoleDao.updateByPrimaryKeySelective(mRole);
        if (update > 0){
            List<String> resources = new ArrayList<>();
            boolean retain = mRole.getResources().stream().collect(Collectors.joining())
                    .equals(resources.stream().collect(Collectors.joining()));
            if (!retain){
                // 如果两个集合不相同，需要修改角色资源关系，先删再插。后续靠，考虑其他方式
                int resource = mRoleDao.deleteRoleResource(mRole.getRoleId());
                int roleResource = mRoleDao.saveRoleResource(mRole.getResources(), mRole.getRoleId());
                if (resource == 0 || roleResource == 0){
                    // 必须两个操作全部成功，否则回滚事务
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    update = 0;
                }
            }
        }
        return update;
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
