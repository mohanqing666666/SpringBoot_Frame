package com.debug.steadyjack.reponse;

import com.debug.steadyjack.enums.StatusCode;

/***
 * 功能描述:通用响应模型
 * @author MS
 * @date 2020-07-22
 */
public class BaseResponse<T> {
    private Integer code;

    private String msg;

    private T data;

    public BaseResponse(StatusCode statusCode) {
        this.code=statusCode.getCode();
        this.msg=statusCode.getMsg();
    }

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(T data, StatusCode statusCode) {
        this.data = data;
        this.code=statusCode.getCode();
        this.msg=statusCode.getMsg();
    }

    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
