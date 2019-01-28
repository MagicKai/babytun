package com.kaixuan.babytun.controller;

import com.kaixuan.babytun.service.PromotionSeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PromotionSeckillController {
    @Autowired
    private PromotionSeckillService promotionSeckillService;

    @RequestMapping("/secKill")
    @ResponseBody
    public Map processSecKill(Long psId,String userId){
        Map result = new HashMap(3);
        try {
            promotionSeckillService.processSecKill(psId,userId,1);
            String orderNo = promotionSeckillService.sendOrderToQueue(userId);
            Map data = new HashMap();
            data.put("orderNo",orderNo);
            result.put("code","0");
            result.put("msg","success");
            result.put("data",data);
        } catch (Exception e) {
            result.put("code","500");
            result.put("msg",e.getMessage());
        }
        return result;
    }
}
