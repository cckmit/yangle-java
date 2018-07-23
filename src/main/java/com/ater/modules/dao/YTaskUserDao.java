package com.ater.modules.dao;

import com.ater.modules.entity.YTaskUserEntity;
import com.ater.modules.dao.BaseDao;
import com.ater.modules.entity.YTaskUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author ater
 * @email ${email}
 * @date 2018-06-08 16:16:00
 */
@Mapper
public interface YTaskUserDao extends BaseDao<YTaskUserEntity> {
    List<YTaskUserVo> queryListByUserId(Map<String, Object> map);

    YTaskUserVo queryOne(String taskUserId );

    YTaskUserEntity selectOneByUserId(String userId);

    YTaskUserEntity queryTodayPriority2(Map<String, Object> map);

    YTaskUserEntity queryTodayPriority3(Map<String, Object> map);

    void updateStatus(YTaskUserVo yTaskUserVo);
}
