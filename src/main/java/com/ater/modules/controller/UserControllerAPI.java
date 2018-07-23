package com.ater.modules.controller;

import java.text.DateFormat;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.ater.common.entity.CacheUserInfo;
import com.ater.common.utils.*;
//import com.ater.modules.entity.SmsResultBean;
import com.ater.modules.entity.SmsResultBean;
import com.ater.modules.entity.User;
import com.ater.modules.entity.YPregnancyEntity;
import com.ater.modules.service.IUserService;
import com.ater.modules.service.YPregnancyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Maps;


@Api(value = "用户", description = "用户")
@RestController
@RequestMapping(value = "/userControllerAPI")
public class UserControllerAPI {

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private YPregnancyService yPregnancyService;
	@Autowired
	private IUserService userServiceAPI;
	@Value("${upload_path}")
	private String uploadPath;


	//计算预产期
	@ApiOperation("计算预产期")
	@ResponseBody
	@RequestMapping(value = "/preProdPeriod", method = RequestMethod.POST)
	public String preProdPeriod(@ApiParam(value = "设备Id equipmentId  生日 birthday 末次月经 lastMenstruation ", required = true) @RequestBody Map<String,Object> params) throws Exception {
		String equipmentId= (String) params.get("equipmentId");
		String lastMenstruation=(String) params.get("lastMenstruation");
		String birthday=(String) params.get("birthday");
		Map<String, Object> map = Maps.newHashMap();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(df.parse(lastMenstruation));
		Date borndayString=PregnancyHelp.getbornmanual(calendar);

		String preId=MyUtil.generateId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String pregnancyDate = sdf.format(borndayString.getTime());
		String  dataBirth=PregnancyHelp.getCurrent(pregnancyDate);
		System.out.println(pregnancyDate);
		//判断该设备Id是否已存在
		YPregnancyEntity yPregnancyEntity=yPregnancyService.queryOneByEquipmentId(equipmentId);
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		if(yPregnancyEntity!=null){
			//存在更新
			yPregnancyEntity.setEquipmentId(equipmentId);
			if(!StringUtils.isEmpty(birthday)){
				yPregnancyEntity.setBirthday(df1.parse(birthday));
			}
            if(!StringUtils.isEmpty(lastMenstruation)){
                yPregnancyEntity.setLastMenstruation(df.parse(lastMenstruation));
            }

			yPregnancyEntity.setPregnancyDate(df.parse(pregnancyDate));
			yPregnancyService.update(yPregnancyEntity);
		}else{
			YPregnancyEntity yPregnancy=new YPregnancyEntity();
			yPregnancy.setPerId(preId);
			yPregnancy.setEquipmentId(equipmentId);
			if(!StringUtils.isEmpty(birthday)){
				yPregnancy.setBirthday(df1.parse(birthday));
			}
            if(!StringUtils.isEmpty(lastMenstruation)){
                yPregnancy.setLastMenstruation(df.parse(lastMenstruation));
            }

			yPregnancy.setPregnancyDate(df.parse(pregnancyDate));
			yPregnancyService.save(yPregnancy);
		}

		map.put("resultMsg", "获取成功");
		map.put("resultCode", 200);
		map.put("pregnancyDate", pregnancyDate);
		map.put("dataBirth", dataBirth);

		return JSONUtils.toJson(map);
	}

