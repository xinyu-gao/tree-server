package com.suda.tree.exception;

import com.suda.tree.dto.ResultCode;
import com.suda.tree.dto.result.HttpResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotFoundException implements ErrorController {

    @RequestMapping("/error")
    public HttpResult error(){
        return HttpResult.failed(ResultCode.NOT_FOUND,"404 not found");
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}