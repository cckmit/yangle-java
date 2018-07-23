package com.ater.modules.controller;

import com.ater.common.annotation.SysLog;
import com.ater.common.utils.MyUtils;
import com.ater.common.utils.PageUtils;
import com.ater.common.utils.Query;
import com.ater.common.utils.R;
import com.ater.modules.entity.SysLogEntity;
import com.ater.modules.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 日志
 */
@Api(value = "系统日志",description = "系统日志")
@RestController
@RequestMapping("/system/syslog")
public class SysLogController {

	@Autowired
	private SysLogService sysLogService;

	private static final Logger logger = Logger.getLogger(SysLogController.class);

	/**
	 * 列表
	 */
	@ApiOperation("系统日志列表")
	@SysLog("获取系统日志列表")
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public R list(@RequestParam Map<String, Object> params) {
		//分割日期
		MyUtils.splitDateRange(params, "operationTime");
		//查询列表数据
		Query query = new Query(params);
		List<SysLogEntity> sysLogList = sysLogService.queryList(query);
		int total = sysLogService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(sysLogList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
}
