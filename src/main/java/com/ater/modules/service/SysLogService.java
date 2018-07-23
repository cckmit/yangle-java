package com.ater.modules.service;

import com.ater.modules.entity.SysLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 */
public interface SysLogService {
	
	SysLogEntity queryObject(Long id);
	
	List<SysLogEntity> queryList(Map <String, Object> map);

	int queryTotal(Map <String, Object> map);
	
	void save(SysLogEntity sysLog);
	
	void update(SysLogEntity sysLog);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
