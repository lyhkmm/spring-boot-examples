package com.lyh.demo.Receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "lyhTest1")
public class Receive1 {

    //lyhTest1是发送者的队列名，如果不一样收不到消息
    @RabbitHandler
    public void receiver(String msg){
        System.out.println("Test1 receiver1:"+msg);
    }
}
