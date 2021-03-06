package com.micro.console.controller;

import com.micro.common.domain.CommonResponse;
import com.micro.common.util.ZuulUtil;
import com.micro.console.config.FeighPayConfig.PayClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//    @Value("${pay.url}")
//    private String payUrl;
//
//    @Value("${zuul.url}")
//    private String zuulUrl;

//    @Resource
//    private UriUtil uriUtil;

    @Resource
    private ZuulUtil zuulUtil;

    @Resource
    private PayClient payClient;

//    @RequestMapping("/list")
//    @HystrixCommand(fallbackMethod = "fallback")
//    public CommonResponse list() {
//        LOG.info("LOG00140:查询支付渠道开始");
////        CommonResponse commonResponse = restTemplate.getForObject(uriUtil.getUri(payUrl) + "/pay/channel/list", CommonResponse.class);
//        CommonResponse commonResponse = restTemplate.getForObject(zuulUtil.getPayUrl() + "/pay/channel/list", CommonResponse.class);
//        LOG.info("LOG00149:查询支付渠道结束:" + commonResponse);
//        return commonResponse;
//    }

    @RequestMapping("/list")
    @HystrixCommand(fallbackMethod = "fallback")
    public CommonResponse list() {
        LOG.info("LOG00140:查询支付渠道开始");
        CommonResponse commonResponse = payClient.payChannelList();
        LOG.info("LOG00149:查询支付渠道结束:" + commonResponse);
        return commonResponse;
    }

    public CommonResponse fallback(){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setSuccess(false);
        commonResponse.setMessage("查询渠道列表失败");
        return commonResponse;
    }

}