	/**
	 * 用户注册
	 *
	 * @author
	 *
	 */
	@ApiOperation("用户注册")
	@ResponseBody
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public String userRegister( @ApiParam(value = "生日birthday 末次月经 lastMenstruation  预产日期 pregnancyDate  账号userAccount 密码 userPassword 用户角色 1:孕妇 2：机构医生 userRole 设备Id  equipmentId  验证码smsCode", required = true) @RequestBody Map<String,Object> params) throws Exception {
	    String userPassword=(String)params.get("userPassword");
	    String userAccount=(String)params.get("userAccount");
	    String userRole=(String)params.get("userRole");
		String smsCode=(String)params.get("smsCode");
		String equipmentId=(String)params.get("equipmentId");
		String birthday=(String)params.get("birthday");
		String lastMenstruation=(String)params.get("lastMenstruation");
		String pregnancyDate=(String)params.get("pregnancyDate");
		Map<String, Object> map = Maps.newHashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(sdf.format(new Date()));
		String userPassword1 = PasswordHelper.encryptPassword(userPassword);// 密码
       //判断该设备Id是否已存在
//		YPregnancyEntity yPregnancyEntity=yPregnancyService.queryOneByEquipmentId(equipmentId);
//		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
//		if(yPregnancyEntity!=null){
//			//存在更新
//			yPregnancyEntity.setEquipmentId(equipmentId);
//			yPregnancyEntity.setBirthday(df1.parse(birthday));
//			yPregnancyEntity.setLastMenstruation(df1.parse(lastMenstruation));
//			yPregnancyEntity.setPregnancyDate(df1.parse(pregnancyDate));
//			yPregnancyService.update(yPregnancyEntity);
//		}else{
//			YPregnancyEntity yPregnancy=new YPregnancyEntity();
//			String preId=MyUtil.generateId();
//			yPregnancy.setPerId(preId);
//			yPregnancy.setEquipmentId(equipmentId);
//			yPregnancy.setBirthday(df1.parse(birthday));
//			yPregnancy.setLastMenstruation(df1.parse(lastMenstruation));
//			yPregnancy.setPregnancyDate(df1.parse(pregnancyDate));
//			yPregnancyService.save(yPregnancy);
//		}
		SmsResultBean smsResult = userServiceAPI.selectSmsCode(userAccount,"1");
		if(smsResult != null && !MyUtil.isEmpty(smsResult.getSmsCode())){
			if(smsResult.getSmsCode().equals(smsCode)){
				Date time1=smsResult.getUpdateTime();
				if( MyUtil.diffMin(time1)>= 10){
					map.put("resultMsg","验证码已过期，请重新获取");
					map.put("resultCode",404);
					return JSONUtils.toJson(map);
				}

			}else{
				map.put("resultMsg","验证码不正确");
				map.put("resultCode",404);
				return JSONUtils.toJson(map);
			}

		}else{
			map.put("resultMsg","请先获取验证码");
			map.put("resultCode",404);
			return JSONUtils.toJson(map);
		}
		if(MyUtil.isEmpty(smsCode)){
			map.put("resultMsg","请先获取验证码");
			map.put("resultCode",404);
			return JSONUtils.toJson(map);
		}
		if ("".equals(userPassword) || "".equals(userAccount)) {
			map.put("resultMsg", "密码与账号不能为空");
			map.put("resultCode", 404);
			return JSONUtils.toJson(map);
		}
		if (!MyUtil.isMobileNO(userAccount)) {
			map.put("resultMsg", "手机号码格式错误");
			map.put("resultCode", 404);
			return JSONUtils.toJson(map);
		}

		if (userServiceAPI.checkUserAccountExist(userAccount,userRole)) {
			map.put("resultMsg", "该账户已经注册");
			map.put("resultCode", 404);
			return JSONUtils.toJson(map);
		}
		User user=new User();
		user.setUserId(MyUtil.generateId());
		user.setEquipmentId(equipmentId);
		user.setUserAccount(userAccount);
		 user.setUserRole(Integer.parseInt(userRole));
		user.setUserPassword(PasswordHelper.encryptPassword(userPassword));
		if (userServiceAPI.insert(user)) {
			map.put("resultMsg", "注册成功");
			map.put("resultCode", 200);
			return JSONUtils.toJson(map);
		} else {
			map.put("resultMsg", "注册失败");
			map.put("resultCode", 404);
			return JSONUtils.toJson(map);
		}
	}



