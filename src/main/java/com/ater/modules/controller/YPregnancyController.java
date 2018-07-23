package com.ater.modules.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import com.ater.common.fastdfs.FileUtil;
import com.ater.common.utils.*;
import com.ater.modules.entity.*;
import com.ater.modules.service.*;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 *
 * @author ater
 * @email ${email}
 * @date 2018-05-31 19:02:50
 */
@Api(value = "首页", description = "首页")
@RestController
@RequestMapping("/pregnancy")
public class YPregnancyController {
	@Autowired
	private YPregnancyService yPregnancyService;
	//	@Autowired
//	private FastDFSClient fastDFSClient;
	@Value("${upload_path}")
	private String uploadPath;
	@Value("${upload_dir}")
	private String uploadDir;

	@Autowired
	private YBabyStatusService yBabyStatusService;
	@Autowired
	private YSowingMapService ySowingMapService;
	@Autowired
	private YTipsService yTipsService;
	@Autowired
	private YKnowledgeService yKnowledgeService;
	@Autowired
	private YTaskService yTaskService;
	@Autowired
	private YTaskUserService yTaskUserService;
	@Autowired
	private YSignInService ySignInService;
	@Autowired
	private IUserService userServiceAPI;
	@Autowired
	private YFetalMovementService yFetalMovementService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pregnancy:ypregnancy:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		List<YPregnancyEntity> yPregnancyList = yPregnancyService.queryList(query);
		int total = yPregnancyService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(yPregnancyList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{perId}")
	@RequiresPermissions("pregnancy:ypregnancy:info")
	public R info(@PathVariable("perId") String perId){
		YPregnancyEntity yPregnancy = yPregnancyService.queryObject(perId);

		return R.ok().put("yPregnancy", yPregnancy);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("pregnancy:ypregnancy:save")
	public R save(@RequestBody YPregnancyEntity yPregnancy){
		yPregnancyService.save(yPregnancy);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("pregnancy:ypregnancy:update")
	public R update(@RequestBody YPregnancyEntity yPregnancy){
		yPregnancyService.update(yPregnancy);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("pregnancy:ypregnancy:delete")
	public R delete(@RequestBody String[] perIds){
		yPregnancyService.deleteBatch(perIds);

		return R.ok();
	}


	/**
	 * 文件上传到分布式文件系统
	 */
	@ApiOperation("分布式上传文件")
	@RequestMapping(value = "/fastdfsUpload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadToFastDFS(@RequestParam("file") MultipartFile file) {
//		Map<String, Object> m = new HashMap<>();
//		if (file != null && !file.isEmpty()) {
//			try {
//				String path = uploadPath + fastDFSClient.uploadFile(file.getBytes(), file.getOriginalFilename());
//				System.out.println("文件上传地址" + path);
//				m.put("code", 0);
//				m.put("url", fastDFSClient.uploadFile(file.getBytes(), file.getOriginalFilename()));
//				m.put("msg", "上传成功");
//			} catch (Exception e) {
//				e.printStackTrace();
//				m.put("code", "fail");
//				m.put("msg", "上传失败");
//			}
//		} else {
//			m.put("code", "lose");
//			m.put("msg", "参数丢失");
//		}
//		return m;
		BufferedOutputStream bw = null;
		String fileName = file.getOriginalFilename();
		//判断是否有文件
		if(org.apache.commons.lang.StringUtils.isNotBlank(fileName)) {
			//输出到fuwq路径
			String filePath=UUID.randomUUID().toString()+ FileUtil.getFileType(fileName);
			String outFilea=uploadDir + filePath;

			File outFile = new File(outFilea);
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

			byte[] bytes = new byte[0];
			try {
				bytes = FileUtil.toByteArray(outFilea);
			} catch (IOException e) {
				e.printStackTrace();
			}

			//String fileUrl = fastDFSClient.uploadFile(bytes, fileName);

			return R.ok().put("filepath",filePath);
		}else{
			return R.error(400,"文件名不能为空");
		}
	}

	/**
	 * 首页
	 * @return
	 * @throws Exception
	 */
	@ApiOperation("首页")
	@ResponseBody
	@RequestMapping(value = "/firstMenu", method = RequestMethod.POST)
	public String firstMenu( @ApiParam(value = " 生日birthday 末次月经 lastMenstruation  设备Id  equipmentId预产日期 pregnancyDate 必填 例子：2019-3-25  userId(不是必填的)    返回的结果中 taskStatus 0：待解锁  1：未完成 2 已完成", required = true) @RequestBody Map<String,Object> params) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		try {
			//根据预产日期查询
			String pregnancyDate= (String) params.get("pregnancyDate");
			String birthday=(String)params.get("birthday");
			String lastMenstruation=(String)params.get("lastMenstruation");
			String equipmentId=(String)params.get("equipmentId");
			String userId = (String) params.get("userId");
			//判断该设备Id是否已存在
			YPregnancyEntity yPregnancyEntity=yPregnancyService.queryOneByEquipmentId(equipmentId);
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			String  pregnancyWeek=PregnancyHelp.getCurrent(pregnancyDate);
			long  babyBirth=PregnancyHelp.getBorn(pregnancyDate);
			if(yPregnancyEntity!=null){
				//存在更新
				yPregnancyEntity.setEquipmentId(equipmentId);
				if(!StringUtils.isEmpty(birthday)){
					yPregnancyEntity.setBirthday(df1.parse(birthday));
				}
				if(!StringUtils.isEmpty(lastMenstruation)){
					yPregnancyEntity.setLastMenstruation(df1.parse(lastMenstruation));
				}

				yPregnancyEntity.setPregnancyDate(df1.parse(pregnancyDate));
				yPregnancyService.update(yPregnancyEntity);
			}else{
				YPregnancyEntity yPregnancy=new YPregnancyEntity();
				String preId=MyUtil.generateId();
				yPregnancy.setPerId(preId);
				yPregnancy.setEquipmentId(equipmentId);
				if(!StringUtils.isEmpty(birthday)){
					yPregnancy.setBirthday(df1.parse(birthday));
				}
				if(!StringUtils.isEmpty(lastMenstruation)){
					yPregnancy.setLastMenstruation(df1.parse(lastMenstruation));
				}
         	yPregnancy.setPregnancyDate(df1.parse(pregnancyDate));
				yPregnancyService.save(yPregnancy);
			}

			//宝宝怀孕周数
			// 	int week=PregnancyHelp.getWeek(pregnancyDate);
			int week=1;
			//宝宝情况
			YBabyStatusEntity yBabyStatusEntity=yBabyStatusService.queryOne(week);
			if(yBabyStatusEntity!=null){
				yBabyStatusEntity.setPregnancyWeek(pregnancyWeek);
				yBabyStatusEntity.setBabyBirth(babyBirth);
				yBabyStatusEntity.setBadyPicture(uploadPath+yBabyStatusEntity.getBadyPicture());
			}
			map.put("BabyStatus",yBabyStatusEntity);
			//今日任务
			//判断用户是否登录
			List<YTaskUserVo> taskList;
			if(StringUtils.isEmpty((String) params.get("userId"))){
				taskList = yTaskService.selectTask();
				map.put("taskListNum", yTaskService.selectTaskNum());
			}else{
				taskList = yTaskService.selectTaskByUserId((String) params.get("userId"));
				if (taskList.size() > 0) {
					int taskNum = 0;
					YTaskUserVo yTaskUserEntity = null;
					for(int i = 0; i < taskList.size(); i++) {
						if(taskList.get(i).getTaskStatus() == null || taskList.get(i).getTaskStatus().equals("0")) {
							taskNum++;
						}
						yTaskUserEntity = taskList.get(0);
					}
					if(taskNum >= 3) { //如果首页显示的三条任务状态都为null或者都为0那么更新第一条状态为1
						yTaskUserEntity.setTaskStatus(1);
						yTaskUserService.updateStatus(yTaskUserEntity);
					}
					taskNum = 0;
				} else {
					//y_user_task没有数据
					List<YTaskUserVo> yTaskList = yTaskService.selectTask();
					for (int i = 0; i < yTaskList.size(); i++) {
						String taskUserId = MyUtil.generateId();
						YTaskUserEntity yTaskUserEntity = new YTaskUserEntity();
						yTaskUserEntity.setTaskId(yTaskList.get(i).getTaskId());
						if (i == 0) {
							yTaskUserEntity.setTaskStatus(1);
						} else {
							yTaskUserEntity.setTaskStatus(0);
						}
						yTaskUserEntity.setTaskUserId(taskUserId);
						yTaskUserEntity.setUserId(userId);
						yTaskUserEntity.setTaskPriority(yTaskList.get(i).getTaskPriority());
						yTaskUserService.save(yTaskUserEntity);
					}
				}
				map.put("taskListNum", yTaskService.selectTaskNumByUserId((String) params.get("userId")));



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
				taskList = yTaskService.selectTaskByUserId((String) params.get("userId"));
			}
			map.put("taskList", taskList);
			//提示
			YTipsEntity tip= yTipsService.queryOne();
			map.put("tip",tip);
			//轮播图
			List<YSowingMapEntity> sowMapList=ySowingMapService.queryAllList();
			for (YSowingMapEntity sowMapOne:sowMapList
					) {
				sowMapOne.setSowPicture(uploadPath+sowMapOne.getSowPicture());
			}

			map.put("sowMapList",sowMapList);
			//今日知识 三条
			List<YKnowledgeEntity> knowledgeList=yKnowledgeService.selectThreeKnowledge();
//			for (YKnowledgeEntity knowledgeOne:knowledgeList) {
//				knowledgeOne.setKnowPicture(uploadPath+knowledgeOne.getKnowPicture());
//				knowledgeOne.setKnowBigPicture(uploadPath+knowledgeOne.getKnowBigPicture());
////                knowledgeOne.setGmtCreate(DateUtils.format(knowledgeOne.get));
//			}
			map.put("knowledgeList",knowledgeList);
			map.put("resultMsg", "获取成功");
			map.put("resultCode", 200);
			return JSONUtils.toJson(map);

		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultMsg", "失败");
			map.put("resultCode", 404);
			return JSONUtils.toJson(map);
		}

	}



	/**
	 * 列表  首页显示多知识
	 */
	@ApiOperation(value = "首页点击更多知识")
	@RequestMapping(value = "/knowledgeList",method = RequestMethod.POST)
	/*	@RequiresPermissions("knowledge:yknowledge:list")*/
	public R knowledgeList( @ApiParam(value = "参数传的是map示例:{page:\"1\",limit:\"10\"} page指的是当前页码数 limit是指每页的个数", required = true) @RequestBody Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		List<YKnowledgeEntity> yKnowledgeList = yKnowledgeService.queryList(query);
		int total = yKnowledgeService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(yKnowledgeList, total, query.getLimit(), query.getPage());

		return R.ok("获取成功").put("data", pageUtil);
	}

	@ApiOperation(value = "签到")
	@ResponseBody
	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public String sign(@ApiParam(value = "用户Id,userId,角色 userRole", required = true) @RequestBody Map<String, Object> params){
		String userId= (String) params.get("userId");
		String userRole= (String) params.get("userRole");
		return ySignInService.sign(userId,userRole);
	}
	@ApiOperation(value = "签到的信息")
	@ResponseBody
	@RequestMapping(value = "/signInfo", method = RequestMethod.POST)
	public String signInfo(@ApiParam(value = "用户Id,userId 角色 userRole 返回的isSign 0表示未签到 1表示已签到", required = true) @RequestBody Map<String, Object> params){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, String> map = new HashMap<String, String>();
		String userId= (String) params.get("userId");
		String userRole= (String) params.get("userRole");
		User user=	userServiceAPI.queryuser(userId,userRole);
		if(user!=null){
			map.put("userId",userId);
			Map<String, Object> signResultMap  = ySignInService.selectSign(map);
			if(signResultMap != null){
				String diffDay = StringUtils.getMapValue("diffDay", signResultMap);
				if(diffDay.equals("0")) {
					//	int signSerialTimes = ySignInService.selectSerialTimes(userId);
					resultMap.put("resultMsg", "获取成功");
					resultMap.put("resultCode", "200");
					resultMap.put("isSign","1");//1表示已签到
					resultMap.put("signSerialTimes",  StringUtils.getMapValue("signSerialTimes", signResultMap));
					return JSONUtils.toJson(resultMap);
				}else{
					resultMap.put("resultMsg", "获取成功");
					resultMap.put("resultCode", "200");
					resultMap.put("isSign","0");//0表示未签到
					int signSerialTimes = Integer.valueOf(StringUtils.getMapValue("signSerialTimes", signResultMap));
					if(signSerialTimes == 7) {
						YSignInEntity ySignInEntity = new YSignInEntity();
						ySignInEntity.setUserId(userId);
						ySignInEntity.setSignSerialTimes(0);
						ySignInService.updateSerialTimes(ySignInEntity);
					}
					resultMap.put("signSerialTimes",  ySignInService.selectSerialTimes(userId));
					return JSONUtils.toJson(resultMap);
				}
			}else{
				resultMap.put("resultMsg", "获取成功");
				resultMap.put("resultCode", "200");
				resultMap.put("signSerialTimes", "0");
				resultMap.put("isSign","0");
				return JSONUtils.toJson(resultMap);
			}
		}else{
			resultMap.put("resultMsg","用户未登录");
			resultMap.put("resultCode","404");
			return JSONUtils.toJson(resultMap);
		}

	}
}
