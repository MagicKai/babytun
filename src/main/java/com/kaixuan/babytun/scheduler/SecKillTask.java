package com.kaixuan.babytun.scheduler;

import com.kaixuan.babytun.dao.PromotionSeckillDao;
import com.kaixuan.babytun.entity.PromotionSeckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SecKillTask {
    public static Logger logger = LoggerFactory.getLogger(SecKillTask.class);

    @Resource
    private PromotionSeckillDao promotionSeckillDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0/5 * * * * ?")
    public void startSecKill(){
        List<PromotionSeckill> unstartSeckills = promotionSeckillDao.findUnstartSeckill();
        for(PromotionSeckill ps : unstartSeckills){
            logger.info(ps.getPsId() + "秒杀活动已启动");
            redisTemplate.delete("seckill:count:"+ps.getPsId());
            for (int i = 0; i < ps.getPsCount(); i++) {
                redisTemplate.opsForList().rightPush("seckill:count:"+ps.getPsId(),ps.getGoodsId());
            }
            ps.setStatus(1);
            promotionSeckillDao.update(ps);
        }
    }
}
