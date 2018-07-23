package com.ater.modules.service;

import com.ater.common.utils.Query;
import com.ater.modules.entity.YFetalMovementEntity;
import com.ater.modules.entity.YTbTaskEntity;

import java.util.List;
import java.util.Map;

/**
 * @author ater
 * @email ${email}
 * @date 2018-06-05 14:01:08
 */
public interface YFetalMovementService {

    YFetalMovementEntity queryObject(String moveId);

    List<YFetalMovementEntity> queryList(Map<String, Object> map);

    List<YTbTaskEntity> queryYTbTaskList(Map<String, Object> map);

  int  queryTbTaskTotal(Map<String, Object> map);


    int queryTotal(Map<String, Object> map);

    void save(YFetalMovementEntity yFetalMovement);

    void update(YFetalMovementEntity yFetalMovement);

    void delete(String moveId);

    void deleteBatch(String[] moveIds);

    int queryTodayTotal(Query query);

    List<YFetalMovementEntity> queryWebList(Query query);

    int queryWebTotal(Query query);

    int querySubmitTotal(Query query);

    /**
     * 查询某个用户当天是否完成胎心监测
     */
    int checkTodayByUserId(String userId);

    /**
     * 查询某个用户当天是否已经提交胎心判读
     *
     * @param userId
     * @return
     */
    int checkTodayPdByUserId(String userId);

    YFetalMovementEntity  queryPrint(String moveId);
}
