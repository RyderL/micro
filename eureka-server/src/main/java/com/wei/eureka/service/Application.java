package com.wei.eureka.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableAutoConfiguration
@EnableEurekaServer
public class Application extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        Environment env = app.run(args).getEnvironment();
        String contextPath = env.getProperty("server.context-path");
        if (StringUtils.isBlank(contextPath)) {
            LOG.info("Access URLs:\n----------------------------------------------------------\n\t" +
                            "Local: \t\thttp://localhost:{}\n\t" +
                            "External: \thttp://{}:{}\n----------------------------------------------------------",
                    env.getProperty("server.port"),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"));
        } else {
            LOG.info("Access URLs:\n----------------------------------------------------------\n\t" +
                            "Local: \t\thttp://localhost:{}{}\n\t" +
                            "External: \thttp://{}:{}{}\n----------------------------------------------------------",
                    env.getProperty("server.port"),
                    env.getProperty("server.context-path"),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"),
                    env.getProperty("server.context-path"));
        }
    }

}