package com.kaixuan.babytun.dao;

import com.kaixuan.babytun.entity.GoodsParam;

import java.util.List;

public interface GoodsParamDao {
    public List<GoodsParam> findByGoodsId(Long goodsId);
}
