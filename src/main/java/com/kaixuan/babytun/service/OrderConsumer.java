package com.kaixuan.babytun.service;

import com.kaixuan.babytun.dao.OrderDao;
import com.kaixuan.babytun.entity.Order;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Component
public class OrderConsumer {
    public static Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @Resource
    private OrderDao orderDao;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue-order"),exchange = @Exchange(value = "exchange-order",type = "fanout")))
    @RabbitHandler
    public void handleMessage(@Payload Map data, Channel channel, @Headers Map<String, Object> headers) {
        logger.info("============获取到订单数据：" + data + "============");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Order order = new Order();
            order.setOrderNo(data.get("orderNo").toString());
            order.setOrderStatus(0);
            order.setUserid(data.get("userid").toString());
            order.setRecvName("XXX");
            order.setRecvAddress("asdasdas");
            order.setRecvMobile("1656335656");
            order.setCreateTime(new Date());
            order.setAmout(19.8f);
            order.setPostage(0f);
            orderDao.insert(order);
            Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(tag, false);
            logger.info(data.get("orderNo")+"订单已创建");
        } catch (IOException e) {
            logger.error("确认消费异常！");
        }
    }

}
