package com.ater.modules.direct;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.ater.common.utils.Constant;
import com.ater.common.utils.JSONUtils;
import com.ater.common.utils.WebSocket;
import com.ater.modules.entity.TaskEntity;
import com.ater.modules.entity.TaskMessage;
import com.ater.modules.entity.User;
import com.ater.modules.service.IUserService;
import com.ater.modules.service.TaskService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 描述:
 *
 * @author
 * @create
 **/
@Component
@RabbitListener(queues = "task_took")
public class tasktookReceiver  implements ChannelAwareMessageListener {

    @Autowired
    TaskService taskService;
    @Autowired
    private IUserService userServiceAPI;
    @Autowired
    ThreadPoolTaskExecutor poolTaskExecutor;

    @Autowired
    private WebSocket webSocketServerEndpoint;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String body = new String(message.getBody(), "utf-8");//转换消息，我们是使用json数据格式

        poolTaskExecutor.execute(new Runnable() {   //多线程处理
            @Async
            public void run() {
                System.out.println("task_took 开始");

                JSONObject jsonObject = JSON.parseObject(body);
                TaskMessage taskMessage = JSONObject.toJavaObject(jsonObject, TaskMessage.class);

                try {
                    //todo
                    //业务系统处理逻辑，调用业务系统接口等
                    //判断是否有人抢单成功 如果有的话则通知下一个人抢单失败前端传session过来
                  //向tb_task表中插入一条数据
                    TaskEntity entity= taskService.queryObject(taskMessage.getMQID());//假设消息ID
                    String userId=taskMessage.getMbody();
                    User user=  userServiceAPI.queryuser(userId,"2");
                    String userName=user.getUserName();
                    String userIdList=entity.getUsers();

                     TaskEntity task = new TaskEntity();
                    String id = UUID.randomUUID().toString();
                    task.setId(id);
                    task.setCreateDate(new Date());
                     task.setMQID(taskMessage.getMQID());//mqid
                    taskService.save(task);
                    List<TaskEntity> taskList= taskService.queryOneByMId(taskMessage.getMQID());
                    if (taskList.size()==2){
                        String[] userList=userIdList.split(",");
                       //通知其他接单的医生说已结单//除去已接单医生的
                        for (String userOne:userList) {
                            if(!userId.equals(userOne)){
                                webSocketServerEndpoint.sendInfo( userName+"医生已结单",userOne);
                            }
                        }
                     }
                    Thread.sleep(10000);
                   //手工返回ACK，通知此消息已经争取消费
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                } catch (Exception e) {
                    // logic send to user
                }
                System.out.println("task_took 完成");
            }
        });
    }
}
