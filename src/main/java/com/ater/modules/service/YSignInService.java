package com.ater.modules.service;

import com.ater.modules.entity.YSignInEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-07 17:24:47
 */
public interface YSignInService {
	
	YSignInEntity queryObject(String id);
	
	List<YSignInEntity> queryList(Map <String, Object> map);
	
	int queryTotal(Map <String, Object> map);
	
	void save(YSignInEntity ySignIn);
	
	void update(YSignInEntity ySignIn);

	void updateSerialTimes(YSignInEntity ySignInEntity);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);

	String sign(String userId,String userRole);

	 int selectSerialTimes(String userId);

	Map<String, Object> selectSign(Map<String, String> map);
}
