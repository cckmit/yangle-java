package com.ater.modules.service;

import com.ater.modules.entity.YPointEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-07 18:00:25
 */
public interface YPointService {
	
	YPointEntity queryObject(String pointId);
	
	List<YPointEntity> queryList(Map <String, Object> map);
	
	int queryTotal(Map <String, Object> map);
	
	void save(YPointEntity yPoint);
	
	void update(YPointEntity yPoint);
	
	void delete(String pointId);
	
	void deleteBatch(String[] pointIds);

	YPointEntity selectPoint( String userId,String pointType);

	int updatePoint(Map<String, String> map);

	int insertPoint(Map<String, String> map);
}
