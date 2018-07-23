package com.ater.modules.dao;

import java.util.List;


import com.ater.modules.entity.SmsResultBean;
import com.ater.modules.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseDao<User>{
	//public int userRegister(@Param("userAccount") String userAccount,@Param("userPassword") String userPassword);
	
	public int checkUserAccountExist(@Param("userAccount") String userAccount,@Param("userRole") String userRole);
	
	public User userLogin(@Param("userAccount") String userAccount, @Param("userPassword1") String userPassword1,@Param("userRole") String userRole);


    public User userWebLogin(@Param("userName") String userName, @Param("userPassword1") String userPassword1,@Param("userRole") String userRole);
    //验证inviteCode是否已经存在
	public int checkInviteCodeExist(@Param("inviteCode") String inviteCode);
	
	 
	//重置密码
	public Integer resetUserPassword(@Param("userPassword1") String userPassword1, @Param("userAccount") String userAccount, @Param("userRole") String userRole);



	public SmsResultBean selectSmsCode(@Param("userAccount") String userAccount, @Param("smsType") String smsType);


	int insertsms(SmsResultBean sms);

	public User queryuser(@Param("userId") String userId,@Param("userRole") String userRole );

	public User queryuserByUserAccount(@Param("userAccount") String userAccount,@Param("userRole") String userRole );

	public int 	updateById(User user);

  public List<User> selectDoctorList(String userRole);

}
