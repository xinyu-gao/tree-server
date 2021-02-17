package com.suda.tree.exception;

import com.suda.tree.dto.result.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xinyu
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

    /**
     * 统一异常处理，两个注解： @ControllerAdvice、@ExceptionHandler
     * Spring 会自动调用该方法，不用我们后续做处理
     * 当系统抛出异常时，返回自定义 JSON 数据，而不返回系统自带的数据
     * @param request 请求
     * @param e 异常信息
     * @return 自定义返回的 JSON 数据
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HttpResult defaultErrorHandler(HttpServletRequest request, Throwable e) {
        String message = e.getMessage();
        log.error("[" + request.getRequestURI() + "]：" + message);
        return HttpResult.failed(message);
    }
}