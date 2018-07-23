package com.ater.modules.service.impl;


import com.ater.modules.dao.TaskDao;
import com.ater.modules.entity.TaskEntity;
import com.ater.modules.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("taskService")
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public TaskEntity queryObject(String id){
		return taskDao.queryObject(id);
	}
	
	@Override
	public List<TaskEntity> queryList(Map<String, Object> map){
		return taskDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return taskDao.queryTotal(map);
	}
	
	@Override
	public void save(TaskEntity task){
		taskDao.save(task);
	}
	
	@Override
	public void update(TaskEntity task){
		taskDao.update(task);
	}
	
	@Override
	public void delete(Long id){
		taskDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		taskDao.deleteBatch(ids);
	}

	public 	List<TaskEntity> queryOneByMId(String mqid){
		return taskDao.queryOneByMId(mqid);
	}

	public TaskEntity queryOneByMIdAndResult(String mqid,String result){
		return taskDao.queryOneByMIdAndResult(mqid,result);
	}

}
