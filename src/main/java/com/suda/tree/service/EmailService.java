package com.suda.tree.service;

public interface EmailService {

    String sendValidateCode(String emailAddress, String code);

    String sendWarnMessage(String emailAddress, String treeId, String data);

}
