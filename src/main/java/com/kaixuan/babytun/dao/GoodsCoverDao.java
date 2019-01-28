package com.kaixuan.babytun.dao;

import com.kaixuan.babytun.entity.GoodsCover;

import java.util.List;

public interface GoodsCoverDao {
    public List<GoodsCover> findByGoodsId(Long goodsId);
}
