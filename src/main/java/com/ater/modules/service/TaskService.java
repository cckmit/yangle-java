package com.ater.modules.service;



import com.ater.modules.entity.TaskEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author
 * @email
 * @date
 */
public interface TaskService {
	
	TaskEntity queryObject(String id);
	
	List<TaskEntity> queryList(Map <String, Object> map);

	int queryTotal(Map <String, Object> map);
	
	void save(TaskEntity task);
	
	void update(TaskEntity task);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<TaskEntity> queryOneByMId(String mqid);

	TaskEntity queryOneByMIdAndResult(String mqid,String result);
}
