package com.ater.modules.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ater.common.annotation.SysLog;
import com.ater.common.utils.*;
import com.ater.modules.entity.*;
import com.ater.modules.service.IUserService;
import com.ater.modules.service.TaskService;
import com.ater.modules.service.YFetalHeartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ater.modules.service.YFetalMovementService;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-05 14:01:08
 */
@Api(value = "胎心", description = "胎心")
@RestController
@RequestMapping("/yFetalMovement/yfetalmovement")
public class YFetalMovementController {
    @Autowired
    private YFetalHeartService yFetalHeartService;
    @Autowired
    private IUserService userServiceAPI;
    @Autowired
    private YFetalMovementService yFetalMovementService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private AmqpTemplate rabbitTemplate;


    private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 列表
	 */
    @SysLog("胎心数据列表")
	@RequestMapping("/list")
	/*@RequiresPermissions("yFetalMovement:yfetalmovement:list")*/
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YFetalMovementEntity> yFetalMovementList = yFetalMovementService.queryWebList(query);
        for (YFetalMovementEntity yFetalMovementOne:yFetalMovementList
             ) {
         int age= DateUtils.getAgeByBirth(yFetalMovementOne.getBirthday());
            yFetalMovementOne.setAge(age);
        }
		int total = yFetalMovementService.queryWebTotal(query);
		
