package com.ater.modules.service.impl;


import com.ater.modules.dao.YKnowledgeDao;
import com.ater.modules.entity.YKnowledgeEntity;
import com.ater.modules.service.YKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("yKnowledgeService")
public class YKnowledgeServiceImpl implements YKnowledgeService {
	@Autowired
	private YKnowledgeDao yKnowledgeDao;
	
	@Override
	public YKnowledgeEntity queryObject(String konwId){
		return yKnowledgeDao.queryObject(konwId);
	}
	
	@Override
	public List<YKnowledgeEntity> queryList(Map<String, Object> map){
		return yKnowledgeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return yKnowledgeDao.queryTotal(map);
	}
	
	@Override
	public void save(YKnowledgeEntity yKnowledge){
		yKnowledgeDao.save(yKnowledge);
	}
	
	@Override
	public void update(YKnowledgeEntity yKnowledge){
		yKnowledgeDao.update(yKnowledge);
	}
	
	@Override
	public void delete(String konwId){
		yKnowledgeDao.delete(konwId);
	}
	
	@Override
	public void deleteBatch(String[] konwIds){
		yKnowledgeDao.deleteBatch(konwIds);
	}

	public List<YKnowledgeEntity> selectThreeKnowledge(){
		return yKnowledgeDao.selectThreeKnowledge();
	}
	
}
