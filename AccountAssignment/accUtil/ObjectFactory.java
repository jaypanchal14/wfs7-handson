package com.hsbc.accUtil;

import com.hsbc.accDAO.AccountDao;
import com.hsbc.accDAO.CollectionAccountDao;
import com.hsbc.accService.AccountService;
import com.hsbc.accService.AccountServiceImpl;

public class ObjectFactory {

	public static AccountDao getAccountDaoInstance(){
		return new CollectionAccountDao();
	}
	
	public static AccountService getAccountServiceInstance(){
		return new AccountServiceImpl();
	}
}