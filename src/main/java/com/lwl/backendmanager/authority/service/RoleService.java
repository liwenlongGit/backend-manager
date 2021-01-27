package com.lwl.backendmanager.authority.service;

import com.lwl.backendmanager.authority.bean.DO.MRole;

import java.util.List;

/**
 * @author Administrator
 */
public interface RoleService {
    List<MRole> findRole(MRole mRole);
    int addRole(MRole mRole);
    int updateRole(MRole mRole);
    int deleteRole(MRole mRole);
    int batchDelete(List<MRole> roles);
}
