package com.ater.modules.controller;

import java.util.List;
import java.util.Map;

import com.ater.modules.entity.YSowingMapEntity;
import com.ater.modules.service.YSowingMapService;
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
 * @date 2018-06-01 15:07:15
 */
@RestController
@RequestMapping("/sowing/ysowingmap")
public class YSowingMapController {
	@Autowired
	private YSowingMapService ySowingMapService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sowing:ysowingmap:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YSowingMapEntity> ySowingMapList = ySowingMapService.queryList(query);
		int total = ySowingMapService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ySowingMapList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{sowId}")
	@RequiresPermissions("sowing:ysowingmap:info")
	public R info(@PathVariable("sowId") String sowId){
		YSowingMapEntity ySowingMap = ySowingMapService.queryObject(sowId);
		
		return R.ok().put("ySowingMap", ySowingMap);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sowing:ysowingmap:save")
	public R save(@RequestBody YSowingMapEntity ySowingMap){
		ySowingMapService.save(ySowingMap);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sowing:ysowingmap:update")
	public R update(@RequestBody YSowingMapEntity ySowingMap){
		ySowingMapService.update(ySowingMap);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sowing:ysowingmap:delete")
	public R delete(@RequestBody String[] sowIds){
		ySowingMapService.deleteBatch(sowIds);
		
		return R.ok();
	}
	
}
