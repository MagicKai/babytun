package com.kaixuan.babytun.controller;

import com.kaixuan.babytun.entity.Goods;
import com.kaixuan.babytun.entity.GoodsCover;
import com.kaixuan.babytun.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GoodsController {
    public static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods")
    public ModelAndView showGoods(Long gid){
        logger.info("当前商品ID：" + gid);
        ModelAndView modelAndView = new ModelAndView("/goods");
        modelAndView.addObject("goods",goodsService.getGoods(gid));
        modelAndView.addObject("covers",goodsService.findCovers(gid));
        modelAndView.addObject("details",goodsService.findDetails(gid));
        modelAndView.addObject("params",goodsService.findParams(gid));
        return modelAndView;
    }

}
