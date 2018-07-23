package com.ater.modules.service.impl;

import com.ater.modules.dao.YBabyStatusDao;
import com.ater.modules.entity.YBabyStatusEntity;
import com.ater.modules.service.YBabyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service("yBabyStatusService")
public class YBabyStatusServiceImpl implements YBabyStatusService {
	@Autowired
	private YBabyStatusDao yBabyStatusDao;
	
	@Override
	public YBabyStatusEntity queryObject(String badyId){
		return yBabyStatusDao.queryObject(badyId);
	}
	
	@Override
	public List<YBabyStatusEntity> queryList(Map<String, Object> map){
		return yBabyStatusDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return yBabyStatusDao.queryTotal(map);
	}
	
	@Override
	public void save(YBabyStatusEntity yBabyStatus){
		yBabyStatusDao.save(yBabyStatus);
	}
	
	@Override
	public void update(YBabyStatusEntity yBabyStatus){
		yBabyStatusDao.update(yBabyStatus);
	}
	
	@Override
	public void delete(String badyId){
		yBabyStatusDao.delete(badyId);
	}
	
	@Override
	public void deleteBatch(String[] badyIds){
		yBabyStatusDao.deleteBatch(badyIds);
	}



    public  YBabyStatusEntity queryOne(int week){
		return yBabyStatusDao.queryOne(week);
	}
	
}
