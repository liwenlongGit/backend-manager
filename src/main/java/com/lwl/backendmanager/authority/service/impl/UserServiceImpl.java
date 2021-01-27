package com.lwl.backendmanager.authority.service.impl;

import com.lwl.backendmanager.authority.bean.DO.MUser;
import com.lwl.backendmanager.authority.dao.MUserDao;
import com.lwl.backendmanager.authority.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
        return mUserDao.insertSelective(mUser);
    }

    @Override
    public List<MUser> findByParam(MUser mUser) {
        return mUserDao.selectByParm(mUser);
    }

    @Override
    public int updateUser(MUser user) {
        int count = mUserDao.updateByPrimaryKeySelective(user);
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
