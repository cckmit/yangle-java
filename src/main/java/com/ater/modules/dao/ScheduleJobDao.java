package com.ater.modules.dao;


import com.ater.modules.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 定时任务
 * 
 * @author
 * @email
 * @date
 */
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map <String, Object> map);

	/**
	 * 根据MessageID挺尸job
	 */
	void stopJobByMID(String params);
}
