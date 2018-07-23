package com.ater.modules.service;

import com.ater.common.utils.Query;
import com.ater.modules.entity.YFetalHeartEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-04 15:42:00
 */
public interface YFetalHeartService {
	
	YFetalHeartEntity queryObject(String heartId);
	
	List<YFetalHeartEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YFetalHeartEntity yFetalHeart);
	
	void update(YFetalHeartEntity yFetalHeart);
	
	void delete(String heartId);
	
	void deleteBatch(String[] heartIds);

	int insertByBatch(List<YFetalHeartEntity> fetalHeartList);

}
