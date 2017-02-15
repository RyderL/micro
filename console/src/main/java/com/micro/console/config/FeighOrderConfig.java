package com.micro.console.config;

import com.micro.common.domain.CommonResponse;
import com.micro.common.domain.OrderInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Configuration
public class FeighOrderConfig {

    @FeignClient("zuul/order")
    public interface OrderClient {
        @RequestMapping(value = "/order/info/list", method = RequestMethod.GET)
        public CommonResponse listOrderInfo();

        @RequestMapping(value = "/order/info/add", method = RequestMethod.POST)
        public CommonResponse addOrderInfo(@RequestBody OrderInfo orderInfo);

        @RequestMapping(value = "/order/info/addAndPay", method = RequestMethod.POST)
        public CommonResponse addAndPay(@RequestBody OrderInfo orderInfo);

        @RequestMapping(value = "/order/info/find", method = RequestMethod.POST)
        public CommonResponse findOrderInfo(@RequestBody OrderInfo orderInfo);

        @RequestMapping(value = "/order/info/delete/{id}", method = RequestMethod.DELETE)
        public CommonResponse delete(@PathVariable(value = "id") String id);
    }
}
