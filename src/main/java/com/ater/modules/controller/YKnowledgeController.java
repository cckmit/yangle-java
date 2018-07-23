package com.ater.modules.controller;

import com.ater.common.utils.PageUtils;
import com.ater.common.utils.Query;
import com.ater.common.utils.R;

import com.ater.modules.entity.YKnowledgeEntity;
import com.ater.modules.service.YKnowledgeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:00:41
 */
@RestController
@RequestMapping("/knowledge/yknowledge")
public class YKnowledgeController {
	@Autowired
	private YKnowledgeService yKnowledgeService;
	
	/**
	 * 列表  首页显示多知识
	 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
/*	@RequiresPermissions("knowledge:yknowledge:list")*/
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YKnowledgeEntity> yKnowledgeList = yKnowledgeService.queryList(query);
		int total = yKnowledgeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(yKnowledgeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{konwId}")
	@RequiresPermissions("knowledge:yknowledge:info")
	public R info(@PathVariable("konwId") String konwId){
		YKnowledgeEntity yKnowledge = yKnowledgeService.queryObject(konwId);
		
		return R.ok().put("yKnowledge", yKnowledge);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("knowledge:yknowledge:save")
	public R save(@RequestBody YKnowledgeEntity yKnowledge){
		yKnowledgeService.save(yKnowledge);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("knowledge:yknowledge:update")
	public R update(@RequestBody YKnowledgeEntity yKnowledge){
		yKnowledgeService.update(yKnowledge);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("knowledge:yknowledge:delete")
	public R delete(@RequestBody String[] konwIds){
		yKnowledgeService.deleteBatch(konwIds);
		
		return R.ok();
	}
	
}
