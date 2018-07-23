package com.ater.modules.service.impl;

import com.ater.common.utils.Query;
import com.ater.modules.entity.YTbTaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ater.modules.dao.YFetalMovementDao;
import com.ater.modules.entity.YFetalMovementEntity;
import com.ater.modules.service.YFetalMovementService;



@Service("yFetalMovementService")
public class YFetalMovementServiceImpl implements YFetalMovementService {
	@Autowired
	private YFetalMovementDao yFetalMovementDao;
	
	@Override
	public YFetalMovementEntity queryObject(String moveId){
		return yFetalMovementDao.queryObject(moveId);
	}
	
	@Override
	public List<YFetalMovementEntity> queryList(Map<String, Object> map){
		return yFetalMovementDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return yFetalMovementDao.queryTotal(map);
	}
	
	@Override
	public void save(YFetalMovementEntity yFetalMovement){
		yFetalMovementDao.save(yFetalMovement);
	}
	
	@Override
	public void update(YFetalMovementEntity yFetalMovement){
		yFetalMovementDao.update(yFetalMovement);
	}
	
	@Override
	public void delete(String moveId){
		yFetalMovementDao.delete(moveId);
	}
	
	@Override
	public void deleteBatch(String[] moveIds){
		yFetalMovementDao.deleteBatch(moveIds);
	}

	public int queryTodayTotal(Query query){
		return yFetalMovementDao.queryTodayTotal(query);
	}

	public List<YFetalMovementEntity> queryWebList(Query query){
		return yFetalMovementDao.queryWebList(query);
	}

	public int queryWebTotal(Query query){
		return yFetalMovementDao.queryWebTotal(query);
	}

	public int querySubmitTotal(Query query){
		return yFetalMovementDao.querySubmitTotal(query);
	}

	@Override
	public int checkTodayByUserId(String userId) {
		return yFetalMovementDao.checkTodayByUserId(userId);
	}

	@Override
	public int checkTodayPdByUserId(String userId) {
		return yFetalMovementDao.checkTodayPdByUserId(userId);
	}

	public YFetalMovementEntity  queryPrint(String moveId){
		return yFetalMovementDao.queryPrint(moveId);
	}
	public	List<YTbTaskEntity> queryYTbTaskList(Map<String, Object> map){
		return yFetalMovementDao.queryYTbTaskList(map);
	}
	public int  queryTbTaskTotal(Map<String, Object> map){
		return yFetalMovementDao.queryTbTaskTotal(map);
	}
}
