package com.ater.modules.dao;

import com.ater.modules.entity.YSowingMapEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:07:15
 */
@Mapper
public interface YSowingMapDao extends BaseDao<YSowingMapEntity> {
    public List<YSowingMapEntity> queryAllList();
}
