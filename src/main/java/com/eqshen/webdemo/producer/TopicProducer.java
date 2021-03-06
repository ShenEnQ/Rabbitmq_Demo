package com.eqshen.webdemo.producer;

import com.eqshen.webdemo.config.MqConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class TopicProducer {
    @Resource(name = "rabbitTemplatenew")
    private RabbitTemplate template;



    public void send(String msg){
        System.out.println("send msg:"+msg);
//        template.convertAndSend(MqConstant.TOPIC_EX,MqConstant.ROUTING_TOPIC_TAG_RQ,msg.getBytes());
        MessageProperties mp = new MessageProperties();
        mp.setMessageId(UUID.randomUUID().toString());
        Message msgObj = new Message(msg.getBytes(),mp);
        template.send(MqConstant.TOPIC_EX,MqConstant.ROUTING_TOPIC_TAG_RQ,msgObj);
    }
}
