package com.lwl.backendmanager.authority.dao;

import com.lwl.backendmanager.authority.bean.DO.MUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户持久层
 * @author Administrator
 */
public interface MUserDao {
    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(MUser record);

    /**
     * 添加
     * @param record
     * @return
     */
    int insertSelective(MUser record);

    /**
     * 查询
     * @param id
     * @return
     */
    MUser selectByPrimaryKey(Integer id);

    /**
     * 查询
     * @return
     */
    List<MUser> selectAll();

    /**
     * 查询
     * @param mUser
     * @return
     */
    List<MUser> selectByParm(MUser mUser);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MUser record);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(MUser record);

    int batchDel(@Param("users") List<MUser> users);
}