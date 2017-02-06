package com.micro.order.controller;

import com.micro.common.domain.CommonResponse;
import com.micro.order.domain.OrderInfo;
import com.micro.order.service.OrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order/info")
public class OrderInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderInfoController.class);
    @Resource
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private CommonResponse list() {
        LOG.info("LOG00000:订单列表查询开始：");
        CommonResponse commonResponse = new CommonResponse();
        List<OrderInfo> orderInfoList = orderInfoService.list();
        commonResponse.setContent(orderInfoList);
        LOG.info("LOG00009:订单列表查询结束：{}", commonResponse);
        return commonResponse;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private CommonResponse add(@RequestBody OrderInfo orderInfo) {
        LOG.info("LOG00050:添加订单开始：{}", orderInfo);
        CommonResponse commonResponse = new CommonResponse();
        orderInfo = orderInfoService.add(orderInfo);
        commonResponse.setContent(orderInfo);
        LOG.info("LOG00059:添加订单结束：{}", commonResponse);
        return commonResponse;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    private CommonResponse delete(@PathVariable(value = "id") String id) {
        LOG.info("LOG00050:添加订单开始：{}", id);
        CommonResponse commonResponse = new CommonResponse();
        orderInfoService.delete(id);
        LOG.info("LOG00059:添加订单结束：{}", commonResponse);
        return commonResponse;
    }

    @RequestMapping(value = "/addAndPay", method = RequestMethod.POST)
    private CommonResponse addAndPay(@RequestBody OrderInfo orderInfo) {
        LOG.info("LOG00190:下单并支付开始：{}", orderInfo);
        CommonResponse commonResponse = orderInfoService.addAndPay(orderInfo);
        commonResponse.setContent(orderInfo);
        LOG.info("LOG00199:下单并支付结束：{}", commonResponse);
        return commonResponse;
    }

    @RequestMapping("/find")
    public CommonResponse find(@RequestBody OrderInfo orderInfo) {
        LOG.info("LOG00270:查询订单开始:{}", orderInfo);
        List<OrderInfo> orderInfoList = orderInfoService.find(orderInfo);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setContent(orderInfoList);
        LOG.info("LOG00279:查询订单结束:" + commonResponse);
        return commonResponse;
    }
}
