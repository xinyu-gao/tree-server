package com.suda.tree.dto;

import lombok.Data;

@Data
public class WebSocketParam<T> {

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

    public WebSocketParam(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> WebSocketParam<T> success(String message, T data) {
        return new WebSocketParam<T>(200, message, data);
    }
}
