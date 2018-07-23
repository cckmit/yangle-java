package com.ater.modules.dao;


import com.ater.modules.entity.YTipsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:39:00
 */
@Mapper
public interface YTipsDao extends BaseDao<YTipsEntity> {
    YTipsEntity queryOne();
}
