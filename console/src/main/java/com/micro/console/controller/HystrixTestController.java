package com.micro.console.controller;

import com.micro.console.service.HystrixTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HystrixTestController {

    @Resource
    private HystrixTestService hystrixTestService;

    @RequestMapping("/hy")
    public String hystrix() {
        return hystrixTestService.test();
    }
}
