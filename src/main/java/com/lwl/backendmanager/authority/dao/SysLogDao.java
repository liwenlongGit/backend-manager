package com.lwl.backendmanager.authority.dao;

import com.lwl.backendmanager.authority.bean.DO.SysLog;

public interface SysLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}