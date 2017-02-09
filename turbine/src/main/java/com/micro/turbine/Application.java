package com.micro.turbine;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableAutoConfiguration
@EnableTurbine
@EnableHystrixDashboard
@EnableDiscoveryClient
public class Application extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        Environment env = app.run(args).getEnvironment();
        String contextPath = env.getProperty("server.context-path");
        if (StringUtils.isBlank(contextPath)) {
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
        LOG.info("Hystrix URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}/hystrix\n----------------------------------------------------------",
                env.getProperty("server.port"));
        LOG.info("Hystrix URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}/turbine.stream?cluster=MAIN\n----------------------------------------------------------",
                env.getProperty("server.port"));
    }

}