package com.ater.config;




import com.ater.modules.direct.taskdistributeReceiver;
import com.ater.modules.direct.tasktookReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述: 配置默认的交换机模式
 *
 * Direct Exchange是RabbitMQ默认的交换机模式，也是最简单的模式，根据key全文匹配去寻找队列。
 *
 * @author yanpenglei
 * @create 2017-10-25 0:09
 **/
@Configuration
public class RabbitDirectConfig {

    private static final String task_took = "task_took";
    private static final String task_distribute = "task_distribute";

    @Bean
    public Queue tasktakeQueue() {
        return new Queue(task_took);
    }

    @Bean
    public Queue task_distributeQueue() {
        return new Queue(task_distribute);
    }

    @Bean
    public Queue directQueue() {
        return new Queue("direct");
    }

    //-------------------配置默认的交换机模式，可以不需要配置以下-----------------------------------
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    //绑定一个key "direct"，当消息匹配到就会放到这个队列中
    @Bean
    Binding bindingExchangeDirectQueue(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("direct");
    }
    // 推荐使用 helloQueue（） 方法写法，这种方式在 Direct Exchange 模式 多此一举，没必要这样写
    //---------------------------------------------------------------------------------------------


    /**
     * 接受消息的监听
     * 针对消费者配置
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer taskdistributeContainer(ConnectionFactory connectionFactory, taskdistributeReceiver transactionConsume) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        container.setQueues(task_distributeQueue());
        container.setQueueNames(task_distribute);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(transactionConsume);
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer tasktookContainer(ConnectionFactory connectionFactory, tasktookReceiver transactionConsume) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(task_took);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(transactionConsume);
        return container;
    }
}
