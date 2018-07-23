package com.ater.modules.dao;

import com.ater.modules.entity.YFetalMovementEntity;
import com.ater.modules.dao.BaseDao;
import com.ater.modules.entity.YTbTaskEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-05 14:01:08
 */
@Mapper
public interface YFetalMovementDao extends BaseDao<YFetalMovementEntity> {
    int queryTodayTotal(Map<String, Object> map);

    int querySubmitTotal(Map<String, Object> map);

    List<YFetalMovementEntity> queryWebList(Object id);

    int queryWebTotal(Map<String, Object> map);

    int checkTodayByUserId(String userId);

    int checkTodayPdByUserId(String userId);

    YFetalMovementEntity  queryPrint(String moveId);

    int queryTbTaskTotal(Map<String, Object> map);

    List<YTbTaskEntity> queryYTbTaskList(Object id);

}
