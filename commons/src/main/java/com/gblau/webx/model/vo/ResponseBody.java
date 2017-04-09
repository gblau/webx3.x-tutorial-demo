package com.gblau.webx.model.vo;

/**
 * 前后端交互定义的统一返回格式。
 * @author gblau
 * @date 2017-04-09
 */
public class ResponseBody<T> {
    private int code; //自定义状态码
    private T data; //需要返回给页面的数据
    private String message; //一些后处理的信息

    public static final String LOGIN_SECCUSS = "LOGIN_SECCUSS";
    public static final String LOGOUT_SUCCESS = "LOGOUT_SUCCESS";
    public static final String NO_SUCH_AUTHORITION = "NO_SUCH_AUTHORITION";
    public static final String USERNAME_PASSWORD_ERROR = "USERNAME_PASSWORD_ERROR";
    public static final String WITHOUT_LOGIN = "WITHOUT_LOGIN";

    public int getCode() {
        return code;
    }

    public ResponseBody setCode(int code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseBody setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseBody setMessage(String message) {
        this.message = message;
        return this;
    }
}
