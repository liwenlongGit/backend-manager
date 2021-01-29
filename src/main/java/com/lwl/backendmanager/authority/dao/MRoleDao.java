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

    /**
     * 批量删除角色
     * @param mRole
     * @return
     */
    int batchDelete(@Param("roles") List<MRole> mRole);

    /**
     * 插入角色资源关系
     * @param resources
     * @param roleId
     * @return
     */
    int saveRoleResource(@Param("resources") List<String> resources,@Param("roleId") String roleId);

    /**
     * 删除角色资源关系
     * @param roleId
     * @return
     */
    int deleteRoleResource(@Param("roleId") String roleId);
}