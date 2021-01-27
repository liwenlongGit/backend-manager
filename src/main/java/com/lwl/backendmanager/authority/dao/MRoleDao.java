package com.lwl.backendmanager.authority.dao;

import com.lwl.backendmanager.authority.bean.DO.MRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MRole record);

    int insertSelective(MRole record);

    MRole selectByPrimaryKey(Integer id);

    List<MRole> selectByparam(MRole mRole);

    int updateByPrimaryKeySelective(MRole record);

    int updateByPrimaryKey(MRole record);

    int batchDelete(@Param("roles") List<MRole> mRole);
}