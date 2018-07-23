package com.ater.modules.controller;

import java.util.List;
import java.util.Map;

import com.ater.modules.entity.YBabyStatusEntity;
import com.ater.modules.service.YBabyStatusService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ater.common.utils.PageUtils;
import com.ater.common.utils.Query;
import com.ater.common.utils.R;




/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 16:01:35
 */
@RestController
@RequestMapping("/baby/ybabystatus")
public class YBabyStatusController {
	@Autowired
	private YBabyStatusService yBabyStatusService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("baby:ybabystatus:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YBabyStatusEntity> yBabyStatusList = yBabyStatusService.queryList(query);
		int total = yBabyStatusService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(yBabyStatusList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{badyId}")
	@RequiresPermissions("baby:ybabystatus:info")
	public R info(@PathVariable("badyId") String badyId){
		YBabyStatusEntity yBabyStatus = yBabyStatusService.queryObject(badyId);
		
		return R.ok().put("yBabyStatus", yBabyStatus);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("baby:ybabystatus:save")
	public R save(@RequestBody YBabyStatusEntity yBabyStatus){
		yBabyStatusService.save(yBabyStatus);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("baby:ybabystatus:update")
	public R update(@RequestBody YBabyStatusEntity yBabyStatus){
		yBabyStatusService.update(yBabyStatus);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("baby:ybabystatus:delete")
	public R delete(@RequestBody String[] badyIds){
		yBabyStatusService.deleteBatch(badyIds);
		
		return R.ok();
	}
	
}
