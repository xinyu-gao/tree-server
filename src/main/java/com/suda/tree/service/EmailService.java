package com.suda.tree.service;

public interface EmailService {
    String sendValidateCode(String emailAddress, String code);
}
