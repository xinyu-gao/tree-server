package com.suda.tree.controller;

import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.entity.SmsCode;
import com.suda.tree.service.ValidateCodeService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/validate")
public class ValidateController {

    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping(value = "/email/code")
    public HttpResult getEmailValidateCode(@RequestParam("email") String Email) {
        String message = validateCodeService.createAndSend(Email);
        return "发送成功".equals(message) ? HttpResult.success(message) : HttpResult.failed(message);
    }
}