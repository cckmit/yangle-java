package com.ater.modules.service.impl;

import com.ater.modules.dao.YTaskDao;
import com.ater.modules.entity.YTaskEntity;
import com.ater.modules.entity.YTaskUserVo;
import com.ater.modules.service.YTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service("yTaskService")
public class YTaskServiceImpl implements YTaskService {
	@Autowired
	private YTaskDao yTaskDao;

	@Override
	public YTaskEntity queryObject(String taskId){
		return yTaskDao.queryObject(taskId);
	}

	@Override
	public List<YTaskEntity> queryList(Map<String, Object> map){
		return yTaskDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return yTaskDao.queryTotal(map);
	}

	@Override
	public void save(YTaskEntity yTask){
		yTaskDao.save(yTask);
	}

	@Override
	public void update(YTaskEntity yTask){
		yTaskDao.update(yTask);
	}

	@Override
	public void delete(String taskId){
		yTaskDao.delete(taskId);
	}

	@Override
	public void deleteBatch(String[] taskIds){
		yTaskDao.deleteBatch(taskIds);
	}

	@Override
	public YTaskEntity selectOneTask() {
		return yTaskDao.selectOneTask();
	}

	public List<YTaskUserVo> selectTask(){
		return yTaskDao.selectTask();
	}

	@Override
	public Integer selectTaskNum() {
		return yTaskDao.selectTaskNum();
	}

	public List<YTaskUserVo> selectTaskByUserId(String userId){
		return yTaskDao.selectTaskByUserId(userId);
	}

	@Override
	public YTaskEntity selectOneTaskByUserId(String userId) {
		return yTaskDao.selectOneTaskByUserId(userId);
	}

	@Override
	public Integer selectTaskNumByUserId(String userId) {
		return yTaskDao.selectTaskNumByUserId(userId);
	}

	public int selectPointByTaskId(String taskUserId){
		return yTaskDao.selectPointByTaskId(taskUserId);
	}
}
