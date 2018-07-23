package com.ater.modules.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ater.modules.entity.YPointEntity;
import com.ater.modules.service.YPointService;
import com.ater.common.utils.PageUtils;
import com.ater.common.utils.Query;
import com.ater.common.utils.R;




/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-07 18:00:25
 */
@RestController
@RequestMapping("/yPoint/ypoint")
public class YPointController {
	@Autowired
	private YPointService yPointService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("yPoint:ypoint:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YPointEntity> yPointList = yPointService.queryList(query);
		int total = yPointService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(yPointList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{pointId}")
	@RequiresPermissions("yPoint:ypoint:info")
	public R info(@PathVariable("pointId") String pointId){
		YPointEntity yPoint = yPointService.queryObject(pointId);
		
		return R.ok().put("yPoint", yPoint);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("yPoint:ypoint:save")
	public R save(@RequestBody YPointEntity yPoint){
		yPointService.save(yPoint);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("yPoint:ypoint:update")
	public R update(@RequestBody YPointEntity yPoint){
		yPointService.update(yPoint);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("yPoint:ypoint:delete")
	public R delete(@RequestBody String[] pointIds){
		yPointService.deleteBatch(pointIds);
		
		return R.ok();
	}
	
}
