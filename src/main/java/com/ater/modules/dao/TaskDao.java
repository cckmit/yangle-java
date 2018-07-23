package com.ater.modules.dao;


import com.ater.modules.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author
 * @email
 * @date
 */
@Mapper
public interface TaskDao extends BaseDao<TaskEntity> {
    List<TaskEntity> queryOneByMId(String mqid);

    TaskEntity queryOneByMIdAndResult(@Param("mqid") String mqid,@Param("result") String result);
}
