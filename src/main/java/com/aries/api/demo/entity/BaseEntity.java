package com.aries.api.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Author: AriesHoo on 2019/2/11 16:05
 * @E-Mail: AriesHoo@126.com
 * @Function: 统一返回实体
 * @Description:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity {

    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public BaseEntity setCode(int code) {
        this.code = code;
        return this;
    }

    public BaseEntity setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public BaseEntity setData(Object data) {
        this.data = data;
        return this;
    }

    public static BaseEntity success(Object data) {
        return new BaseEntity()
                .setCode(200)
                .setMsg("请求成功")
                .setData(data);
    }
}
