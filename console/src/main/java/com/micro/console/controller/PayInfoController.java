package com.micro.console.controller;

import com.micro.common.domain.CommonResponse;
import com.micro.common.domain.PayInfo;
import com.micro.common.util.ZuulUtil;
import com.micro.console.utils.UriUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pay/info")
public class PayInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(PayInfoController.class);
    @Resource
    private RestTemplate restTemplate;

//    @Value("${pay.url}")
//    private String payUrl;

//    @Resource
//    private UriUtil uriUtil;

    @Resource
    private ZuulUtil zuulUtil;

    @RequestMapping("/list")
    public CommonResponse list() {
        LOG.info("LOG00150:查询订单开始");
        CommonResponse commonResponse = restTemplate.getForObject(zuulUtil.getPayUrl() + "/pay/info/list", CommonResponse.class);
        LOG.info("LOG00159:查询订单结束:" + commonResponse);
        return commonResponse;
    }

    @RequestMapping("/add")
    public CommonResponse add(@RequestBody PayInfo payInfo) {
        LOG.info("LOG00160:创建订单开始:{}", payInfo);
        CommonResponse commonResponse = restTemplate.postForObject(zuulUtil.getPayUrl() + "/pay/info/add", payInfo, CommonResponse.class);
        LOG.info("LOG00169:创建订单结束:" + commonResponse);
        return commonResponse;
    }

    @RequestMapping("/delete/{id}")
    public CommonResponse delete(@PathVariable(value = "id") String id) {
        LOG.info("LOG00170:删除订单开始:{}", id);
        CommonResponse commonResponse = new CommonResponse();
        restTemplate.delete(zuulUtil.getPayUrl() + "/pay/info/delete/" + id);
        LOG.info("LOG00179:删除订单结束:");
        return commonResponse;
    }

    @RequestMapping("/find")
    public CommonResponse find(@RequestBody PayInfo payInfo) {
        LOG.info("LOG00270:查询订单开始");
        CommonResponse commonResponse = restTemplate.postForObject(zuulUtil.getPayUrl() + "/pay/info/find", payInfo, CommonResponse.class);
        LOG.info("LOG00279:查询订单结束:" + commonResponse);
        return commonResponse;
    }
}
