package com.micro.order.service;

import com.micro.common.domain.CommonResponse;
import com.micro.common.domain.PayInfo;
import com.micro.common.util.ZuulUtil;
import com.micro.order.utils.UriUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class PayInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(PayInfoService.class);

    @Resource
    private RestTemplate restTemplate;

//    @Value("${pay.url}")
//    private String payUrl;
//
//    @Resource
//    private UriUtil uriUtil;

    @Resource
    private ZuulUtil zuulUtil;

    public CommonResponse pay(PayInfo payInfo) {
        LOG.info("LOG00200:支付开始:{}", payInfo);
        CommonResponse commonResponse = new CommonResponse();
        try {
            commonResponse = restTemplate.postForObject(zuulUtil.getPayUrl() + "/pay/info/pay", payInfo, CommonResponse.class);
        } catch (Exception e) {
            commonResponse.setSuccess(false);
            commonResponse.setMessage("调用支付接口异常");
            LOG.info("LOG0020E:支付结束, 调用支付接口异常:{}", commonResponse);
        }
        LOG.info("LOG00209:支付结束:{}", commonResponse);
        return commonResponse;
    }
}
