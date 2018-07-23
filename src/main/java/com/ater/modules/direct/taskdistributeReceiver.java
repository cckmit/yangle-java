package com.ater.modules.direct;

import com.alibaba.fastjson.JSON;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author
 * @create
 **/
@Component
@RabbitListener(queues = "task_distribute")
public class taskdistributeReceiver implements ChannelAwareMessageListener {

    @Autowired
    TaskService taskService;
    @Autowired
    private IUserService userServiceAPI;
    @Autowired
    private  WebSocket webSocketServerEndpoint;

    @Autowired
    private  ThreadPoolTaskExecutor poolTaskExecutor;
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String body = new String(message.getBody(), "utf-8");//转换消息，我们是使用json数据格式

        poolTaskExecutor.execute(new Runnable() {   //多线程处理
            @Async
            public void run() {
//                Jedis jedis = jedisShardInfo.createResource();
//                jedis.sadd(TopicRabbitConfig.TRANSACTION_QUEUE, bodythreadPoolthreadPoolthreadPool);//添加到key为当前消息类型的集合里面，防止丢失消息
                System.out.println("task_distribute 开始");

                JSONObject jsonObject = JSON.parseObject(body);
                TaskMessage taskMessage = JSONObject.toJavaObject(jsonObject, TaskMessage.class);
           //     TaskEntity taskEntity = taskService.queryOneByMId(taskMessage.getMQID());
                TaskEntity taskEntity = taskService.queryObject(taskMessage.getMQID());
                //todo 收集需要通知的用户，并且，发送并更新数据
                //0. 根据相关参数获取需要发送的用户
               String related_info= taskEntity.getRelatedInfo();//根据相关参数孕妇Id
                Map<String, Object> map = new HashMap<String, Object>();
                List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
               List<User> userList =userServiceAPI.selectDoctorList("2");

                //2. 其他需要的参数等扩展
              //  taskEntity.setParams("{'params':'333','params':'444'}");
                try {
                    //根据孕妇的Id查询出孕妇的名字
                  User user=  userServiceAPI.queryuser(related_info,"1");
                  String userName=user.getUserName();
                    //3. 设置状态
                    taskEntity.setResult(Constant.TaskStatus.SENDED.getValue());
                    //判断list的长度

//                    for (User userOne:userList) {
//                        String userId=userOne.getUserId();
//                        map.put("userId",userId);
//                        listMap.add(map);
//                        //4.用户发送逻辑
//                        Map<String, Object> sendMap = new HashMap<String, Object>();
//                        sendMap.put("message",userName+"孕妇已申请判断");
//                        sendMap.put("mqid",taskMessage.getMQID());
//                    webSocketServerEndpoint.sendInfo( JSONUtils.toJson(sendMap),userId);
//                    }
                    String aa="";
                    for(int i=0;i<userList.size();i++){
                           aa+=","+userList.get(i).getUserId();

                        //4.用户发送逻辑
                         Map<String, Object> sendMap = new HashMap<String, Object>();
                      sendMap.put("message",userName+"孕妇已申请判断");
                       sendMap.put("mqid",taskMessage.getMQID());
                   webSocketServerEndpoint.sendInfo( JSONUtils.toJson(sendMap),userList.get(i).getUserId());
                    }
                    taskEntity.setUsers(aa.substring(1,aa.length()));
                    Thread.sleep(10000);

                    //手工返回ACK，通知此消息已经争取消费
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                } catch (Exception e) {
                    // logic send to user
                    taskEntity.setResult(Constant.TaskStatus.FAILD.getValue());

                }

                //更新发送情况
                taskService.update(taskEntity);
                System.out.println("task_distribute 完成");
            }
        });
    }
}
