package com.lwl.backendmanager.authority.bean.DO;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_log
 * @author 
 */
@Data
public class SysLog implements Serializable {
    private Long id;

    private String operator;

    /**
     * 0:select 1：insert 2：update 3：delete
     */
    private Byte operatorType;

    private Date operatorTime;

    private String operatorId;

    private static final long serialVersionUID = 1L;
}