package com.gblau.webx.model.po;

/**
 * @author gblau
 * @date 2017-05-26
 */
public enum Status {

    OK(2000, "结果请求成功"),

    ACCEPTED(2001, "验证成功"),

    REJECTED(2002, "验证失败"),

    INVALID_ACCOUNT(2003, "非法账号，请检查账号的存在性"),

    BAD_REQUEST(4000, "请求错误"),

    FORBIDDEN(4003, "权限不足无法操作"),

    MISSING_PARAMETER(4004, "参数缺失"),

    INTERNAL_SERVER_ERROR(5000, ""),

    ;

    private final int value;
    private final String reasonPhrase;


    Status(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
}
