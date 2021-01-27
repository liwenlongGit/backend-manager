package com.lwl.backendmanager.authority.service;

import com.lwl.backendmanager.authority.bean.DO.MUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserService {
    /**
     * 添加用户
     * @param mUser
     * @return
     */
    int addUser(MUser mUser);


    /**
     * 条件查询用户
     * @param mUser
     * @return
     */
    List<MUser> findByParam(MUser mUser);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUser(MUser user);

    int batchDel(List<MUser> users);
}
