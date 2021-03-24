package com.suda.tree.entity;

import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
public class WebSocketLogInfo {

    private String sessionId;

    private Authentication user;

    private String message;

    public WebSocketLogInfo(String sessionId, Authentication user, String message) {
        this.sessionId = sessionId;
        this.user = user;
        this.message = message;
    }

    public static String buildAndToString(String sessionId, Authentication user, String message){
        return new WebSocketLogInfo(sessionId, user, message).toString();
    }
}
