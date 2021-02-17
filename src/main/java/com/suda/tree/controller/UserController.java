package com.suda.tree.controller;

import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.dto.param.UserLoginForEmailParam;
import com.suda.tree.dto.param.UserLoginParam;
import com.suda.tree.entity.mysql.User;
import com.suda.tree.service.UserService;
import com.suda.tree.service.ValidateCodeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService userService;

    @Autowired
    private ValidateCodeService validateCodeService;


    @GetMapping("/test")
    @ApiOperation("测试")
    @PreAuthorize("hasAuthority('admin')")
    public HttpResult test() throws Exception {
        throw new Exception("dasd");
//        return HttpResult.success(tokenHead);
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/password")
    public HttpResult updatePassword(@RequestBody User user) {
        return HttpResult.success(userService.updatePassword(user));
    }


    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public HttpResult register(@RequestBody UserLoginParam userLoginParam) {
        User user = new User();
        user.setUsername(userLoginParam.getUsername());
        user.setPassword(userLoginParam.getPassword());
        if (userService.saveUser(user)) {
            return HttpResult.success("注册成功");
        } else {
            return HttpResult.failed("用户名已存在");
        }
    }

    @ApiOperation(value = "登录以后返回token")
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

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/email/login")
    public HttpResult loginForEmail(@RequestBody UserLoginForEmailParam userLoginParam) {
        String token = userService.loginForEmail(userLoginParam.getEmail(), userLoginParam.getCode());
        if (token == null) return HttpResult.validateFailed("验证码错误");
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return HttpResult.success(tokenMap);
    }

    @GetMapping(value = "/email/code")
    public HttpResult getEmailValidateCode(@RequestParam("email") String Email) {
        String message = validateCodeService.createAndSend(Email);
        return "发送成功".equals(message) ? HttpResult.success(message) : HttpResult.failed(message);
    }

    @ApiOperation("获取用户所有角色")
    @GetMapping(value = "/roles")
    public HttpResult getPermissionList(@RequestParam("username") String username) {
        List<String> permissionList = userService.findRolesByUsername(username);
        return HttpResult.success(permissionList);
    }

//    @ApiOperation("获取用户基本信息")
//    @GetMapping(value = "/info")
//    public HttpResult getInfoByUsername(@RequestParam("username") String username) {
//        List<String> permissionList = userService.findRolesByUsername(username);
//        List<>
//        return HttpResult.success(permissionList);
//    }
//
//    @ApiOperation("获取用户基本信息")
//    @GetMapping(value = "/info/mail")
//    public HttpResult getInfoByEmail(@RequestParam("username") String username) {
//        List<String> permissionList = userService.findRolesByUsername(username);
//        return HttpResult.success(permissionList);
//    }

    @ApiOperation("用户登出")
    @PutMapping(value = "/logout")
    public HttpResult logout(HttpServletRequest request) throws Exception {
        return HttpResult.success(userService.logout(request));

    }
}
