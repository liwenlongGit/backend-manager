package com.lwl.backendmanager.authority.service.impl;

import com.lwl.backendmanager.authority.bean.DO.MUser;
import com.lwl.backendmanager.authority.dao.MUserDao;
import com.lwl.backendmanager.authority.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {
    private final MUserDao mUserDao;

    @Autowired
    public UserServiceImpl(MUserDao mUserDao) {
        this.mUserDao = mUserDao;
    }

    /**
     * @param mUser
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(MUser mUser) {
        mUser.setUserId(UUID.randomUUID().toString().replace("-",""));
        mUser.setCreater("1111111111111");
        int count = mUserDao.insertSelective(mUser);
        // 首先判断是否需要配置角色
        if (count > 0 && !mUser.getRoles().isEmpty()){
            //添加关系
            int saveUserRole = mUserDao.saveUserRole(mUser.getRoles(), mUser.getUserId());
            if (saveUserRole == 0){
                //手动回滚事务
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                count = 0;
            }
        }
        return count;
    }

    @Override
    public List<MUser> findByParam(MUser mUser) {
        return mUserDao.selectByParm(mUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(MUser user) {
        int count = mUserDao.updateByPrimaryKeySelective(user);
        if (count > 0){
            // 判断用户角色和参数中的角色列表是否相同
            List<String> roles = new ArrayList<>();
            // 通过将两个集合转换为字符串判断是否相同
            boolean retain = user.getRoles().stream().collect(Collectors.joining())
                    .equals(roles.stream().collect(Collectors.joining()));
            if (!retain) {
                // 两个集合不相同，需要修改用户角色关系，比较快速的方法是先删除，再创建。后续考虑其他方式
                int deleteUserRole = mUserDao.deleteUserRole(user.getUserId());
                // 如果没有修改成功，回滚事务
                if (deleteUserRole == 0){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    count = 0;
                }
            }
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDel(List<MUser> users) {
        int count = users.size();
        int batch = 0;
        if (count >= 1){
            batch = mUserDao.batchDel(users);
        }
        return batch;
    }
}
