package com.micro.console.controller;

import com.micro.common.domain.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        CommonResponse commonResponse = new CommonResponse();
        LOG.error("GlobalExceptionHandler:", e);
        commonResponse.setSuccess(false);
        commonResponse.setMessage("系统异常，请联系管理员");
        return commonResponse;
    }
}
