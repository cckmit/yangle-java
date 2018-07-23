package com.ater.modules.service.impl;

import com.ater.modules.dao.YTipsDao;
import com.ater.modules.entity.YTipsEntity;
import com.ater.modules.service.YTipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("yTipsService")
public class YTipsServiceImpl implements YTipsService {
	@Autowired
	private YTipsDao yTipsDao;
	
	@Override
	public YTipsEntity queryObject(String tipId){
		return yTipsDao.queryObject(tipId);
	}
	
	@Override
	public List<YTipsEntity> queryList(Map<String, Object> map){
		return yTipsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return yTipsDao.queryTotal(map);
	}
	
	@Override
	public void save(YTipsEntity yTips){
		yTipsDao.save(yTips);
	}
	
	@Override
	public void update(YTipsEntity yTips){
		yTipsDao.update(yTips);
	}
	
	@Override
	public void delete(String tipId){
		yTipsDao.delete(tipId);
	}
	
	@Override
	public void deleteBatch(String[] tipIds){
		yTipsDao.deleteBatch(tipIds);
	}

	public YTipsEntity queryOne(){
		return  yTipsDao.queryOne();
	}
	
}
