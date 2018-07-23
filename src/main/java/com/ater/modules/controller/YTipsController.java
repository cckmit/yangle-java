package com.ater.modules.controller;

import java.util.List;
import java.util.Map;

import com.ater.modules.entity.YTipsEntity;
import com.ater.modules.service.YTipsService;
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
 * @date 2018-06-01 15:39:00
 */
@RestController
@RequestMapping("/tips/ytips")
public class YTipsController {
	@Autowired
	private YTipsService yTipsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tips:ytips:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YTipsEntity> yTipsList = yTipsService.queryList(query);
		int total = yTipsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(yTipsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{tipId}")
	@RequiresPermissions("tips:ytips:info")
	public R info(@PathVariable("tipId") String tipId){
		YTipsEntity yTips = yTipsService.queryObject(tipId);
		
		return R.ok().put("yTips", yTips);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tips:ytips:save")
	public R save(@RequestBody YTipsEntity yTips){
		yTipsService.save(yTips);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tips:ytips:update")
	public R update(@RequestBody YTipsEntity yTips){
		yTipsService.update(yTips);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tips:ytips:delete")
	public R delete(@RequestBody String[] tipIds){
		yTipsService.deleteBatch(tipIds);
		
		return R.ok();
	}
	
}