	/**
	 * 用户密码登录
	 *
	 * @author 王瑶
	 * @return
	 */
	@ApiOperation("用户登录")
	@ResponseBody
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String userLogin(@ApiParam(value = "账号userAccount（必填）  用户角色 userRole1:孕妇 2：机构医生（必填） 密码userPassword（不必填） 验证码smsCode（不必填） 设备I equipmentId（不必填） 登录方式 loginType（必填） 1:密码登录 2：验证码登录  ", required = true)   @RequestBody Map<String,Object> params) throws Exception {
		Map<String, Object> map = Maps.newHashMap();

		String userAccount=(String)params.get("userAccount");
		String userRole=(String)params.get("userRole");

		String loginType=(String)params.get("loginType");
		//当选择登录方式为密码登录的时候
		if(!StringUtils.isEmpty(loginType)){
			if(loginType.equals("1")){
				//密码登录
        	if (!"".equals((String)params.get("userPassword")) && null != (String)params.get("userPassword") && !"".equals(userAccount)
						&& null != userAccount) {
					String userPassword1 = PasswordHelper.encryptPassword((String)params.get("userPassword"));
					if (MyUtil.isMobileNO(userAccount)) {
						User user = userServiceAPI.userLogin(userAccount, userPassword1,userRole);
						if (null != user) {
							//判断userToken是否存在 存在更新 不存在增加一条
							String token = MyUtil.generateToken();
							CacheUserInfo userInfo = new CacheUserInfo();
							userInfo.setUserId(user.getUserId());
							userInfo.setToken(token);
							userInfo.setExpireTime(DateUtils.getDate());
							redisUtils.set(token, userInfo);
							user.setToken(token);
							user.setUserIcon(uploadPath+user.getUserIcon());
							map.put("resultMsg", "登录成功");
							map.put("resultCode", 200);
							map.put("data", user);
							return JSONUtils.toJson(map);
						} else {
							map.put("resultMsg", "手机号或密码错误");
							map.put("resultCode", 404);
							return JSONUtils.toJson(map);
						}
					}  else {
						map.put("resultMsg", "手机号码格式错误");
						map.put("resultCode", 404);
						return JSONUtils.toJson(map);
					}

				} else {
					map.put("resultMsg", "账户名与密码不能为空");
					map.put("resultCode", 404);
					return JSONUtils.toJson(map);
				}
			}else{
				//验证码登录 		String smsCode=(String)params.get("smsCode");
				//		String equipmentId=(String)params.get("equipmentId");
				String smsType="2";
				SmsResultBean smsResult = userServiceAPI.selectSmsCode(userAccount,smsType);
				if(smsResult != null && !MyUtil.isEmpty(smsResult.getSmsCode())){
					if(smsResult.getSmsCode().equals((String)params.get("smsCode"))){
						Date time=smsResult.getUpdateTime();
						if( MyUtil.diffMin(time)>= 10){
							map.put("resultMsg","验证码已过期，请重新获取");
							map.put("resultCode",404);
							return JSONUtils.toJson(map);
						}

					}else{
						map.put("resultMsg","验证码不正确");
						map.put("resultCode",404);
						return JSONUtils.toJson(map);
					}

				}else{
					map.put("resultMsg","请先获取验证码");
					map.put("resultCode",404);
					return JSONUtils.toJson(map);
				}
				if(MyUtil.isEmpty((String)params.get("smsCode"))||MyUtil.isEmpty(userAccount)||MyUtil.isEmpty((String)params.get("equipmentId"))){
					map.put("resultMsg","验证码和手机号和设备Id不能为空");
					map.put("resultCode",404);
					return JSONUtils.toJson(map);
				}
            //根据验证码登录 判断用户是否存在 如果不存在保存一条用户 存在直接登录
				if(userServiceAPI.checkUserAccountExist(userAccount,userRole)){
					User user1=userServiceAPI.queryuserByUserAccount(userAccount,userRole);
					String token = MyUtil.generateToken();
					CacheUserInfo userInfo = new CacheUserInfo();
					userInfo.setUserId(user1.getUserId());
					userInfo.setToken(token);
					userInfo.setExpireTime(DateUtils.getDate());
					redisUtils.set(token, userInfo);
					user1.setToken(token);
					user1.setUserIcon(uploadPath+user1.getUserIcon());
					map.put("resultMsg", "登录成功");
					map.put("resultCode", 200);
					map.put("data", user1);
					return JSONUtils.toJson(map);
				}else{
					User user=new User();
					user.setUserId(MyUtil.generateId());
					user.setEquipmentId((String)params.get("equipmentId"));
					user.setUserAccount(userAccount);
				 	if (userServiceAPI.insert(user)) {
						User user2=userServiceAPI.queryuserByUserAccount(userAccount,userRole);
						String token = MyUtil.generateToken();
						CacheUserInfo userInfo = new CacheUserInfo();
						userInfo.setUserId(user2.getUserId());
						userInfo.setToken(token);
						userInfo.setExpireTime(DateUtils.getDate());
						redisUtils.set(token, userInfo);
						user2.setToken(token);
						user2.setUserIcon(uploadPath+user2.getUserIcon());
                    	map.put("resultMsg", "登录成功");
						map.put("resultCode", 200);
						map.put("data", user2);
						return JSONUtils.toJson(map);
					} else {
						map.put("resultMsg", "登录失败");
						map.put("resultCode", 404);
						return JSONUtils.toJson(map);
					}
				}
			}
		}else{
			map.put("resultMsg", "登录方式不能为空");
			map.put("resultCode", 404);
			return JSONUtils.toJson(map);
		}

	}

