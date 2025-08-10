package com.qinge.backend.exception;

import com.qinge.backend.response.Result;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Data: 2025/8/3 18:54
 * @Author: Lc
 * @Description: 全局异常处理器
 */


@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public Result exceptionHandler(Exception e, HttpServletRequest request) {
        logger.error("请求错误，请求地址{}，错误信息：{}: {}", request.getRequestURL(), e.getClass(), e.getMessage());
        return Result.error(e.getMessage());
    }
}
