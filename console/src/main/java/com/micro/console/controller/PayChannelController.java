package com.micro.console.controller;

import com.micro.common.domain.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pay/channel")
public class PayChannelController {

    private static final Logger LOG = LoggerFactory.getLogger(PayChannelController.class);
    @Resource
    private RestTemplate restTemplate;

    @Value("${pay.url}")
    private String payUrl;

    @RequestMapping("/list")
    public CommonResponse list() {
        LOG.info("LOG00140:查询支付渠道开始");
        CommonResponse commonResponse = restTemplate.getForObject(payUrl + "/pay/channel/list", CommonResponse.class);
        LOG.info("LOG00149:查询支付渠道结束:" + commonResponse);
        return commonResponse;
    }

}
