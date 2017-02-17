package com.micro.pay.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${hello.world:World!}")
    private String hello;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    private String hello() {
        return hello;
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/instance/{name}", method = RequestMethod.GET)
    private List<ServiceInstance> instance(@PathVariable(value = "name") String pay) {
        List<ServiceInstance> instances = discoveryClient.getInstances(pay);
        return instances;
    }


}
