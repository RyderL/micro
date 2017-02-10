package com.micro.common.util;

public class ZuulUtil {

    private String url;

    private String pay;

    private String order;

    public String getPayUrl() {
        return "http://" + this.url + "/" + this.getPay();
    }

    public String getOrderUrl() {
        return "http://" + this.url + "/" + this.getOrder();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
