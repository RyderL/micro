package com.micro.console.config;

import com.micro.common.domain.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Configuration
public class FeighPayConfig {

    @FeignClient("zuul/pay")
    public interface PayClient {
        @RequestMapping(value = "/pay/channel/list", method = GET)
        public CommonResponse payChannelList();
    }
}
