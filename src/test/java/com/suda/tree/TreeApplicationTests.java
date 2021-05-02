package com.suda.tree;

import com.suda.tree.service.EmailService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TreeApplicationTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void testEmail() {
        Assert.assertEquals("发送成功",emailService.sendWarnMessage("isgaoxy@gmail.com", "13", "温度超出异常，当前温度 57°C"));
    }
}
