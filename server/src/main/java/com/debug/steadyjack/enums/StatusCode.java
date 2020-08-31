package com.debug.steadyjack.enums;
/***
 * 功能描述:自定义的状态码列表
 * @author MS
 * @date 2020-07-22
 */
public enum StatusCode {
    Success(0,"成功"),
    Fail(-1,"失败"),
    NotFound(10010,"不存在"),
    Entity_Not_Exist(10011,"实体信息不存在"),
    Invalid_Params(10012,"请求参数不合法!"),
    Validate_UserName_Expire(10013,"未在有效时间内验证注册信息，请重新注册!!");

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
