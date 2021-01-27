package com.lwl.backendmanager.authority.bean.DO;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * m_user
 * @author 
 */
@Data
public class MUser implements Serializable {
    private Integer id;

    private String userId;

    private String userName;

    private String snake;

    private String email;

    private String idCard;

    private Date createTime;

    private String creater;

    private String password;

    private String checkCode;

    private String phone;

    private String headPhoto;

    private boolean status;

    private static final long serialVersionUID = 1L;
}