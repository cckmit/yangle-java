package com.ater.modules.dao;

import com.ater.modules.entity.YSignInEntity;
import com.ater.modules.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-07 17:24:47
 */
@Mapper
public interface YSignInDao extends BaseDao<YSignInEntity> {
    public Map<String, Object> selectSign(Map<String, String> map);

    public int insertSign(Map<String, String> map);

    public int updateSign(Map<String, String> map);

    public void updateSerialTimes(YSignInEntity ySignInEntity);

    public int selectSerialTimes(String userId);

}
