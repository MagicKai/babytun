package com.kaixuan.babytun.dao;

import com.kaixuan.babytun.entity.Goods;
import org.apache.ibatis.annotations.Param;

public interface GoodsDao {
    public Goods findById(@Param("goodsId") Long goodsId);
}
