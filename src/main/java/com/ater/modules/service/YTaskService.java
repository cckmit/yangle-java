package com.ater.modules.service;


import com.ater.modules.entity.YTaskEntity;
import com.ater.modules.entity.YTaskUserVo;

import java.util.List;
import java.util.Map;

/**
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:14:21
 */
public interface YTaskService {

    YTaskEntity queryObject(String taskId);

    List<YTaskEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(YTaskEntity yTask);

    void update(YTaskEntity yTask);

    void delete(String taskId);

    void deleteBatch(String[] taskIds);

    YTaskEntity selectOneTask();

    List<YTaskUserVo> selectTask();

    Integer selectTaskNum();

    List<YTaskUserVo> selectTaskByUserId(String userId);

    YTaskEntity selectOneTaskByUserId(String userId);

    Integer selectTaskNumByUserId(String userId);

    int selectPointByTaskId(String taskUserId);
}
