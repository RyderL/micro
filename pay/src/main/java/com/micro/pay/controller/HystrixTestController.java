package com.micro.pay.controller;

import com.micro.pay.service.HystrixTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HystrixTestController {

    @Resource
    private HystrixTestService hystrixTestService;

    @RequestMapping("/hy")
    public String hystrix() {
        return hystrixTestService.testPay();
    }
}
