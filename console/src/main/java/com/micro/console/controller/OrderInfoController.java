package com.micro.console.controller;

import com.micro.common.domain.CommonResponse;
import com.micro.common.domain.OrderInfo;
import com.micro.common.util.ZuulUtil;
import com.micro.console.config.FeighOrderConfig;
import com.micro.console.config.FeighPayConfig;
import com.micro.console.utils.UriUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order/info")
public class OrderInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderInfoController.class);
    @Resource
    private RestTemplate restTemplate;

//    @Value("${order.url}")
//    private String orderUrl;
//
//    @Resource
//    private UriUtil uriUtil;

//    @Resource
//    private ZuulUtil zuulUtil;

    @Resource
    private FeighOrderConfig.OrderClient orderClient;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public CommonResponse list() {
        LOG.info("LOG00060:订单列表开始");
        CommonResponse commonResponse = orderClient.listOrderInfo();
        LOG.info("LOG00069:订单列表结束:" + commonResponse);
        return commonResponse;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value="创建订单接口", notes="该接口用于创建业务订单")
    public CommonResponse add(@RequestBody OrderInfo orderInfo) {
        LOG.info("LOG00080:创建订单开始:{}", orderInfo);
        CommonResponse commonResponse = orderClient.addOrderInfo(orderInfo);
        LOG.info("LOG00089:创建订单结束:" + commonResponse);
        return commonResponse;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public CommonResponse delete(@PathVariable(value = "id") String id) {
        LOG.info("LOG00090:删除订单开始:{}", id);
        CommonResponse commonResponse = new CommonResponse();
        orderClient.delete(id);
        LOG.info("LOG00099:删除订单结束:");
        return commonResponse;
    }

    @RequestMapping(value = "addAndPay", method = RequestMethod.POST)
    @ApiOperation(value="下单并支付接口", notes="console调用order服务下单, order服务调用pay服务支付, " +
            "pay返回order支付结果, order返回console订单结果1, 示例：\n\r" +
            "{\n\r" +
            "  \"orderDesc\": \"一大箱苹果\",\n\r" +
            "  \"payChannel\": \"0000000002\"\n\r" +
            "}")
    public CommonResponse addAndPay(@ApiParam(required=true, name="orderInfo", value="订单信息") @RequestBody OrderInfo orderInfo) {
        LOG.info("LOG00180:下单并支付开始:{}", orderInfo);
        CommonResponse commonResponse = orderClient.addAndPay(orderInfo);
        LOG.info("LOG00189:下单并支付结束:" + commonResponse);
        return commonResponse;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public CommonResponse find(@RequestBody OrderInfo orderInfo) {
        LOG.info("LOG00270:查询订单开始");
        CommonResponse commonResponse = orderClient.findOrderInfo(orderInfo);
        LOG.info("LOG00279:查询订单结束:" + commonResponse);
        return commonResponse;
    }
}
