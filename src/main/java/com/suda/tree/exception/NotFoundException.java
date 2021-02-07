package com.suda.tree.exception;

import com.suda.tree.dto.HttpResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotFoundException implements ErrorController {

    private static final String ERROR_PATH = "/error";


    @RequestMapping("/error")
    public HttpResult error(){
        return HttpResult.failed("404 not found");
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}