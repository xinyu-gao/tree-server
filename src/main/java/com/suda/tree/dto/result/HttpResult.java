package com.suda.tree.dto.result;


import com.suda.tree.dto.ResultCode;
import lombok.Data;

/**
 * 通用返回对象
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

    public static HttpResult<Boolean> success() {
        return new HttpResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), true);
    }

    public static <T> HttpResult<T> success(T data) {
        return new HttpResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> HttpResult<T> success(T data, String message) {
        return new HttpResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> HttpResult<T> failed(ResultCode errorCode) {
        return new HttpResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> HttpResult<T> failed(ResultCode errorCode, String message) {
        return new HttpResult<>(errorCode.getCode(), message, null);
    }

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
