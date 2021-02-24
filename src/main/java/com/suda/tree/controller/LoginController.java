package com.suda.tree.controller;


import com.suda.tree.dto.param.UserLoginForEmailParam;
import com.suda.tree.dto.param.UserLoginParam;
import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(value = "登录登出")
@RestController
@RequestMapping()
public class LoginController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "账号密码登录")
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody UserLoginParam userLoginParam) {
        String token = userService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (token == null) {
            return HttpResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return HttpResult.success(tokenMap);
    }

    @ApiOperation(value = "邮箱登录")
    @PostMapping(value = "/login/email")
    public HttpResult loginForEmail(@RequestBody UserLoginForEmailParam userLoginParam) {
        String token = userService.loginForEmail(userLoginParam.getEmail(), userLoginParam.getCode());
        if (token == null) return HttpResult.validateFailed("验证码错误");
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return HttpResult.success(tokenMap);
    }

    @ApiOperation("用户登出")
    @PutMapping(value = "/logout")
    public HttpResult logout(HttpServletRequest request) throws Exception {
        return HttpResult.success(userService.logout(request));

    }
}
