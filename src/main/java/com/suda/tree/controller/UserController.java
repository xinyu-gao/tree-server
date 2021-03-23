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
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/password")
    public HttpResult updatePassword(@RequestBody User user) {
        return HttpResult.success(userService.updatePassword(user));
    }

    @ApiOperation(value = "修改邮箱")
    @PutMapping("/email")
    public HttpResult updateEmail(@RequestBody User user) {
        return HttpResult.success(userService.updateEmail(user));
    }

    @ApiOperation(value = "修改电话号码")
    @PutMapping("/phone_number")
    public HttpResult updatePhoneNumber(@RequestBody User user) {
        return HttpResult.success(userService.updatePhoneNumber(user));
    }

    @ApiOperation("修改角色")
    @PutMapping("/roles")
    public HttpResult updateRoles(@RequestBody User user) {
        return HttpResult.success(userService.updateUserRoles(user));
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public HttpResult register(@RequestBody User user) {
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

    @ApiOperation("获取用户排序列表")
    @GetMapping("/list_sorted")
    public HttpResult getUserListSorted(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("keys") String[] keys,
            @RequestParam(name = "asc") int asc) {
        return HttpResult.success(userService.getUserListSorted(page, size, keys, asc));
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public HttpResult getUserList(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return HttpResult.success(userService.getUserList(page, size));
    }

    @ApiOperation("删除用户")
    @DeleteMapping("")
    public HttpResult deleteUser(@RequestParam("username") String username) {
        return HttpResult.success(userService.deleteUser(username));
    }

    @ApiOperation("查询用户")
    @GetMapping("")
    public HttpResult getUser(@RequestParam("username") String username) {
        return HttpResult.success(userService.findByUsername(username));
    }

}
