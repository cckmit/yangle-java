package com.ater.modules.controller;

import com.ater.common.annotation.SysLog;
import com.ater.common.entity.CacheUserInfo;
import com.ater.common.utils.*;
import com.ater.common.validator.Assert;
import com.ater.modules.entity.User;
import com.ater.modules.oauth2.TokenGenerator;

import com.ater.modules.service.IUserService;
import com.ater.modules.service.YPregnancyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ater.common.utils.ShiroUtils.getUserId;

/**
 *
 *
 * @author ater
 * @email ${email}
 * @date 2018-04-16 18:03:30
 */
@Api(value = "后台系统", description = "后台系统")
@RestController
@RequestMapping("/userweb")
public class UserWebController   {
	@Autowired
	private RedisUtils redisUtils;
 	@Autowired
	private IUserService userService;


    @Autowired
    private RedisUtils redisUtils1;




	@ApiOperation(value = "后台登录接口", response = User.class)
	@RequestMapping(value = "/weblogin", method = RequestMethod.POST)
	public R weblogin(@ApiParam(value = "用户名", required = true) @RequestParam String userName,
                      @ApiParam(value = "密码", required = true) @RequestParam String userPassword) {

		if(StringUtils.isEmpty(userName) || userName.trim().length() <= 0) {
			return R.error("请填写正确的用户名");
		}
		if(StringUtils.isEmpty(userPassword) || userPassword.trim().length() <= 0) {
			return R.error("请输入正确的密码");
		}
		//用户信息
		User user = userService.userWebLogin(userName, PasswordHelper.encryptPassword(userPassword),"2");
		//账号不存在、密码错误
		if (user == null || !user.getUserPassword().equals(PasswordHelper.encryptPassword(userPassword))) {
			return R.error("用户名或密码不正确");
		}

		//生成token，并保存到redis中
		String userId=user.getUserId()+"";
		String token = TokenGenerator.generateValue();

		CacheUserInfo userInfo = new CacheUserInfo();
		userInfo.setUserId(userId);
		userInfo.setToken(token);
		userInfo.setExpireTime(DateUtils.getDate());
		redisUtils1.set(token, userInfo);

		return R.ok("登录成功").put("userObj", user).put("token",token);
	}

}
