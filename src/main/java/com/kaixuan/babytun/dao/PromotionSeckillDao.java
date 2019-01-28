package com.kaixuan.babytun.dao;

import com.kaixuan.babytun.entity.PromotionSeckill;

import java.util.List;

public interface PromotionSeckillDao {
    public PromotionSeckill findById(Long psId);

    public List<PromotionSeckill> findUnstartSeckill();

    public void update(PromotionSeckill ps);
}
