package com.suda.tree.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suda.tree.dao.UserMapper;
import com.suda.tree.entity.mysql.User;
import com.suda.tree.dto.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping()
    public HttpResult save(@RequestBody User user){
        return HttpResult.success(userMapper.insert(user));
//        return HttpResult.success(userMapper.selectList(new QueryWrapper<User>().lambda().ge(User::getAge, 20)));
    }

    @PutMapping("/password")
    public HttpResult updatePassword(@RequestBody User user){
//        userMapper.updatePassword(user);
//        return HttpResult.success("");
        return HttpResult.success(userMapper.selectList(new QueryWrapper<User>().lambda().ge(User::getAge, 20)));
    }
}
