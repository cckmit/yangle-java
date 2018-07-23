package com.ater.modules.direct;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 描述: 接收者
 * @author:
 * @create:
 */
@Component
@RabbitListener(queues = "direct")
public class DirectReceiver {

    public void process(String message) {
        System.out.println("接收者 DirectReceiver," + message);
    }
}
