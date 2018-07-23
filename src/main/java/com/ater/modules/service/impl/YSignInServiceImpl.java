package com.ater.modules.service.impl;

import com.ater.common.utils.JSONUtils;
import com.ater.common.utils.MyUtil;
import com.ater.common.utils.StringUtils;
import com.ater.modules.dao.UserMapper;
import com.ater.modules.dao.YPointDao;
import com.ater.modules.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ater.modules.dao.YSignInDao;
import com.ater.modules.entity.YSignInEntity;
import com.ater.modules.service.YSignInService;



@Service("ySignInService")
public class YSignInServiceImpl implements YSignInService {
	@Autowired
	private YSignInDao ySignInDao;
	@Autowired
	private UserMapper userMapper;

    @Autowired
    private YPointDao yPointDao;

	@Override
	public YSignInEntity queryObject(String id){
		return ySignInDao.queryObject(id);
	}

	@Override
	public List<YSignInEntity> queryList(Map<String, Object> map){
		return ySignInDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return ySignInDao.queryTotal(map);
	}

	@Override
	public void save(YSignInEntity ySignIn){
		ySignInDao.save(ySignIn);
	}

	@Override
	public void update(YSignInEntity ySignIn){
		ySignInDao.update(ySignIn);
	}

	@Override
	public void updateSerialTimes(YSignInEntity ySignInEntity) {
		ySignInDao.updateSerialTimes(ySignInEntity);
	}

	@Override
	public void delete(String id){
		ySignInDao.delete(id);
	}

	@Override
	public void deleteBatch(String[] ids){
		ySignInDao.deleteBatch(ids);
	}

	@Override
	public String sign(String userId,String userRole) {
		{
			Map<String, String> resultMap = new HashMap<String, String>();
			Map<String, String> map = new HashMap<String, String>();
			if(!StringUtils.isEmpty(userId)){
				User user = userMapper.queryuser(userId,userRole);
				if(user != null){
					map.put("userId", userId);
					Map<String, Object> signResultMap  = ySignInDao.selectSign(map);
					if(signResultMap != null){
						String diffDay = StringUtils.getMapValue("diffDay", signResultMap);
						if(diffDay.equals("0")){
							resultMap.put("resultMsg","今天已签到");
							resultMap.put("resultCode","404");
						}else if(diffDay.equals("-1")){
							if(ySignInDao.updateSign(map)==0){
								resultMap.put("resultMsg","签到失败");
								resultMap.put("resultCode","404");
							}else{
								//现在积分写成死的
                             int signSerialTimes=   StringUtils.getMapIntValue("signSerialTimes", signResultMap);
                             if(signSerialTimes==1){
                                 map.put("point","point+3");
                                 yPointDao.updatePoint(map);
								 resultMap.put("signSerialTimes","2");
								 resultMap.put("point","3");
                             }else if(signSerialTimes==2){
                                 map.put("point","point+5");
                                 yPointDao.updatePoint(map);
								 resultMap.put("signSerialTimes","3");
								 resultMap.put("point","5");
                             }else if(signSerialTimes==3){
                                 map.put("point","point+5");
                                 yPointDao.updatePoint(map);
								 resultMap.put("signSerialTimes","4");
								 resultMap.put("point","5");
                             }else if(signSerialTimes==4){
                                 map.put("point","point+10");
								 resultMap.put("signSerialTimes","5");
                                 yPointDao.updatePoint(map);
								 resultMap.put("point","10");
                             }else if(signSerialTimes==5){
                                 map.put("point","point+15");
								 resultMap.put("signSerialTimes","6");
                                 yPointDao.updatePoint(map);
								 resultMap.put("point","15");
                             }else if(signSerialTimes==6) {
                                 map.put("point","point+20");
                                 yPointDao.updatePoint(map);
								 resultMap.put("signSerialTimes","7");
								 resultMap.put("point","20");
                             }else{
								 map.put("point","point+3");
								 yPointDao.updatePoint(map);
								 resultMap.put("signSerialTimes","1");
								 resultMap.put("point","3");
							 }
                             	resultMap.put("resultMsg","连续签到"+(StringUtils.getMapIntValue("signSerialTimes", signResultMap)+1)+"次");
								resultMap.put("resultCode","200");
							}
						}else{
							map.put("signSerialTimes", "0");
							if(ySignInDao.updateSign(map) == 0){
								resultMap.put("resultMsg","签到失败");
								resultMap.put("resultCode","404");

							}else{
								map.put("point","point+3");
								yPointDao.updatePoint(map);
								resultMap.put("signSerialTimes","1");
								resultMap.put("point","3");
                                resultMap.put("resultMsg","累计签到"+(StringUtils.getMapIntValue("signTotalTimes", signResultMap)+1)+"次");
								resultMap.put("resultCode","200");
							}
						}
					}else{
						map.put("id", MyUtil.generateId());
						if(ySignInDao.insertSign(map) == 0){
							resultMap.put("resultMsg","签到失败");
							resultMap.put("resultCode","404");
						}else{
							map.put("pointType", "1");
							map.put("pointId", MyUtil.generateId());
							// 签到一次 积分加3分
							map.put("point","3");
							yPointDao.insertPoint(map);
							resultMap.put("resultMsg","签到1次");
							resultMap.put("signTotalTimes","1");
							resultMap.put("point","3");
							resultMap.put("resultCode","200");
						}
					}

				}else{
					resultMap.put("resultMsg","用户未登录");
					resultMap.put("resultCode","404");
				}
			}else{
				resultMap.put("resultMsg","用户未登录");
				resultMap.put("resultCode","404");
			}
			return JSONUtils.toJson(resultMap);
		}
	}
	public int selectSerialTimes(String userId){
		return  ySignInDao.selectSerialTimes(userId);
	}

	public Map<String, Object> selectSign(Map<String, String> map){
		return ySignInDao.selectSign(map);
	}
}
