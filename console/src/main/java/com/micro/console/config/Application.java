package com.micro.console.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

@ComponentScan(basePackages = {"com.micro"})
@EnableAutoConfiguration
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
public class Application extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(Application.class)
                .sources(Application.class);

        Environment env = springApplicationBuilder.application().run(args).getEnvironment();
        String contextPath = env.getProperty("server.context-path");
        if (StringUtils.isEmpty(contextPath)) {
            LOG.info("Access URLs:\n----------------------------------------------------------\n\t" +
                            "Local: \t\thttp://127.0.0.1:{}\n\t" +
                            "External: \thttp://{}:{}\n----------------------------------------------------------",
                    env.getProperty("server.port"),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"));
        } else {
            LOG.info("Access URLs:\n----------------------------------------------------------\n\t" +
                            "Local: \t\thttp://127.0.0.1:{}{}\n\t" +
                            "External: \thttp://{}:{}{}\n----------------------------------------------------------",
                    env.getProperty("server.port"),
                    env.getProperty("server.context-path"),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"),
                    env.getProperty("server.context-path"));
        }
    }
}