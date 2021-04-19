package com.suda.tree.controller;

import com.suda.tree.service.UserService;
import com.suda.tree.service.WebSocketService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthAlipayRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.EncodeException;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private UserService userService;

    @Value("${oauth.alipay.client-id}")
    private String clientId;

    @Value("${oauth.alipay.client-secret}")
    private String clientSecret;

    @Value("${oauth.alipay.public-key}")
    private String publicKey;

    @Value("${oauth.alipay.callback-url}")
    private String callbackUrl;


    @ApiOperation("阿里云登录")
    @RequestMapping("/render/{wsId}")
    public void renderAuth(HttpServletResponse response, @PathVariable(name = "wsId") String wsId) throws IOException {
        AuthRequest authRequest = getAuthRequest(wsId);
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @ApiOperation("阿里云登录回调路径")
    @RequestMapping("/callback/{wsId}")
    public String login(AuthCallback callback, @PathVariable(name = "wsId") String wsId) throws IOException, EncodeException {
        AuthRequest authRequest = getAuthRequest(wsId);
        AuthResponse authResponse = authRequest.login(callback);
        WebSocketService.sendInfoForOne(wsId, userService.loginByAlipayRegister(authResponse));
        return "登陆成功,请返回原页面";
    }

    private AuthRequest getAuthRequest(String wsId) {
        return new AuthAlipayRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .alipayPublicKey(publicKey)
                .redirectUri(callbackUrl + "/oauth/callback/" + wsId)
                .build());
    }


}