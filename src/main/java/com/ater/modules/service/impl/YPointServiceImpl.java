package com.ater.modules.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ater.modules.dao.YPointDao;
import com.ater.modules.entity.YPointEntity;
import com.ater.modules.service.YPointService;



@Service("yPointService")
public class YPointServiceImpl implements YPointService {
	@Autowired
	private YPointDao yPointDao;
	
	@Override
	public YPointEntity queryObject(String pointId){
		return yPointDao.queryObject(pointId);
	}
	
	@Override
	public List<YPointEntity> queryList(Map<String, Object> map){
		return yPointDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return yPointDao.queryTotal(map);
	}
	
	@Override
	public void save(YPointEntity yPoint){
		yPointDao.save(yPoint);
	}
	
	@Override
	public void update(YPointEntity yPoint){
		yPointDao.update(yPoint);
	}
	
	@Override
	public void delete(String pointId){
		yPointDao.delete(pointId);
	}
	
	@Override
	public void deleteBatch(String[] pointIds){
		yPointDao.deleteBatch(pointIds);
	}

	public YPointEntity selectPoint( String userId,String pointType){
		return yPointDao.selectPoint(userId,pointType);
	}
	public int updatePoint(Map<String, String> map){
     	return  yPointDao.updatePoint(map);
	}
	public int insertPoint(Map<String, String> map){
		return yPointDao.insertPoint(map);
	}
}
