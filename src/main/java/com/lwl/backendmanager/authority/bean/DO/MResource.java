package com.lwl.backendmanager.authority.bean.DO;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * m_resource
 * @author 
 */
@Data
public class MResource implements Serializable {
    private Integer id;

    private String resourceId;

    private String resourceName;

    /**
     * 0:菜单  1：页面  2：按钮
     */
    private Short resourceType;

    private Date createTime;

    private String creater;

    private String parentId;

    private boolean status;

    private static final long serialVersionUID = 1L;
}