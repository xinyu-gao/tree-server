package com.suda.tree.service;


public interface ValidateCodeService {

    /**
     * 创建验证码
     */
    String createAndSend(String key);

    /**
     * 验证验证码
     */
    boolean validate(String key, String value) ;
}
