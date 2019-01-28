package com.kaixuan.babytun.service;

import com.kaixuan.babytun.dao.PromotionSeckillDao;
import com.kaixuan.babytun.entity.PromotionSeckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PromotionSeckillService {

    public static Logger logger = LoggerFactory.getLogger(PromotionSeckillService.class);
    @Resource
    private PromotionSeckillDao promotionSeckillDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void processSecKill(Long psId,String userId,Integer num){
        PromotionSeckill ps = promotionSeckillDao.findById(psId);
        if(null == ps){
            throw new SecurityException(ps.getPsId() + "秒杀活动不存在");
        }
        if(ps.getStatus() == 0){
            throw new SecurityException(ps.getPsId() + "秒杀活动未开始");
        }
        if(ps.getStatus() == 2){
            throw new SecurityException(ps.getPsId() + "秒杀活动已经结束");
        }
        Integer goodsId = (Integer) redisTemplate.opsForList().leftPop("seckill:count:" + ps.getPsId());
        if(goodsId != null){
            Boolean isExisted = redisTemplate.opsForSet().isMember("seckill:users:" + ps.getPsId(), userId);
            if(!isExisted){
                logger.info("恭喜您抢到商品，赶快去下单吧!");
                redisTemplate.opsForSet().add("seckill:users:" + ps.getPsId(),userId);
            }else {
                redisTemplate.opsForList().rightPush("seckill:count:" + ps.getPsId(),ps.getGoodsId());
                logger.info("你已经抢购过该商品，请勿重复抢购！");
                throw new SecurityException("你已经抢购过该商品，请勿重复抢购！");
            }
        }else{
            logger.info("很抱歉，该商品已被抢光，下次再来吧！");
            throw new SecurityException("很抱歉，该商品已被抢光，下次再来吧！");
        }
    }

    public String sendOrderToQueue(String userid){
        logger.info("准备向队列发送信息");
        Map data = new HashMap();
        String orderNo = UUID.randomUUID().toString();
        data.put("userid",userid);
        data.put("orderNo",orderNo);
        rabbitTemplate.convertAndSend("exchange-order",null,data);
        return orderNo;
    }

}