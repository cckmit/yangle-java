package com.ater.modules.dao;

import com.ater.modules.entity.YBabyStatusEntity;
import com.ater.modules.dao.BaseDao;
import com.ater.modules.entity.YBabyStatusEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 16:01:35
 */
@Mapper
public interface YBabyStatusDao extends BaseDao<YBabyStatusEntity> {
     YBabyStatusEntity queryOne(int week);
}
