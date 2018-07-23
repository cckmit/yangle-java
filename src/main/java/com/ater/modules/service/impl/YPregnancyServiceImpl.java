package com.ater.modules.service.impl;

import com.ater.modules.dao.YPregnancyDao;
import com.ater.modules.entity.YPregnancyEntity;
import com.ater.modules.service.YPregnancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service("yPregnancyService")
public class YPregnancyServiceImpl implements YPregnancyService {
	@Autowired
	private YPregnancyDao yPregnancyDao;
	
	@Override
	public YPregnancyEntity queryObject(String perId){
		return yPregnancyDao.queryObject(perId);
	}
	
	@Override
	public List<YPregnancyEntity> queryList(Map<String, Object> map){
		return yPregnancyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return yPregnancyDao.queryTotal(map);
	}
	
	@Override
	public void save(YPregnancyEntity yPregnancy){
		yPregnancyDao.save(yPregnancy);
	}
	
	@Override
	public int update(YPregnancyEntity yPregnancy){
		return yPregnancyDao.updatePregnancy(yPregnancy);
	}
	
	@Override
	public void delete(String perId){
		yPregnancyDao.delete(perId);
	}
	
	@Override
	public void deleteBatch(String[] perIds){
		yPregnancyDao.deleteBatch(perIds);
	}

	public YPregnancyEntity queryOneByEquipmentId(String equipmentId){
		return yPregnancyDao.queryOneByEquipmentId(equipmentId);
	}
	
}
