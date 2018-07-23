package com.ater.modules.controller;

import com.alibaba.fastjson.JSONObject;

import com.ater.common.annotation.SysLog;
import com.ater.common.utils.*;

import com.ater.modules.entity.*;
import com.ater.modules.service.ScheduleJobService;
import com.ater.modules.service.TaskService;
import com.ater.modules.service.YFetalMovementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * 任务生成
 *
 * @author Yam
 * @email
 * @date
 */
@RestController
@RequestMapping("/api")
@Api(value = "医生抢单", description = "医生抢单")
public class ApiTaskController {
//    @Autowired
//    private ScheduleJobService scheduleJobService;

    @Autowired
    private  TaskService taskService;
    @Autowired
    private YFetalMovementService yFetalMovementService;
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private WebSocket webSocketServerEndpoint;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @ApiOperation("任务生成")
    @RequestMapping(value = "/neworder", method = RequestMethod.POST)
     public R NewOrder(@ApiParam(value = "平台方式platform  业务相关参数related_info  表示孕妇的userId", required = true)   @RequestBody Map<String,Object> params) {
        String platform=(String) params.get("platform");
        String related_info=(String) params.get("related_info");
        String mid = UUID.randomUUID().toString();
       long curren = System.currentTimeMillis();
//        curren += 5 * 60 * 1000;//加5分钟
        curren += 60 * 1000;
        Date date = new Date(curren);
        String cronExpression = DateUtils.format(date, "ss mm HH dd MM ? yyyy-yyyy");

        try {
            //开启Order状态监控Job
            ScheduleJobEntity entity = new ScheduleJobEntity();

            entity.setBeanName("dispatchJob");
            entity.setMethodName("notifyfailed");
            entity.setRemark(mid);
            entity.setCreateTime(new Date());
            entity.setCronExpression(cronExpression);
            entity.setParams(mid);
            //scheduleJobService.save(entity);

            //添加Task任务
            TaskEntity task = new TaskEntity();
            task.setId(mid);
            task.setPlatform(platform);
            task.setCreateDate(new Date());
            task.setRelatedInfo(related_info);//存储业务逻辑的相关数据
            task.setResult(Constant.TaskStatus.START.getValue());//开始状态
     //       task.setJobid(entity.getJobId().toString());
            task.setMQID(mid);//mqid
            taskService.save(task);

            //插入Order数据到MQ，通知其接单人
            TaskMessage taskMessage = new TaskMessage();
            taskMessage.setMbody(related_info);
            taskMessage.setMid(mid);
            taskMessage.setMQID(mid);
            taskMessage.setPlatform(platform);
            taskMessage.setTime(new Date().getTime());
            String context = JSONObject.toJSONString(taskMessage);

            String routeKey = "task_distribute";
            this.rabbitTemplate.convertAndSend(routeKey, context);
        } catch (Exception e) {
            logger.error("RRExceptionHandler 异常处理失败", e);
            return R.error(99, e.getMessage());
        }
        return R.ok("OK").put("mqid",mid);
    }


    /**
     * 医生接单  /api/takeOrder/app/08b8334b-10c1-46ab-bf63-ba9e7146d54d
     */
//

    @ApiOperation("医生接单")
    @RequestMapping(value="/takeOrder", method = RequestMethod.POST)
   // public R TakeOrder(@PathVariable("platform") String platform,@PathVariable("mqid") String mqid,@PathVariable("userId") String userId){
     public R TakeOrder(@ApiParam(value = "平台方式platform  mqid  医生userId", required = true)   @RequestBody Map<String,Object> params){
        String platform=(String) params.get("platform");
        String mqid=(String) params.get("mqid");
        String userId=(String) params.get("userId");
          //判断是否已结单
        TaskEntity taskEntity=  taskService.queryOneByMIdAndResult(mqid,"2");
        if(taskEntity!=null){
            return  R.error("已有人接单");
        }
        TaskEntity entity= taskService.queryObject(mqid);//假设消息ID
        entity.setResult(Constant.TaskStatus.COMPLETED.getValue());//更新订单已完成
        taskService.update(entity);
        //插入Order数据到MQ，通知业务系统
        String mid = UUID.randomUUID().toString();
        TaskMessage taskMessage = new TaskMessage();
        taskMessage.setMbody(userId);
        taskMessage.setMid(mid);
        taskMessage.setMQID(mqid);
        taskMessage.setPlatform(platform);
        taskMessage.setTime(new Date().getTime());
        String context = JSONObject.toJSONString(taskMessage);


        String routeKey = "task_took";
        this.rabbitTemplate.convertAndSend(routeKey, context);
        return R.ok("接单成功");
    }
    /**
     * 测试后台向前台发送消息
     */
//    @ApiOperation("后台和前台进行交互  用户Id是活的    wss://yl.ibao365.net:8080/yangle/websocket/userId"  )
//    @RequestMapping(value="/pushVideoListToWeb",method=RequestMethod.POST,consumes = "application/json")
//    public Map<String,Object> pushVideoListToWeb(@RequestBody Map<String,Object> params) {
//        Map<String,Object> result =new HashMap<String,Object>();
//     String userId= (String) params.get("userId");
//        try {
//       webSocketServerEndpoint.sendInfo("孕妇申请判断",userId);
//            result.put("operationResult", true);
//        }catch (IOException e) {
//            result.put("operationResult", false);
//        }
//        return result;
//    }

    /**
     * 接到通知列表
     */
    @SysLog("接到通知列表")
    @RequestMapping("/tbTasklist")

    public R tbTasklist(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<YTbTaskEntity> ytbtaskList = yFetalMovementService.queryYTbTaskList(query);

        int total = yFetalMovementService.queryTbTaskTotal(query);

        PageUtils pageUtil = new PageUtils(ytbtaskList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }
}
