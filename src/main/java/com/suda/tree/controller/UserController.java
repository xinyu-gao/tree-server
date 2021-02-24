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

    @Autowired
    private UserService userService;

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

    @ApiOperation("获取用户所有角色")
    @GetMapping(value = "/roles")
    public HttpResult getPermissionList(@RequestParam("username") String username) {
        List<String> permissionList = userService.findRolesByUsername(username);
        return HttpResult.success(permissionList);
    }

}
