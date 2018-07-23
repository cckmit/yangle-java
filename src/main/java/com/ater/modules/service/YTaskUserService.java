package com.ater.modules.service;

import com.ater.modules.entity.YTaskUserEntity;
import com.ater.modules.entity.YTaskUserVo;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author ater
 * @email ${email}
 * @date 2018-06-08 16:16:00
 */
public interface YTaskUserService {

	YTaskUserEntity queryObject(String taskUserId);

	List<YTaskUserEntity> queryList(Map <String, Object> map);

	int queryTotal(Map <String, Object> map);

	void save(YTaskUserEntity yTaskUser);

	void update(YTaskUserEntity yTaskUser);

	void updateStatus(YTaskUserVo yTask);

	void delete(String taskUserId);

	void deleteBatch(String[] taskUserIds);

	List<YTaskUserVo> queryListByUserId(Map<String, Object> map);

	YTaskUserVo queryOne(String taskUserId );

	YTaskUserEntity selectOneByUserId(String userId);

	YTaskUserEntity queryTodayPriority2(Map <String, Object> map);

	YTaskUserEntity queryTodayPriority3(Map <String, Object> map);
}
