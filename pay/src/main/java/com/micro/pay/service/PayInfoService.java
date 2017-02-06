package com.micro.pay.service;

import com.micro.common.domain.CommonResponse;
import com.micro.common.enums.OrderStatus;
import com.micro.common.util.NoUtil;
import com.micro.common.util.UUIDUtil;
import com.micro.pay.domain.PayInfo;
import com.micro.pay.domain.PayInfoExample;
import com.micro.pay.mapper.PayInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class PayInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(PayInfoService.class);
    @Resource
    private PayInfoMapper payInfoMapper;

    public List<PayInfo> list() {
        PayInfoExample example = new PayInfoExample();
        example.setOrderByClause("order_no desc");
        return payInfoMapper.selectByExample(example);
    }

    public List<PayInfo> find(PayInfo payInfo) {
        PayInfoExample example = new PayInfoExample();
        example.setOrderByClause("order_no desc");
        PayInfoExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(payInfo.getOrderNo())) {
            criteria.andOrderNoEqualTo(payInfo.getOrderNo());
        }
        return payInfoMapper.selectByExample(example);
    }

    public PayInfo add(PayInfo payInfo) {
        payInfo.setPayOrderNo(NoUtil.generate("P"));
        payInfo.setId(UUIDUtil.generate());
        payInfo.setStatus(OrderStatus.I.name());
        payInfoMapper.insert(payInfo);
        return payInfo;
    }

    /**
     * 支付
     * @param payInfo
     * @param commonResponse
     */
    public void pay(PayInfo payInfo, CommonResponse commonResponse) {
        payInfo = this.add(payInfo);

        try {
            if (payMock()) {
                payInfo.setStatus(OrderStatus.S.name());
                commonResponse.setSuccess(true);
            } else {
                payInfo.setStatus(OrderStatus.F.name());
                commonResponse.setSuccess(false);
                commonResponse.setMessage("支付失败");
            }
        } catch (Exception e) {
            payInfo.setStatus(OrderStatus.P.name());
            commonResponse.setSuccess(false);
            commonResponse.setMessage("支付发生异常");
        }

        payInfoMapper.updateByPrimaryKey(payInfo);
        commonResponse.setContent(payInfo);
    }

    public boolean payMock() {
        int randomInt= new Random().nextInt(10) ;
        LOG.info("LOG00250:支付结果随机值{}", randomInt);
        if (randomInt<3) {
            throw new RuntimeException("异常");
        } if (randomInt<6) {
            return false;
        } else {
            return true;
        }
    }

    public int delete(String id) {
        return payInfoMapper.deleteByPrimaryKey(id);
    }
}
