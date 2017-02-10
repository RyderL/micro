package com.micro.order.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.micro.common.domain.CommonResponse;
import com.micro.common.domain.PayInfo;
import com.micro.common.enums.OrderStatus;
import com.micro.common.util.NoUtil;
import com.micro.common.util.UUIDUtil;
import com.micro.order.domain.OrderInfo;
import com.micro.order.domain.OrderInfoExample;
import com.micro.order.mapper.OrderInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderInfoService.class);
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private PayInfoService payInfoService;

    private Gson gson = new Gson();

    public List<OrderInfo> list() {
        OrderInfoExample example = new OrderInfoExample();
        example.setOrderByClause("order_no desc");
        return orderInfoMapper.selectByExample(example);
    }

    public List<OrderInfo> find(OrderInfo orderInfo) {
        OrderInfoExample example = new OrderInfoExample();
        example.setOrderByClause("order_no desc");
        OrderInfoExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(orderInfo.getOrderNo())) {
            criteria.andOrderNoEqualTo(orderInfo.getOrderNo());
        }
        return orderInfoMapper.selectByExample(example);
    }

    /**
     * 下单
     * @param orderInfo
     * @return
     */
    public OrderInfo add(OrderInfo orderInfo) {
        LOG.info("LOG00240:下单开始：{}", orderInfo);
        orderInfo.setOrderNo(NoUtil.generate("O"));
        orderInfo.setId(UUIDUtil.generate());
        orderInfo.setStatus(OrderStatus.I.name());
        orderInfoMapper.insert(orderInfo);
        LOG.info("LOG00249:下单结束：{}", orderInfo);
        return orderInfo;
    }

    /**
     * 下单并支付
     * @param orderInfo
     * @return
     */
    public CommonResponse addAndPay(OrderInfo orderInfo) {
        orderInfo = this.add(orderInfo);

        PayInfo payInfo = new PayInfo();
        payInfo.setOrderNo(orderInfo.getOrderNo());
        payInfo.setPayChannel(orderInfo.getPayChannel());
        CommonResponse commonResponse = payInfoService.pay(payInfo);
        payInfo = gson.fromJson(gson.toJson(commonResponse.getContent()), new TypeToken<PayInfo>() {}.getType());
        if (commonResponse.isSuccess()) {
            orderInfo.setStatus(OrderStatus.S.name());
        } else {
            if (payInfo != null && OrderStatus.F.name().equals(payInfo.getStatus())) {
                orderInfo.setStatus(OrderStatus.F.name());
            } else {
                orderInfo.setStatus(OrderStatus.P.name());
            }
        }
        commonResponse.setContent(orderInfo);
        orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
        return commonResponse;
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    public int delete(String id) {
        return orderInfoMapper.deleteByPrimaryKey(id);
    }
}
