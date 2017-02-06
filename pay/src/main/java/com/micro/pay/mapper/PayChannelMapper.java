package com.micro.pay.mapper;

import com.micro.pay.domain.PayChannel;
import com.micro.pay.domain.PayChannelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayChannelMapper {
    int countByExample(PayChannelExample example);

    int deleteByExample(PayChannelExample example);

    int deleteByPrimaryKey(String id);

    int insert(PayChannel record);

    int insertSelective(PayChannel record);

    List<PayChannel> selectByExample(PayChannelExample example);

    PayChannel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PayChannel record, @Param("example") PayChannelExample example);

    int updateByExample(@Param("record") PayChannel record, @Param("example") PayChannelExample example);

    int updateByPrimaryKeySelective(PayChannel record);

    int updateByPrimaryKey(PayChannel record);
}