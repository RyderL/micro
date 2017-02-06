package com.micro.console.utils;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;

@Component
public class UriUtil {

    @Resource
    private LoadBalancerClient loadBalancerClient;

    public String getUri(String serverName) {
        URI uri = null;
        try {
            ServiceInstance instance = this.loadBalancerClient.choose(serverName);
            uri = instance.getUri();
        } catch (Exception e) {
            //当服务不可用时
            e.printStackTrace();
//            uri = URI.create(fallbackUri);
        }
        return uri.toString();
    }
}
