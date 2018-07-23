package com.ater.modules.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.ater.modules.dao.YFetalHeartDao;
import com.ater.modules.entity.YFetalHeartEntity;
import com.ater.modules.service.YFetalHeartService;



@Service("yFetalHeartService")
public class YFetalHeartServiceImpl implements YFetalHeartService {
	@Autowired
	private YFetalHeartDao yFetalHeartDao;
	
	@Override
	public YFetalHeartEntity queryObject(String heartId){
		return yFetalHeartDao.queryObject(heartId);
	}
	
	@Override
	public List<YFetalHeartEntity> queryList(Map<String, Object> map){
		return yFetalHeartDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return yFetalHeartDao.queryTotal(map);
	}
	
	@Override
	public void save(YFetalHeartEntity yFetalHeart){
		yFetalHeartDao.save(yFetalHeart);
	}
	
	@Override
	public void update(YFetalHeartEntity yFetalHeart){
		yFetalHeartDao.update(yFetalHeart);
	}
	
	@Override
	public void delete(String heartId){
		yFetalHeartDao.delete(heartId);
	}
	
	@Override
	public void deleteBatch(String[] heartIds){
		yFetalHeartDao.deleteBatch(heartIds);
	}

	public int insertByBatch(List<YFetalHeartEntity> fetalHeartList){
		return yFetalHeartDao.insertByBatch(fetalHeartList);
	}

}
