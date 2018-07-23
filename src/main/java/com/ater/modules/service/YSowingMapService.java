package com.ater.modules.service;


import com.ater.modules.entity.YSowingMapEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:07:15
 */
public interface YSowingMapService {
	
	YSowingMapEntity queryObject(String sowId);
	
	List<YSowingMapEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YSowingMapEntity ySowingMap);
	
	void update(YSowingMapEntity ySowingMap);
	
	void delete(String sowId);
	
	void deleteBatch(String[] sowIds);

	 List<YSowingMapEntity> queryAllList();
}
