package com.ater.modules.dao;

import com.ater.modules.entity.YTaskEntity;
import com.ater.modules.entity.YTaskUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 *
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:14:21
 */
@Mapper
public interface YTaskDao extends BaseDao<YTaskEntity> {
    List<YTaskUserVo> selectTask();

    Integer selectTaskNum();

    YTaskEntity selectOneTask();

    List<YTaskUserVo> selectTaskByUserId(String userId);

    YTaskEntity selectOneTaskByUserId(String userId);

    Integer selectTaskNumByUserId(String userId);

    int selectPointByTaskId(String taskUserId);


}
