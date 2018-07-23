package com.ater.modules.service;

import com.ater.modules.entity.YBabyStatusEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 16:01:35
 */
public interface YBabyStatusService {
	
	YBabyStatusEntity queryObject(String badyId);
	
	List<YBabyStatusEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YBabyStatusEntity yBabyStatus);
	
	void update(YBabyStatusEntity yBabyStatus);
	
	void delete(String badyId);
	
	void deleteBatch(String[] badyIds);

	YBabyStatusEntity queryOne(int week);
}
