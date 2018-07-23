package com.ater.modules.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ater.common.utils.*;
import com.ater.modules.entity.*;
import com.ater.modules.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author ater
 * @email ${email}
 * @date 2018-06-08 16:16:00
 */

@Api(value = "任务相关接口", description = "任务相关接口")
@RestController
@RequestMapping("/yTaskUser/ytaskuser")
public class YTaskUserController {
    @Autowired
    private YTaskUserService yTaskUserService;
    @Autowired
    private YTaskService yTaskService;
    @Autowired
    private YPointService yPointService;
    @Autowired
    private YFetalMovementService yFetalMovementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("yTaskUser:ytaskuser:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<YTaskUserEntity> yTaskUserList = yTaskUserService.queryList(query);
        int total = yTaskUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(yTaskUserList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 我的任务今天列表接口
     */
    @ApiOperation(value = "我的任务今天列表接口")
    @RequestMapping(value = "/myTaskList", method = RequestMethod.POST)
    public R myTaskList(@ApiParam(value = "用户Id userId ", required = true) @RequestBody Map<String, Object> params) {
        String userId = (String) params.get("userId");
//        params.put("taskStatus", "0");
//        List<YTaskUserVo> yTaskUserList1 = yTaskUserService.queryListByUserId(params);
        //如果查询出来的任务全部是待解锁的任务 则有一个置为1
//        if (yTaskUserList.size() > 0) {
//            if (yTaskUserList.size() == yTaskUserList1.size()) {
//                //String userId = (String) params.get("userId");
//                YTaskUserEntity yTaskUserEntity = yTaskUserService.selectOneByUserId(userId);
//                if (yTaskUserEntity != null) {
//                    yTaskUserEntity.setTaskStatus(1);
//                    yTaskUserService.update(yTaskUserEntity);
//                }
//            }
//        } else {
//            //y_user_task没有数据
//            List<YTaskEntity> taskList = yTaskService.selectTask();
//            for (int i = 0; i < taskList.size(); i++) {
//                String taskUserId = MyUtil.generateId();
//                YTaskUserEntity yTaskUserEntity = new YTaskUserEntity();
//                yTaskUserEntity.setTaskId(taskList.get(i).getTaskId());
//                if (i == 0) {
//                    yTaskUserEntity.setTaskStatus(1);
//                } else {
//                    yTaskUserEntity.setTaskStatus(0);
//                }
//                yTaskUserEntity.setTaskUserId(taskUserId);
//                yTaskUserEntity.setUserId(userId);
//                yTaskUserEntity.setTaskPriority(taskList.get(i).getTaskPriority());
//                yTaskUserService.save(yTaskUserEntity);
//            }
//        }
//        params.remove("taskStatus");
        params.put("taskPriority", 2);
        YTaskUserEntity entity = yTaskUserService.queryTodayPriority2(params); //查询出优先级为2的任务
        if(entity != null) {
            int todayFinishFhrNum = yFetalMovementService.checkTodayByUserId(userId);
            //查询出来的此用户的今日胎心记录有数据就把这个胎心监测任务状态变为2,否则变为1
            if(entity.getTaskStatus() != null && entity.getTaskStatus() != 0 && todayFinishFhrNum > 0) {
                entity.setTaskStatus(2);
                YTaskUserEntity entity2 = yTaskUserService.queryTodayPriority3(params); //查询出优先级为3的任务
                entity2.setTaskStatus(1);
                yTaskUserService.update(entity2);
                yTaskUserService.update(entity);
            }
        }
        params.put("taskPriority", 3);
        YTaskUserEntity entity1 = yTaskUserService.queryTodayPriority3(params); //查询出优先级为3的任务
        if(entity1 != null) {
            int todayFinishFhrNum = yFetalMovementService.checkTodayPdByUserId(userId);
            //查询出来的此用户的今日胎心记录有数据就把这个胎心监测任务状态变为2,否则变为1
            if(entity1.getTaskStatus() != null && entity1.getTaskStatus() != 0 && todayFinishFhrNum > 0) {
                entity1.setTaskStatus(2);
                yTaskUserService.update(entity1);
            }
        }
        params.remove("taskPriority");
        List<YTaskUserVo> yTaskUserList = yTaskUserService.queryListByUserId(params);
        return R.ok("获取成功").put("yTaskUserList", yTaskUserList);
    }

    /**
     * 我的任务详情 queryOne
     */
    @ApiOperation(value = "我的任务详情")
    @RequestMapping(value = "/myTaskOne", method = RequestMethod.POST)
    public R myTaskOne(@ApiParam(value = "用户任务Id taskUserId ", required = true) @RequestBody Map<String, Object> params) {
        String taskUserId = (String) params.get("taskUserId");
        YTaskUserVo yTaskUserOne = yTaskUserService.queryOne(taskUserId);
        return R.ok("获取成功").put("yTaskUserOne", yTaskUserOne);
    }

    /**
     * 去完成任务
     */
    @ApiOperation(value = "去完成任务")
    @RequestMapping(value = "/toFinish", method = RequestMethod.POST)
    public R toFinish(@ApiParam(value = "用户任务Id taskUserId（必填 详情里面返回了） 用户Id userId（必填 详情里面返回了）   完成状态 0：待解锁  1：未完成 2 已完成 taskStatus(必填 将状态为1的变为2 )", required = true) @RequestBody Map<String, Object> params) {
        String taskUserId = (String) params.get("taskUserId");
        String taskStatus = (String) params.get("taskStatus");
        YTaskUserEntity yTaskUser = new YTaskUserEntity();
        yTaskUser.setTaskUserId(taskUserId);
        yTaskUser.setTaskStatus(Integer.parseInt(taskStatus));
        yTaskUserService.update(yTaskUser);
        //完成之后 将下一个待解锁状态改为未完成状态
        String userId = (String) params.get("userId");
        YTaskUserEntity yTaskUserEntity = yTaskUserService.selectOneByUserId(userId);
        if (yTaskUserEntity != null) {
            switch(yTaskUserEntity.getTaskPriority()) {
                case 1: //普通线下任务
                    yTaskUserEntity.setTaskStatus(1);
                    break;

                case 2: //监测胎心的任务
                    int todayFinishFhrNum = yFetalMovementService.checkTodayByUserId(userId);
                    //查询出来的此用户的今日胎心记录有数据就把这个胎心监测任务状态变为2,否则变为1
                    yTaskUserEntity.setTaskStatus(todayFinishFhrNum > 0 ? 2 : 1);
                    break;

                case 3: //判读胎心监测图的任务
                    int todayFinishPdFhrNum = yFetalMovementService.checkTodayPdByUserId(userId);
                    //查询出来的此用户的今日胎心记录有数据就把这个胎心监测任务状态变为2,否则变为1
                    yTaskUserEntity.setTaskStatus(todayFinishPdFhrNum > 0 ? 2 : 1);
                    break;
            }
            yTaskUserService.update(yTaskUserEntity);
        }
        //完成之后积分加积分 判断是否存在该用户
        YPointEntity yPointEntity = yPointService.selectPoint(userId, "2");
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        int point = yTaskService.selectPointByTaskId(taskUserId);
        if (yPointEntity != null) {
            //从任务表中拿积分
            map.put("point", "point+" + point);
            yPointService.updatePoint(map);
        } else {
            map.put("point", point + "");
            map.put("pointType", "2");
            map.put("pointId", MyUtil.generateId());
            yPointService.insertPoint(map);
        }
        return R.ok().put("point", point);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{taskUserId}")
    @RequiresPermissions("yTaskUser:ytaskuser:info")
    public R info(@PathVariable("taskUserId") String taskUserId) {
        YTaskUserEntity yTaskUser = yTaskUserService.queryObject(taskUserId);

        return R.ok().put("yTaskUser", yTaskUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("yTaskUser:ytaskuser:save")
    public R save(@RequestBody YTaskUserEntity yTaskUser) {
        yTaskUserService.save(yTaskUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("yTaskUser:ytaskuser:update")
    public R update(@RequestBody YTaskUserEntity yTaskUser) {
        yTaskUserService.update(yTaskUser);

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("yTaskUser:ytaskuser:delete")
    public R delete(@RequestBody String[] taskUserIds) {
        yTaskUserService.deleteBatch(taskUserIds);
        return R.ok();
    }

}
