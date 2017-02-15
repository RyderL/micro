package com.micro.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "订单信息类", description = "订单信息")
public class OrderInfo {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private String id;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 订单描述
     */
    @ApiModelProperty(value = "订单描述")
    private String orderDesc;

    /**
     * 支付渠道编号
     */
    @ApiModelProperty(value = "支付渠道编号")
    private String payChannel;

    /**
     * 订单状态：I：初始，P：处理中，S：成功，F：失败
     */
    @ApiModelProperty(value = "订单状态：I：初始，P：处理中，S：成功，F：失败")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", orderDesc=").append(orderDesc);
        sb.append(", payChannel=").append(payChannel);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderInfo other = (OrderInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getOrderDesc() == null ? other.getOrderDesc() == null : this.getOrderDesc().equals(other.getOrderDesc()))
            && (this.getPayChannel() == null ? other.getPayChannel() == null : this.getPayChannel().equals(other.getPayChannel()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getOrderDesc() == null) ? 0 : getOrderDesc().hashCode());
        result = prime * result + ((getPayChannel() == null) ? 0 : getPayChannel().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}