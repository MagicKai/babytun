package com.kaixuan.babytun.dao;

import com.kaixuan.babytun.entity.GoodsDetail;

import java.util.List;

public interface GoodsDetailDao {
    public List<GoodsDetail> findByGoodsId(Long goodsId);
}
