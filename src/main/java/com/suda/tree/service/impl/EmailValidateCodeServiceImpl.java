package com.suda.tree.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.suda.tree.service.EmailService;
import com.suda.tree.service.RedisService;
import com.suda.tree.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private RedisService redisService;

    @Value("${spring.mail.validate-code.expiration}")
    private int expiration;

    @Override
    public String createAndSend(String key) {
        String validateCode = generate();
        save(key, validateCode);
        return send(key, validateCode);
    }

    @Override
    public boolean validate(String key, String value) {
        if (value == null || key == null) return false;
        return value.equals(redisService.get(key));
    }


    private String send(String emailAddress, String validateCode) {
        return emailService.sendValidateCode(emailAddress, validateCode);
    }


    /**
     * 保存验证码，保存到 redis 中
     */
    private void save(String emailAddress, String validateCode) {
        redisService.set(emailAddress, validateCode, expiration);
    }


    /**
     * 生成四位数字验证码
     */
    private String generate() {
        return String.valueOf(RandomUtil.randomInt(1000, 9999));
    }

}
