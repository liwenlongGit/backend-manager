package com.lwl.backendmanager.authority.dao;

import com.lwl.backendmanager.authority.bean.DO.MResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MResourceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MResource record);

    int insertSelective(MResource record);

    List<MResource> selectByPrimaryKey(MResource resource);

    int updateByPrimaryKeySelective(MResource record);

    int updateByPrimaryKey(MResource record);

    int batchDel(@Param("resources") List<MResource> resources);
}