package com.micro.console.config;

import com.micro.common.util.ZuulUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {

    @Bean
    @ConfigurationProperties(prefix = "zuul")
    public ZuulUtil zuulUtil(){
        return new ZuulUtil();
    }
}
