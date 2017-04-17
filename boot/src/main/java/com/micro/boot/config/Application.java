package com.micro.boot.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.micro"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}