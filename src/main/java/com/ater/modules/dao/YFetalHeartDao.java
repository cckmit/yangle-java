package com.ater.modules.dao;

import com.ater.modules.entity.YFetalHeartEntity;
import com.ater.modules.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-04 15:42:00
 */
@Mapper
public interface YFetalHeartDao extends BaseDao<YFetalHeartEntity> {

    int insertByBatch(List<YFetalHeartEntity> fetalHeartList);
}