 	@ApiOperation("获取短信验证码")
	@ResponseBody
	@RequestMapping(value="/getSmsCode", method = RequestMethod.POST)
	public String getSmsCode(
							  @ApiParam(value = "userAccount 手机号，短信类型 1：注册发验证码 填1 2：登录发送验证码 填2  3：找回密码发送验证码 填3", required = true) @RequestBody(required = false)  Map<String,Object> params
	) throws ClientException {
		String userAccount= (String) params.get("userAccount");
		String smsType= (String) params.get("smsType");
		Map<String, Object> map = Maps.newHashMap();
		String smsCode = MyUtil.buildRandom(6)+"";
		if ("".equals(userAccount) || "".equals(smsType)) {
			map.put("resultMsg","手机号和验证类型不能为空");
			map.put("resultCode",404);
			return JSONUtils.toJson(map);
		}
		if (!MyUtil.isMobileNO(userAccount)) {
			map.put("resultMsg","手机号码格式有误");
			map.put("resultCode",404);
			return JSONUtils.toJson(map);
		}


		SmsResultBean smsResult = userServiceAPI.selectSmsCode(userAccount,smsType);
		SmsResultBean sms = new SmsResultBean();
		sms.setUserAccount(userAccount);
		sms.setSmsType(Integer.parseInt(smsType));
		sms.setSmsCode(smsCode);

		if(smsResult != null && !MyUtil.isEmpty(smsResult.getSmsCode())){
			if(smsResult.getSmsCode().equals(smsCode)){
				Date time=smsResult.getUpdateTime();
				if( MyUtil.diffMin(time)>= 10){
					map.put("resultMsg","短信验证码已发送，10分钟之内有效");
					map.put("resultCode",404);
					return JSONUtils.toJson(map);
				}
			} else{
				SendSmsResponse smsResultBean1 = SmsDemo.sendSms(userAccount,smsCode);
				if("OK".equals(smsResultBean1.getCode())){
					sms.setErrorCode(smsResultBean1.getCode());
					if (userServiceAPI.insertsms(sms)) {
						map.put("resultMsg", "短信验证码发送成功，10分钟之内有效");
						map.put("resultCode", 200);
                    	return JSONUtils.toJson(map);
					} else {
						map.put("resultMsg", "获取失败");
						map.put("resultCode", 404);
						return JSONUtils.toJson(map);
					}
				}
			}


		}else{
			SendSmsResponse smsResultBean = SmsDemo.sendSms(userAccount, smsCode);
			if("OK".equals(smsResultBean.getCode())){
				sms.setErrorCode(smsResultBean.getCode());
				if (userServiceAPI.insertsms(sms)) {
					map.put("resultMsg", "短信验证码发送成功，10分钟之内有效");
					map.put("resultCode", 200);

					return JSONUtils.toJson(map);
				} else {
					map.put("resultMsg", "获取失败");
					map.put("resultCode", 404);
					return JSONUtils.toJson(map);
				}
			}



		}
		return JSONUtils.toJson(map);
	}
	/**
	 *
	 * 忘记密码
	 * @param
	 * @return
	 */
	@ApiOperation("忘记密码")
	@ResponseBody
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(@ApiParam(value = "账号userAccount（必填） 验证码smsCode（必填） 用户角色 userRole1:孕妇 2：机构医生（必填） 密码userPassword（必填）", required = true) @RequestBody(required = true)  Map<String,Object> params) {
		Map<String, Object> map=Maps.newHashMap();
		String userAccount= (String) params.get("userAccount");
		String userRole= (String) params.get("userRole");
		String userPassword= (String) params.get("userPassword");
		String smsCode= (String) params.get("smsCode");
		String userPassword1= PasswordHelper.encryptPassword(userPassword);
		String smsType="3";

		SmsResultBean smsResult = userServiceAPI.selectSmsCode(userAccount,smsType);
		if(smsResult != null && !MyUtil.isEmpty(smsResult.getSmsCode())){
			if(smsResult.getSmsCode().equals(smsCode)){
				Date time=smsResult.getUpdateTime();
				if( MyUtil.diffMin(time)>= 10){
					map.put("resultMsg","验证码已过期，请重新获取");
					map.put("resultCode",404);
					return JSONUtils.toJson(map);
				}

			}else{
				map.put("resultMsg","验证码不正确");
				map.put("resultCode",404);
				return JSONUtils.toJson(map);
			}

		}else{
			map.put("resultMsg","请先获取验证码");
			map.put("resultCode",404);
			return JSONUtils.toJson(map);
		}
		if(MyUtil.isEmpty(smsCode)){
			map.put("resultMsg","请先获取验证码");
			map.put("resultCode",404);
			return JSONUtils.toJson(map);
		}

		if (MyUtil.isEmpty(userPassword)||MyUtil.isEmpty(userAccount)) {
			map.put("resultMsg","账户名与密码不能为空");
			map.put("resultCode",404);
			return JSONUtils.toJson(map);
		}
		if (!MyUtil.isMobileNO(userAccount)) {
			map.put("resultMsg","手机号码格式错误");
			map.put("resultCode",404);
			return JSONUtils.toJson(map);
		}
		if (userServiceAPI.checkUserAccountExist(userAccount,userRole)==false) {
			map.put("resultMsg","该手机号尚未注册");
			map.put("resultCode",404);
			return JSONUtils.toJson(map);
		}
		if (userServiceAPI.resetUserPassword(userPassword1,userAccount,userRole)>0) {
			map.put("resultMsg","修改成功");
			map.put("resultCode",200);
			return JSONUtils.toJson(map);
		}else {
			map.put("resultMsg","修改失败");
			map.put("resultCode",404);
			return JSONUtils.toJson(map);
		}

	}


