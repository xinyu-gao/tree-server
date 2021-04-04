package com.suda.tree.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.suda.tree.dao.UserRepository;
import com.suda.tree.dto.WebSocketParam;
import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.entity.mysql.User;
import com.suda.tree.service.UserService;
import com.suda.tree.service.WebSocketService;
import com.suda.tree.util.TransferUtil;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthAlipayRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeChatMpRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/oauth")
public class OAuthController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/render/{wsId}")
    public void renderAuth(HttpServletResponse response, @PathVariable(name = "wsId") String wsId) throws IOException {
        AuthRequest authRequest = getAuthRequest(wsId);
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback/{wsId}")
    public String login(AuthCallback callback, @PathVariable(name = "wsId") String wsId) throws IOException, EncodeException {
        AuthRequest authRequest = getAuthRequest(wsId);
        AuthResponse authResponse = authRequest.login(callback);
        log.info(authResponse.getData().toString());
        String token = userService.loginByAlipayRegister(authResponse);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        String userId = TransferUtil.getUserIdFromAuth(authResponse);
        tokenMap.put("userId", userId);
        tokenMap.put("username", userRepository.findUserByUserId(userId).getUsername());

        String message = JSONUtil.toJsonStr((WebSocketParam.success("登录", tokenMap)));
        WebSocketService.sendInfoForOne(wsId, message);
        return "登陆成功,请返回原页面";
    }

    private AuthRequest getAuthRequest(String wsId) {
        return new AuthAlipayRequest(AuthConfig.builder()
                .clientId("2021002135688012")
                .clientSecret("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCP55D69pTzSZFhJnf89qdgvEsDqw7arZg9llCchqZ1czM4bicEnmPMFmJmp7WIlFsA9NzpAKE7Vdy1dRUo2FmMJ24ThNk9qSNbAKzwtDemhVVk/dImeVW7mHqs9ca91ReNL7fuJ4zu6FLTT7fblpZAZKyyubJMXXY1x72xFxE2XuOjEktgiP6qodUQxJHuPyh3yxG/l3x4ZmcTZI5tR1PExA0qVQK+e6c4j6e/8cJHVjx0u9N+dTqGJZOc12E/yUWTvmtJRbdxW7BUnBecbhRv8FkqBd6LD5DxeGJvDJ7sIWywYVddCXxNnogP2EFQCPjq4/AExn9YHavKKv8I7G+XAgMBAAECggEAb4lgSeRNVrQyxkX2wk67NLM8wT8YaE4crb+QA4IqCuabUKWwAFhzqX/ADSj3/0BduMQRZVdjoIXQZ6S0HdByt7roCqDxcGVkTeicIoTMjXjS01AYTlHFLX3lt6FXxVMgygGuYhytk47mg3pKWxzAoHBnmq9rjtzx/oUuddAXOzfpEY7FTn9Fb55AZzG3Cgi9L8rMx33fg9L8r/h3lvUn5cn9y8Wlj9RukeQIjIcZJraylv83do5p1K8n7wTcYBnheJnnY66jO/XLr8QMB1/VgvNa8C3tyuB5kVktN/9VQxruOPJAJVq9tRZy1/Wlp0HPlghDowAVuuwUrT3aEa1RAQKBgQDrmrFgNDOvSAtttJko0PKbtGLBDGc94AU4A3A0BeN2s3/UiG9ZKYvJhwlDTnsIhKFKb8GYgyrS+rcr5J0e66LkamJLqA+nQUBYMLdeZCjN475duknHKqQYoeYUB8/c1zELOoiWGlAUUbVoqR7edPxU3UnRiv8+BeENSgo9nrL/twKBgQCcXK9DM5FDdN+UwD+owgUXAvLtFRubTFjgL2WWvivOlBG0ZnNqDZlfGlvx+ioINeWAg+4fX/nJUhgKZHFGt/IzL7aOo0HXcnxFWBYsu/NIwEgq9CfAPr4j2vUnRMM2d8xPN42ECh+JaJuH36LCcgX5WND2pSRTbdh4jfG/gJBPIQKBgH/HrlvW9BEkqc4eHNMeP/Zipwy8hWHxleMDOl98shk20SWbuYhKGB58tnFipye/hxqv8fedbCRxBdcwbmbOtdHcQON3isXThepOwaTX/als01pSJqF2ZvlVFUvX0MydiWPlox6FttjOV+0jjDJqkFAWlGqpUDV7SsvCVDuHTt2RAoGBAJE4V6JfS2POMTTTFIyknduQZpQgjYI/dy/HdV96ge8bgBH/MOPne3FjZs+nGtCDVgJ1BXxoQ/JhZ2iHftlCUBSQFMoHAEib0KUKuZWUfdAsnRofpqea3jcozZVJ/NAilUNhgfKHAGE7IXAhGOXkxn2dqn3YRJOjqm/UzRihy+VhAoGAATyJmyzcOYqYL8rcX3rnlMrbW6PCpJDuMq9yu75eClm8GCZ1gezfu5J8CGnJ4uMa+mYHZVOpYIDj2KaMK58P9lkLBonyCZm5cVuM3SDd+kY091uFhIM2+fkJ+ggYQKZAYdTyZZj1kcQu6+CBi1B2b5eTLeZ0xZXm2/XDMpW7SpE=")
                .alipayPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhIl9tqsPWNpzh22pyzu/2HeWWyPT6gafXWlRUiUTL4LCAADG1fdHF9hywS/Lc3W+D85qkAPYjrRzObe6Emi08OlCKH1OTnMNE1MxmwGqc04FDeWqpX6m3gUGa7j2fyINNCS2+BlRUvpwZsPWvVrpQWi7RfKVTVHrcylixpubLfztTVcv2Wh/j3jss1p46cS9uDHs5CcFrhajwRn+1XHEWa5/HbL0Z+rl9TANGNHttCuXwH0ZdDclnetRrcqsH0rBuoyyVbgpSjkN19cdzTuX1B7Eri3wmiG+JuZZzkKH5zAa6MFz9C5PNLs56A6sRGUiT/LzQrx9UBXWRP1ZCD7GTwIDAQAB")
                .redirectUri("http://3d41214b2870.ngrok.io/oauth/callback/" + wsId)
                .build());
    }


}