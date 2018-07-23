package com.ater.modules.dao;


import com.ater.modules.entity.YPregnancyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-05-31 19:02:50
 */
@Mapper
public interface YPregnancyDao extends BaseDao<YPregnancyEntity> {
    YPregnancyEntity queryOneByEquipmentId(String equipmentId);

     int updatePregnancy(YPregnancyEntity yPregnancy);
}
