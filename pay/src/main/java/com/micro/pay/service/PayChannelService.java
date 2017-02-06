package com.micro.pay.service;

import com.micro.pay.domain.PayChannel;
import com.micro.pay.mapper.PayChannelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PayChannelService {

    @Resource
    private PayChannelMapper payChannelMapper;

    public List<PayChannel> list() {
        return payChannelMapper.selectByExample(null);
    }

}
