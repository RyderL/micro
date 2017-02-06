package com.micro.pay.controller;

import com.micro.common.domain.CommonResponse;
import com.micro.pay.domain.PayInfo;
import com.micro.pay.service.PayInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/pay/info")
public class PayInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(PayInfoController.class);
    @Resource
    private PayInfoService payInfoService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private CommonResponse list() {
        LOG.info("LOG00110:支付订单列表查询开始：");
        CommonResponse commonResponse = new CommonResponse();
        List<PayInfo> payInfoList = payInfoService.list();
        commonResponse.setContent(payInfoList);
        LOG.info("LOG00119:支付订单列表查询结束：{}", commonResponse);
        return commonResponse;
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    private CommonResponse pay(@RequestBody PayInfo payInfo) {
        LOG.info("LOG00120:支付开始：{}", payInfo);
        CommonResponse commonResponse = new CommonResponse();
        payInfoService.pay(payInfo, commonResponse);
        LOG.info("LOG00129:支付结束：{}", commonResponse);
        return commonResponse;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    private CommonResponse delete(@PathVariable(value = "id") String id) {
        LOG.info("LOG00130:删除支付订单开始：{}", id);
        CommonResponse commonResponse = new CommonResponse();
        payInfoService.delete(id);
        LOG.info("LOG00139:删除支付订单结束：{}", commonResponse);
        return commonResponse;
    }

    @RequestMapping("/find")
    public CommonResponse find(@RequestBody PayInfo payInfo) {
        LOG.info("LOG00270:查询订单开始:{}", payInfo);
        List<PayInfo> orderInfoList = payInfoService.find(payInfo);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setContent(orderInfoList);
        LOG.info("LOG00279:查询订单结束:" + commonResponse);
        return commonResponse;
    }
}
