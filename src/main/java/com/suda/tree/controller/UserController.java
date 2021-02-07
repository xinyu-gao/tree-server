package com.suda.tree.controller;

import com.suda.tree.dto.HttpResult;
import com.suda.tree.entity.mysql.User;
import com.suda.tree.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${minio.endpoint}")
    private String ENDPOINT;

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    @ApiOperation("测试")
    public HttpResult test(){

        return HttpResult.success(ENDPOINT);
    }

    @PostMapping()
    public HttpResult save(@RequestBody User user){
        userService.saveUser(user);
        return HttpResult.success();
    }

    @PutMapping("/password")
    public HttpResult updatePassword(@RequestBody User user){
        return HttpResult.success(userService.updatePassword(user));
    }
}
