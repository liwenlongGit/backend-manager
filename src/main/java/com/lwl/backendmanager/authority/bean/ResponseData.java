package com.lwl.backendmanager.authority.bean;


import lombok.Data;

@Data
public class ResponseData {
    /**
     * 状态码，0：表示成功 1：失败
     */
    private int code;
    private String msg;
    private int count;
    private Object data;
}
