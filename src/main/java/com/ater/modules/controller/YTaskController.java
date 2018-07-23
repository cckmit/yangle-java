package com.ater.modules.controller;

import java.util.List;
import java.util.Map;

import com.ater.modules.entity.YTaskEntity;
import com.ater.modules.service.YTaskService;
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
 * @date 2018-06-01 15:14:21
 */
@RestController
@RequestMapping("/task/ytask")
public class YTaskController {
	@Autowired
	private YTaskService yTaskService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("task:ytask:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YTaskEntity> yTaskList = yTaskService.queryList(query);
		int total = yTaskService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(yTaskList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{taskId}")
	@RequiresPermissions("task:ytask:info")
	public R info(@PathVariable("taskId") String taskId){
		YTaskEntity yTask = yTaskService.queryObject(taskId);
		
		return R.ok().put("yTask", yTask);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("task:ytask:save")
	public R save(@RequestBody YTaskEntity yTask){
		yTaskService.save(yTask);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("task:ytask:update")
	public R update(@RequestBody YTaskEntity yTask){
		yTaskService.update(yTask);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("task:ytask:delete")
	public R delete(@RequestBody String[] taskIds){
		yTaskService.deleteBatch(taskIds);
		
		return R.ok();
	}
	
}
