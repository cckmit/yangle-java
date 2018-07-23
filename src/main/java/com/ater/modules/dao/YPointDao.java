package com.ater.modules.dao;

import com.ater.modules.entity.YPointEntity;
import com.ater.modules.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-07 18:00:25
 */
@Mapper
public interface YPointDao extends BaseDao<YPointEntity> {

      int insertPoint(Map<String, String> map);

      int updatePoint(Map<String, String> map);

    YPointEntity selectPoint(@Param("userId") String userId,@Param("pointType") String pointType);
}
