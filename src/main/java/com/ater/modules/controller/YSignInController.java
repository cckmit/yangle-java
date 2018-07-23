package com.ater.modules.controller;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ater.modules.entity.YSignInEntity;
import com.ater.modules.service.YSignInService;
import com.ater.common.utils.PageUtils;
import com.ater.common.utils.Query;
import com.ater.common.utils.R;




/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-07 17:24:47
 */
@RestController
@RequestMapping("/ySignIn/ysignin")
public class YSignInController {
	@Autowired
	private YSignInService ySignInService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ySignIn:ysignin:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YSignInEntity> ySignInList = ySignInService.queryList(query);
		int total = ySignInService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ySignInList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ySignIn:ysignin:info")
	public R info(@PathVariable("id") String id){
		YSignInEntity ySignIn = ySignInService.queryObject(id);
		
		return R.ok().put("ySignIn", ySignIn);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ySignIn:ysignin:save")
	public R save(@RequestBody YSignInEntity ySignIn){
		ySignInService.save(ySignIn);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ySignIn:ysignin:update")
	public R update(@RequestBody YSignInEntity ySignIn){
		ySignInService.update(ySignIn);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ySignIn:ysignin:delete")
	public R delete(@RequestBody String[] ids){
		ySignInService.deleteBatch(ids);
		
		return R.ok();
	}

}
