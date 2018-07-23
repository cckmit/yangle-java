package com.ater.modules.task;


import com.ater.common.utils.Constant;
import com.ater.modules.entity.TaskEntity;
import com.ater.modules.service.ScheduleJobService;
import com.ater.modules.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//@Component("dispatchJob")
public class DispatchJob {

    @Autowired
    TaskService taskService;

    @Autowired
    ScheduleJobService scheduleJobService;

    public void notifyfailed(String messageId) {
        TaskEntity entity = taskService.queryObject(messageId);

        if (entity.getResult() == Constant.TaskStatus.FAILD.getValue()) {
            //todo 添加其他异常处理逻辑

        }
        if (entity.getResult() == Constant.TaskStatus.START.getValue()) {
            entity.setResult(Constant.TaskStatus.MANUAL.getValue());
            //todo 发送指令到后台分配或者其他逻辑
        }
        //更新Task
        taskService.update(entity);
        //停止此job
        scheduleJobService.stopJobByMID(messageId);

        System.out.println("notifyfailed Job executed");
    }
}
