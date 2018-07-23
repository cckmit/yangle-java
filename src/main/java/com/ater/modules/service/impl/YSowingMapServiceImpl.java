package com.ater.modules.service.impl;

import com.ater.modules.dao.YSowingMapDao;
import com.ater.modules.entity.YSowingMapEntity;
import com.ater.modules.service.YSowingMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("ySowingMapService")
public class YSowingMapServiceImpl implements YSowingMapService {
	@Autowired
	private YSowingMapDao ySowingMapDao;
	
	@Override
	public YSowingMapEntity queryObject(String sowId){
		return ySowingMapDao.queryObject(sowId);
	}
	
	@Override
	public List<YSowingMapEntity> queryList(Map<String, Object> map){
		return ySowingMapDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ySowingMapDao.queryTotal(map);
	}
	
	@Override
	public void save(YSowingMapEntity ySowingMap){
		ySowingMapDao.save(ySowingMap);
	}
	
	@Override
	public void update(YSowingMapEntity ySowingMap){
		ySowingMapDao.update(ySowingMap);
	}
	
	@Override
	public void delete(String sowId){
		ySowingMapDao.delete(sowId);
	}
	
	@Override
	public void deleteBatch(String[] sowIds){
		ySowingMapDao.deleteBatch(sowIds);
	}

	public List<YSowingMapEntity> queryAllList(){
		return ySowingMapDao.queryAllList();
	}
	
}
