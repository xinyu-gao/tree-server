package com.suda.tree;

import com.suda.tree.dao.UserMapper;
import com.suda.tree.entity.mysql.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TreeApplicationTests {

    @Test
    void contextLoads() {

        userMapper.insert(new User());
    }
    @Autowired
    private UserMapper userMapper;

}
