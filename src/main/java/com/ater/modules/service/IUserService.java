package com.ater.modules.service;

import java.util.List;


import com.ater.modules.entity.SmsResultBean;
import com.ater.modules.entity.User;
import org.apache.ibatis.annotations.Param;

public interface IUserService {
	
	//验证手机号码是否已经存在
	public boolean checkUserAccountExist(String userAccount,String userRole);
    //用户登录
	public User userLogin(String userAccount, String userPassword1,String userRole);


	public User userWebLogin(String userName, String userPassword1,String userRole);

	public User queryuser(String userId,String userRole);
	//重置密码
	public Integer resetUserPassword(String userPassword1, String userAccount,String userRole);
	


	boolean insert(User user);

	public SmsResultBean selectSmsCode(String userAccount, String smsType);

	boolean insertsms(SmsResultBean sms);

	User queryuserByUserAccount( String userAccount ,String userRole);

    int updateById(User user);

	 List<User> selectDoctorList(String userRole);
}
