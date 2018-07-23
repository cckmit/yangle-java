package com.ater.modules.service;



import com.ater.modules.entity.YPregnancyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-05-31 19:02:50
 */
public interface YPregnancyService {
	
	YPregnancyEntity queryObject(String perId);
	
	List<YPregnancyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YPregnancyEntity yPregnancy);
	
	int update(YPregnancyEntity yPregnancy);
	
	void delete(String perId);
	
	void deleteBatch(String[] perIds);

	YPregnancyEntity queryOneByEquipmentId(String equipmentId);
}
