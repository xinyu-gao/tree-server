package com.suda.tree.dto;


import lombok.Data;

/**
 * 通用返回对象
 * Created by macro on 2019/4/19.
 */
@Data
public class HttpResult<T> {
    /**
     * 状态码
     */
    private long code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据封装
     */
    private T data;

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> HttpResult<T> success(T data) {
        return new HttpResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> HttpResult<T> success(T data, String message) {
        return new HttpResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> HttpResult<T> failed(ResultCode errorCode) {
        return new HttpResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static <T> HttpResult<T> failed(ResultCode errorCode, String message) {
        return new HttpResult<>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> HttpResult<T> failed(String message) {
        return new HttpResult<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> HttpResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> HttpResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> HttpResult<T> validateFailed(String message) {
        return new HttpResult<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> HttpResult<T> unauthorized(String message) {
        return new HttpResult(ResultCode.UNAUTHORIZED.getCode(), message);
    }

    /**
     * 未授权返回结果
     */
    public static <T> HttpResult<T> forbidden(T data) {
        return new HttpResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    private HttpResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    private HttpResult(long code, String message) {
        this.code = code;
        this.message = message;
    }
}
