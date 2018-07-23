package com.ater.modules.service.impl;

import com.ater.modules.dao.FzBillingDao;
import com.ater.modules.entity.FzBillingEntity;
import com.ater.modules.service.FzBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("fzBillingService")
public class FzBillingServiceImpl implements FzBillingService {
	@Autowired
	private FzBillingDao fzBillingDao;

	@Override
	public FzBillingEntity queryObject(Long id){
		return fzBillingDao.queryObject(id);
	}
}
