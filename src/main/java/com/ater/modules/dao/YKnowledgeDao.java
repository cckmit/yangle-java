package com.ater.modules.dao;

import com.ater.modules.entity.YKnowledgeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:00:41
 */
@Mapper
public interface YKnowledgeDao extends BaseDao<YKnowledgeEntity> {

      List<YKnowledgeEntity> selectThreeKnowledge();
	
}