		PageUtils pageUtil = new PageUtils(yFetalMovementList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

	
	/**
	 * 信息
	 */
    @SysLog("打印胎心数据")
    @ResponseBody
    @RequestMapping(value = "/printDetail", method = RequestMethod.POST)
    public R printDetail(@ApiParam(value = "参数：胎动Id   moveId(必填)    返回的 state表示 1：不合规  <20 分钟 、2：未提交>20分钟、3：待判读；判读后分为：4：已判断", required = true)  @RequestBody Map<String,Object> params) throws Exception {
        String moveId= (String) params.get("moveId");
        YFetalMovementEntity yFetalMovementEntity=  yFetalMovementService.queryPrint(moveId);

        return R.ok("获取成功").put("data", yFetalMovementEntity);
    }
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
//	@RequiresPermissions("yFetalMovement:yfetalmovement:save")
	public R save(@RequestBody YFetalMovementEntity yFetalMovement){
		yFetalMovementService.save(yFetalMovement);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
    @ApiOperation("修改胎心数据")
	@RequestMapping(value="/update",method= RequestMethod.POST)
    public R update(@ApiParam(value = "  moveId 胎心Id meanHeartRate:平均心率 monitoringTime监测时间  startTime开始时间  heartReard传的是这种数据 120：胎心率 100：宫所   12：heartTime胎心时间   [[120,100,12] ]  jsonStr字符串{moveId:\"\",meanHeartRate:\"\",startTime:\"\",monitoringTime:\"\",fetalMove:\"\",heartRecord:\"\" }")@RequestBody String jsonStr) throws ParseException {
      JSONObject jsonObj = JSONArray.parseObject(jsonStr);
       String moveId=jsonObj.getString("moveId");
       String meanHeartRate=jsonObj.getString("meanHeartRate");
       String startTime=jsonObj.getString("startTime");
       String monitoringTime=jsonObj.getString("monitoringTime");
       String heartRecord=jsonObj.getString("heartRecord");
       YFetalMovementEntity fetalMovementEntity =yFetalMovementService.queryObject(moveId);
       fetalMovementEntity.setFetalMove(Integer.parseInt(jsonObj.getString("fetalMove")));
       fetalMovementEntity.setMeanHeartRate(Double.valueOf(meanHeartRate));
       fetalMovementEntity.setMonitoringTime(monitoringTime);
       //  state状态 1：不合规  <20 分钟 、2：未提交>20分钟、3：待判读；4  已判读
     //  monitoringTime = monitoringTime.replaceAll("：", ":");
     //  String monitoringTime1 =monitoringTime.substring(0,monitoringTime.indexOf(":"));
//       if(Integer.parseInt(monitoringTime1)<20){
//           fetalMovementEntity.setState(1);
//       }else{
           fetalMovementEntity.setState(2);
     //  }

       DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       fetalMovementEntity.setStartTime(df.parse(startTime));
       fetalMovementEntity.setHeartRecord(heartRecord);
       yFetalMovementService.update(fetalMovementEntity);
     	return R.ok("修改成功");
	}

    /**
     * 更新胎心数据（后台）
     * @param yFetalMovement
     * @return
     */
    @RequestMapping("/updateWeb")
     public R update(@RequestBody YFetalMovementEntity yFetalMovement){
        yFetalMovementService.update(yFetalMovement);
       return R.ok();
    }
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("yFetalMovement:yfetalmovement:delete")
	public R delete(@RequestBody String[] moveIds){
		yFetalMovementService.deleteBatch(moveIds);
		
		return R.ok();
	}
    //保存上传胎心数据 heartList:[{heartRate:"",uterineContraction:"",heartTime:""}]
    @ApiOperation("保存上传胎心数据")
    @RequestMapping(value="/saveFetalHeart",method= RequestMethod.POST)
    public R saveFetalHeart(@ApiParam(value = "  moveId 胎心Id meanHeartRate:平均心率 monitoringTime监测时间  startTime开始时间  heartReard传的是这种数据 120：胎心率 100：宫所   12：heartTime胎心时间   [[120,100,12] ]  jsonStr字符串{moveId:\"\",userId:\"\",meanHeartRate:\"\",startTime:\"\",monitoringTime:\"\",fetalMove:\"\",heartRecord:\"\" }")@RequestBody String jsonStr) throws ParseException {
        List<YFetalHeartEntity>  fetalHeartList = new ArrayList<YFetalHeartEntity>();
        // 将json字符串转换为json对象
        JSONObject jsonObj = JSONArray.parseObject(jsonStr);
        String userId=jsonObj.getString("userId");
         String meanHeartRate=jsonObj.getString("meanHeartRate");
        String startTime=jsonObj.getString("startTime");
        String monitoringTime=jsonObj.getString("monitoringTime");
        String heartRecord=jsonObj.getString("heartRecord");
        //判断不能为空
         if(StringUtils.isEmpty(userId)){
             return  R.error("用户Id不能为空");
         }
         if(StringUtils.isEmpty(meanHeartRate)){
             return  R.error("平均心率不能为空");
         }
        if(StringUtils.isEmpty(startTime)){
            return  R.error("开始时间不能为空");
        }
        if(StringUtils.isEmpty(monitoringTime)){
            return  R.error("监测时间不能为空");
        }
        if(StringUtils.isEmpty(heartRecord)){
            return  R.error("胎心数据不能为空");
        }

        YFetalMovementEntity fetalMovementEntity = new YFetalMovementEntity();
        fetalMovementEntity.setUserId(userId);
         fetalMovementEntity.setMoveId(MyUtil.generateId());

        fetalMovementEntity.setFetalMove(Integer.parseInt(jsonObj.getString("fetalMove")));
        fetalMovementEntity.setMeanHeartRate(Double.valueOf(meanHeartRate));
        fetalMovementEntity.setMonitoringTime(monitoringTime);
      //  state状态 1：不合规  <20 分钟 、2：未提交>20分钟、3：待判读；4  已判读
        monitoringTime = monitoringTime.replaceAll("：", ":");
       String monitoringTime1 =monitoringTime.substring(0,monitoringTime.indexOf(":"));
//       if(Integer.parseInt(monitoringTime1)<20){
//           fetalMovementEntity.setState(1);
//       }else{
           fetalMovementEntity.setState(2);
      // }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fetalMovementEntity.setStartTime(df.parse(startTime));
        fetalMovementEntity.setHeartRecord(heartRecord);
        yFetalMovementService.save(fetalMovementEntity);
       return R.ok("上传保存成功").put("moveId",fetalMovementEntity.getMoveId());
    }
    /**
     * 我的胎心
     */
    @ApiOperation("我的胎心")
    @ResponseBody
    @RequestMapping(value = "/myfetalHeart", method = RequestMethod.POST)
    public R myfetalHeart(@ApiParam(value = "参数传的是map示例:{page:\"1\",limit:\"10\",userId:\"\",userRole:\"\",state:\"\"} page指的是当前页码数 limit是指每页的个数 state 未提交传2  已提交参数传3,4 以逗号隔开", required = true) @RequestBody(required = true) Map<String, Object> params ) throws Exception {
        String userId= (String) params.get("userId");
        String userRole=(String)params.get("userRole");
        if (!StringUtils.isEmpty(userId)) {
            User user=userServiceAPI.queryuser(userId,userRole);
            if(user!=null){
                //查询列表数据
                Query query = new Query(params);
                List<YFetalMovementEntity> yFetalMovementList = yFetalMovementService.queryList(query);
                for(int i = 0; i < yFetalMovementList.size(); i++) {
                    if(i != yFetalMovementList.size() - 1) {
                        YFetalMovementEntity entity = yFetalMovementList.get(i);
                        YFetalMovementEntity entity1 = yFetalMovementList.get(i + 1);
                        if(DateUtils.format(entity.getStartTime()).equals(DateUtils.format(entity1.getStartTime()))) {
                            entity.setStartTime(null);
                        }
                    }
                }
                int total = yFetalMovementService.queryTotal(query);
               int notSubmitTotal=yFetalMovementService.queryTodayTotal(query);
               int submitTotal = yFetalMovementService.querySubmitTotal(query);
                //已提交判断和未提交判读
                PageUtils pageUtil = new PageUtils(yFetalMovementList, total,notSubmitTotal, submitTotal,query.getLimit(), query.getPage());
                return R.ok("获取成功").put("pageUtil", pageUtil);
            }else{
                return R .error(404,"该用户不存在");
            }
        } else {
            return R .error(404,"该用户未登录");
        }
    }
    /**
     * 胎心详细情况  moveId
     */
    @ApiOperation("胎心详细情况")
    @ResponseBody
    @RequestMapping(value = "/fetalMoveDetail", method = RequestMethod.POST)
    public R fetalMoveDetail(@ApiParam(value = "参数：胎动Id   moveId(必填)    返回的 state表示 1：不合规  <20 分钟 、2：未提交>20分钟、3：待判读；判读后分为：4：已判断", required = true)  @RequestBody Map<String,Object> params) throws Exception {
        String moveId= (String) params.get("moveId");
        YFetalMovementEntity yFetalMovementEntity=  yFetalMovementService.queryObject(moveId);

        return R.ok("获取成功").put("data", yFetalMovementEntity);
   }
    /**
     * 申请医生判断 更新状态为3
     */
    @ApiOperation("申请医生判断")
    @RequestMapping(value="/applicateRead", method = RequestMethod.POST)
    public R applicateRead(@ApiParam(value = "胎动Id moveId (必填);  状态填3是指已申请判读 state(必填 传3)", required = true) @RequestBody Map<String,Object> params
    ){
        String moveId= (String) params.get("moveId");
        String state= (String) params.get("state");
        YFetalMovementEntity yFetalMovementEntity=  yFetalMovementService.queryObject(moveId);
        if(yFetalMovementEntity==null){
            return R.error("moveId不存在");
        }
        YFetalMovementEntity yFetalMovement =new YFetalMovementEntity();
        yFetalMovement.setMoveId(moveId);
        yFetalMovement.setState(Integer.parseInt(state));
        yFetalMovementService.update(yFetalMovement);
        //申请完给医生发通知

  //      String platform=(String) params.get("platform");
        String platform="app";
        String related_info=yFetalMovementEntity.getUserId();
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
       return R.ok("申请成功");
    }

}
