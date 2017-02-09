package com.micro.pay.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HystrixTestService {

    @HystrixCommand(fallbackMethod = "fallback1")
    public String testPay(){
        int randomInt= new Random().nextInt(10) ;
        System.out.println(randomInt );
        if(randomInt<9){  //模拟调用失败情况
            System.out.println("exception");
            throw new RuntimeException("exception");
        }else{
            return "success++++++"+randomInt;
        }
    }

    public String fallback1(){
        return "fail---------";
    }
}
