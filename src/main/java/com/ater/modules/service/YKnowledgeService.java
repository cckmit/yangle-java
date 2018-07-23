package com.ater.modules.service;



import com.ater.modules.entity.YKnowledgeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:00:41
 */
public interface YKnowledgeService {
	
	YKnowledgeEntity queryObject(String konwId);
	
	List<YKnowledgeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YKnowledgeEntity yKnowledge);
	
	void update(YKnowledgeEntity yKnowledge);
	
	void delete(String konwId);
	
	void deleteBatch(String[] konwIds);

	 List<YKnowledgeEntity> selectThreeKnowledge();
}
