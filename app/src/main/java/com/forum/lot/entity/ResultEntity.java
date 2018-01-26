package com.forum.lot.entity;

/**
 * 网络访问返回结果 on 2018/1/27.
 */

public class ResultEntity <T>{
    public int code;
    public T data;
    public String msg;
    public long system_time;
    public String version;
}
