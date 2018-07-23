package com.ater.modules.service.impl;

import com.ater.modules.dao.UserMapper;

/*import com.ater.modules.entity.SmsResultBean;*/
import com.ater.modules.entity.SmsResultBean;
import com.ater.modules.entity.User;
import com.ater.modules.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userServiceAPI")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	//验证手机号是否已经存在
	@Override
	public boolean checkUserAccountExist(String userAccount,String userRole) {
		int temp=userMapper.checkUserAccountExist(userAccount,userRole);
		if (temp==1) {
			return true;
		}else {
			return false;
		}
	}
    //用户登录
	@Override
	public User userLogin(String userAccount, String userPassword1,String userRole) {
		// TODO Auto-generated method stub
		return userMapper.userLogin(userAccount, userPassword1,userRole);
	}

    @Override
    public User userWebLogin(String userName, String userPassword1,String userRole) {
        // TODO Auto-generated method stub
        return userMapper.userWebLogin(userName, userPassword1,userRole);
    }
	

	public SmsResultBean selectSmsCode(String userAccount, String smsType){
		return userMapper.selectSmsCode(userAccount, smsType);
	}

	@Override
	public boolean insertsms(SmsResultBean sms) {
		if(userMapper.insertsms(sms)==1){
			return true;
		}else {
			return false;
		}

	}

	//重置密码
	 @Override
    public Integer resetUserPassword(String userPassword1, String userAccount,String userRole) {
	 	return userMapper.resetUserPassword(userPassword1,userAccount,userRole);
	 	}
		

	public boolean insert(User user){
		if(userMapper.save(user)==1){
			return true;
		}else{
			return false;
		}
 	}
	public User queryuserByUserAccount(String userAccount,String userRole ){
		return userMapper.queryuserByUserAccount(userAccount,userRole);
	}

	public User queryuser(String userId,String userRole){
		return userMapper.queryuser(userId,userRole);
	}

    public int updateById(User user){
	    return userMapper.updateById(user);
    }

	public List<User> selectDoctorList(String userRole){
		return userMapper.selectDoctorList(userRole);
	}
}