	/**
	 * 第四个Menu
	 */
	@ApiOperation("基本信息")
	@ResponseBody
	@RequestMapping(value = "/fourthMenu", method = RequestMethod.POST)
	public String fourthMenu(@ApiParam(value = "用户Id userId(必填) 用户角色（userRole 必填） 1:孕妇 2：机构医生", required = true) @RequestBody(required = true) Map<String,Object> params ) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		String userId= (String) params.get("userId");
		String userRole= (String) params.get("userRole");
		if (!"".equals(userId)&& null!= userId&&!"".equals(userRole)&& null!= userRole) {
		 	User user=userServiceAPI.queryuser(userId,userRole);
		 	if(user!=null){
				if(user.getUserIcon() != null && !user.getUserIcon().equals("")){
					String userIcon=uploadPath+user.getUserIcon();
					user.setUserIcon(userIcon);
				}
				map.put("resultMsg", "成功");
				map.put("resultCode", 200);
				map.put("user", user);//用户信息
				return JSONUtils.toJson(map);
			}else{
				map.put("resultMsg", "不存在该用户");
				map.put("resultCode", 404);
            	return JSONUtils.toJson(map);
			}

		}else {
       map.put("resultMsg", "用户Id和用户角色不能为空");
			map.put("resultCode", 404);
        	return JSONUtils.toJson(map);
		}

	}


	/**
	 * 个人信息编辑
	 */
	@ApiOperation("个人信息编辑")
	@ResponseBody
	@RequestMapping(value = "/userInfoEdit", method = RequestMethod.POST)
	public String userInfoEdit(@ApiParam(value = "用户Id  userId(必填)  用户角色 1:孕妇 2：机构医生 userRole(必填)   头像 userIcon（不必填） 昵称 userName（不必填） userAddress（地址 不必填） lastMenstruation（不必填 末次月经） userBirthday（不必填 生日）  ", required = true) @RequestBody  Map<String,Object> params ) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		String userId= (String) params.get("userId");
		String userRole= (String) params.get("userRole");

		if ("".equals(userId)|| null==userId ) {
			map.put("resultMsg", "用户Id不能为空");
			map.put("resultCode", 404);
			return JSONUtils.toJson(map);
		}

     	User user =userServiceAPI.queryuser(userId,userRole);
		YPregnancyEntity pregnancy=yPregnancyService.queryOneByEquipmentId(user.getEquipmentId());
		if (!"".equals( (String) params.get("userName")) && null!= (String) params.get("userName") ) {
			user.setUserName( (String) params.get("userName"));
		}
		if (!"".equals( (String) params.get("userIcon")) && null!= (String) params.get("userIcon")) {
			user.setUserIcon( (String) params.get("userIcon"));
		}
		if (!"".equals((String) params.get("userAddress")) && null!=(String) params.get("userAddress")) {
			user.setUserAddress((String) params.get("userAddress"));
		}
		if (!"".equals((String) params.get("userBirthday")) && null!=(String) params.get("userBirthday")) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			pregnancy.setBirthday(df.parse((String) params.get("userBirthday")));
		}
		if (!"".equals((String) params.get("lastMenstruation")) && null!=(String) params.get("lastMenstruation")) {
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			pregnancy.setLastMenstruation(df1.parse((String) params.get("lastMenstruation")));
		}
 		if (userServiceAPI.updateById(user)==1 && yPregnancyService.update(pregnancy)==1) {
			map.put("resultMsg", "成功");
			map.put("resultCode", 200);
		} else {
			map.put("resultMsg", "失败");
			map.put("resultCode", 404);
		}
		return JSONUtils.toJson(map);

	}
}
