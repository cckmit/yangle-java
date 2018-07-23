package com.ater.modules.service.impl;

import com.ater.modules.entity.YTaskUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ater.modules.dao.YTaskUserDao;
import com.ater.modules.entity.YTaskUserEntity;
import com.ater.modules.service.YTaskUserService;



@Service("yTaskUserService")
public class YTaskUserServiceImpl implements YTaskUserService {
	@Autowired
	private YTaskUserDao yTaskUserDao;

	@Override
	public YTaskUserEntity queryObject(String taskUserId){
		return yTaskUserDao.queryObject(taskUserId);
	}

	@Override
	public List<YTaskUserEntity> queryList(Map<String, Object> map){
		return yTaskUserDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return yTaskUserDao.queryTotal(map);
	}

	@Override
	public void save(YTaskUserEntity yTaskUser){
		yTaskUserDao.save(yTaskUser);
	}

	@Override
	public void update(YTaskUserEntity yTaskUser){
		yTaskUserDao.update(yTaskUser);
	}

	@Override
	public void updateStatus(YTaskUserVo yTask) {
		yTaskUserDao.updateStatus(yTask);
	}

	@Override
	public void delete(String taskUserId){
		yTaskUserDao.delete(taskUserId);
	}

	@Override
	public void deleteBatch(String[] taskUserIds){
		yTaskUserDao.deleteBatch(taskUserIds);
	}

	public List<YTaskUserVo> queryListByUserId(Map<String, Object> map){
		return yTaskUserDao.queryListByUserId(map);
	}
	public YTaskUserVo queryOne(String taskUserId ){
		return yTaskUserDao.queryOne(taskUserId);
	}


	public YTaskUserEntity selectOneByUserId(String userId){
		return yTaskUserDao.selectOneByUserId(userId);
	}

	@Override
	public YTaskUserEntity queryTodayPriority2(Map<String, Object> map) {
		return yTaskUserDao.queryTodayPriority2(map);
	}

	@Override
	public YTaskUserEntity queryTodayPriority3(Map<String, Object> map) {
		return yTaskUserDao.queryTodayPriority3(map);
	}
}
