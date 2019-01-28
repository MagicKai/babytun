package com.kaixuan.babytun.service;

import com.kaixuan.babytun.dao.GoodsCoverDao;
import com.kaixuan.babytun.dao.GoodsDao;
import com.kaixuan.babytun.dao.GoodsDetailDao;
import com.kaixuan.babytun.dao.GoodsParamDao;
import com.kaixuan.babytun.entity.Goods;
import com.kaixuan.babytun.entity.GoodsCover;
import com.kaixuan.babytun.entity.GoodsDetail;
import com.kaixuan.babytun.entity.GoodsParam;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {
    @Resource
    private GoodsDao goodsDao;

    @Resource
    private GoodsCoverDao goodsCoverDao;

    @Resource
    private GoodsDetailDao goodsDetailDao;

    @Resource
    private GoodsParamDao goodsParamDao;

    @Cacheable(value = "goods", key = "#goodsId")
    public Goods getGoods(Long goodsId){
        return goodsDao.findById(goodsId);
    }

    @Cacheable(value = "covers", key = "#goodsId")
    public List<GoodsCover> findCovers(Long goodsId){
        return goodsCoverDao.findByGoodsId(goodsId);
    }

    @Cacheable(value = "details", key = "#goodsId")
    public List<GoodsDetail> findDetails(Long goodsId){
        return goodsDetailDao.findByGoodsId(goodsId);
    }

    @Cacheable(value = "params", key = "#goodsId")
    public List<GoodsParam> findParams(Long goodsId){
        return goodsParamDao.findByGoodsId(goodsId);
    }
}
