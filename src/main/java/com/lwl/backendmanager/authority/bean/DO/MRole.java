package com.lwl.backendmanager.authority.bean.DO;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * m_role
 * @author 
 */
@Data
public class MRole implements Serializable {
    private Integer id;

    private String roleId;

    private String roleName;

    private Date createTime;

    private String creater;

    private boolean status;

    private static final long serialVersionUID = 1L;
}