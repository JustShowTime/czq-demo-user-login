package com.example.demo.controller.other;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice("com.example.demo")
public class ControllerExceptionResolver {
    
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionResolver.class);


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<Map<String, ?>> handleRuntimeException(RuntimeException e) {
        // 运行时异常, 通常是业务抛出的异常
        LOG.error("RuntimeException!!!", e);
        return ResultHelper.errorMsg(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String, ?>> handleException(Exception e) {
        // 预期之外的异常, 通常是代码不够健壮抛出
        LOG.error("系统异常!!!", e);
        return ResultHelper.errorMsg(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
