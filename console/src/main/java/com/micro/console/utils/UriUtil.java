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

//    /**
//     * 根据实例名获取其中一个具体实例
//     * @param serverName 客户端实例名
//     * @return
//     */
//    public String getUri(String serverName) {
//        URI uri = null;
//        try {
//            ServiceInstance instance = this.loadBalancerClient.choose(serverName);
//            uri = instance.getUri();
//        } catch (Exception e) {
//            //当服务不可用时
//            e.printStackTrace();
////            uri = URI.create(fallbackUri);
//        }
//        return uri.toString();
//    }

    /**
     * 按实例名访问
     * @param serverName 客户端实例名
     * @return
     */
    public String getUri(String serverName) {
        return "http://" + serverName;
    }
}
