package com.kaixuan.babytun.dao;

import com.kaixuan.babytun.entity.Order;

public interface OrderDao {
    public Long insert(Order order);
    public Order findByOrderNo(String orderNo);
}
