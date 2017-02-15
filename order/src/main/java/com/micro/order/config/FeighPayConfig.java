package com.micro.order.config;

import com.micro.common.domain.CommonResponse;
import com.micro.common.domain.PayInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Configuration
public class FeighPayConfig {

    @FeignClient("zuul/pay")
    public interface PayClient {
        @RequestMapping(value = "/pay/info/pay", method = POST)
        public CommonResponse payInfoPay(@RequestBody PayInfo payInfo);
    }
}
