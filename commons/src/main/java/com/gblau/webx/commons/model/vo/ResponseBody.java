package com.gblau.webx.commons.model.vo;

import com.gblau.webx.commons.util.Status;
import com.gblau.webx.commons.util.Assert;

/**
 * 前后端交互定义的统一返回格式。
 * @author gblau
 * @date 2017-04-09
 */
public class ResponseBody<T> {
    private Status status;
    private T data;
    private String message;

    private ResponseBody(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public ResponseBody setStatus(Status status) {
        this.status = status;
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

    /**
     * 200：操作成功。
     * @return
     */
    public static ResponseBody.ModelBuilder ok() {
        return status(Status.OK);
    }

    /**
     * 操作成功，并且返回数据。
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseBody<T> ok(T data) {
        return ok().body(data);
    }

    /**
     * 202：登录成功。
     * @return
     */
    public static ResponseBody.ModelBuilder accepted() {
        return status(Status.ACCEPTED);
    }

    /**
     * 406：拒绝操作。例如登录失败、没有权限访问
     * @return
     */
    public static ResponseBody.ModelBuilder rejected() {
        return status(Status.REJECTED);
    }

    /**
     * 400：错误的请求
     * @return
     */
    public static ResponseBody.ModelBuilder badRequest() {
        return status(Status.BAD_REQUEST);
    }

    /**
     * 403：没有权限进行相关操作。
     * @return
     */
    public static ResponseBody.ModelBuilder forbidden() {
        return status(Status.FORBIDDEN);
    }

    /**
     * 500：服务器内部错误
     * @return
     */
    public static ResponseBody.ModelBuilder internerServerError() {
        return status(Status.INTERNAL_SERVER_ERROR);
    }

    /**
     * 通过设置状态码获取一个ModelBuilder。可以设置自定义状态码
     * @param status
     * @return
     */
    public static ResponseBody.ModelBuilder status(Status status) {
        Assert.notNull(status, "Status must not be null");
        return new ResponseBody.DefaultBuilder(status);
    }

    /**
     * ResponseBody的构造器接口。目的是构造一个ResponseBody
     */
    public interface ModelBuilder {
        /**
         * 构建一个含有message的ResponseBody
         * @param message
         * @return
         */
        ResponseBody message(String message);

        /**
         * 构建一个ResponseBody
         * @return
         */
        ResponseBody build();

        /**
         * 构建一个带返回对象的ResponseBody
         * @param data
         * @param <T>
         * @return
         */
        <T> ResponseBody<T> body(T data);

        /**
         * 构建一个带返回对象并且含有message的ResponseBody
         * @param data
         * @param message
         * @param <T>
         * @return
         */
        <T> ResponseBody<T> messageWithBody(T data, String message);
    }

    private static class DefaultBuilder implements ResponseBody.ModelBuilder {
        private Status status;

        private DefaultBuilder(Status status) {
            this.status = status;
        }

        @Override
        public ResponseBody message(String message) {
            return new ResponseBody(status, null, message);
        }

        @Override
        public ResponseBody build() {
            return body(null);
        }

        @Override
        public <T> ResponseBody<T> body(T data) {
            return new ResponseBody<>(status, data, null);
        }

        @Override
        public <T> ResponseBody<T> messageWithBody(T data, String message) {
            return new ResponseBody<>(status, data, message);
        }
    }
}
