package com.kaixuan.babytun.entity;

import java.io.Serializable;

public class GoodsDetail implements Serializable {
    private Long gdId;
    private Long goodsId;
    private String gdPicUrl;
    private Long  gdOrder;

    public Long getGdId() {
        return gdId;
    }

    public void setGdId(Long gdId) {
        this.gdId = gdId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGdPicUrl() {
        return gdPicUrl;
    }

    public void setGdPicUrl(String gdPicUrl) {
        this.gdPicUrl = gdPicUrl;
    }

    public Long getGdOrder() {
        return gdOrder;
    }

    public void setGdOrder(Long gdOrder) {
        this.gdOrder = gdOrder;
    }
}
