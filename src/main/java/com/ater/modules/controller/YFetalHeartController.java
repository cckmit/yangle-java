package com.ater.modules.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ater.common.utils.*;
import com.ater.modules.entity.User;
import com.ater.modules.entity.YFetalMovementEntity;
import com.ater.modules.service.IUserService;
import com.ater.modules.service.YFetalMovementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ater.modules.entity.YFetalHeartEntity;
import com.ater.modules.service.YFetalHeartService;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-04 15:42:00
 */

@RestController
@RequestMapping("/fetalHeart/yfetalheart")
public class YFetalHeartController {
	@Autowired
	private YFetalHeartService yFetalHeartService;
	@Autowired
	private IUserService userServiceAPI;
	@Autowired
	private YFetalMovementService yFetalMovementService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("fetalHeart:yfetalheart:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YFetalHeartEntity> yFetalHeartList = yFetalHeartService.queryList(query);
		int total = yFetalHeartService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(yFetalHeartList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{heartId}")
	@RequiresPermissions("fetalHeart:yfetalheart:info")
	public R info(@PathVariable("heartId") String heartId){
		YFetalHeartEntity yFetalHeart = yFetalHeartService.queryObject(heartId);
		
		return R.ok().put("yFetalHeart", yFetalHeart);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("fetalHeart:yfetalheart:save")
	public R save(@RequestBody YFetalHeartEntity yFetalHeart){
		yFetalHeartService.save(yFetalHeart);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("fetalHeart:yfetalheart:update")
	public R update(@RequestBody YFetalHeartEntity yFetalHeart){
		yFetalHeartService.update(yFetalHeart);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("fetalHeart:yfetalheart:delete")
	public R delete(@RequestBody String[] heartIds){
		yFetalHeartService.deleteBatch(heartIds);
	 	return R.ok();
	}


}
