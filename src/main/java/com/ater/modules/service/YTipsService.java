package com.ater.modules.service;



import com.ater.modules.entity.YTipsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:39:00
 */
public interface YTipsService {
	
	YTipsEntity queryObject(String tipId);
	
	List<YTipsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YTipsEntity yTips);
	
	void update(YTipsEntity yTips);
	
	void delete(String tipId);
	
	void deleteBatch(String[] tipIds);

	  YTipsEntity queryOne();

}
