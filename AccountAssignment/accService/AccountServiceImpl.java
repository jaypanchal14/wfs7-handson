package com.hsbc.accService;

import java.util.List;
import java.util.stream.Collectors;

import com.hsbc.accCustomException.InsufficientBalanceException;
import com.hsbc.accCustomException.InvalidAccountException;
import com.hsbc.accDAO.AccountDao;
import com.hsbc.accModel.Account;
import com.hsbc.accUtil.ObjectFactory;

public class AccountServiceImpl implements AccountService{

	private AccountDao accountDao = null;
		
	public AccountServiceImpl() {
		accountDao = ObjectFactory.getAccountDaoInstance();
	}

	@Override
	public Account createAccount(Account acc) {
		return accountDao.createAccount(acc);
	}

	@Override
	public double getBalance(int accNumber) throws InvalidAccountException{
		// TODO Auto-generated method stub
		Account acc = accountDao.getAccount(accNumber);
		if(acc == null){
			throw new InvalidAccountException("Invalid Account Number");
		}	
		return acc.getBalance();
	}

	@Override
	public void transfer(int sourceAcc, int destAcc, double amount)  throws InvalidAccountException,InsufficientBalanceException{
		Account source = accountDao.getAccount(sourceAcc);
		Account dest = accountDao.getAccount(destAcc);
		if(source == null || dest == null){
			throw new InvalidAccountException();
		}
		accountDao.debit(sourceAcc, amount);
		accountDao.credit(destAcc, amount);
	}

	@Override
	public List<Account> getAccountsSortByName() {
		List<Account> accounts = accountDao.getAccounts();
		List<Account> sortedAcc = accounts.stream().sorted((acc1,acc2)->acc1.getCustomerName().compareTo(acc2.getCustomerName()))
				.collect(Collectors.toList());
		return sortedAcc;
	}

	@Override
	public List<Account> getAccountsSortByAccoutNumber() {
		List<Account> accounts = accountDao.getAccounts();
		List<Account> sortedAcc = accounts.stream().sorted((acc1,acc2)->acc1.getAccountNum()-acc2.getAccountNum())
				.collect(Collectors.toList());
		return sortedAcc;
	}

	@Override
	public Account deleteAccount(int accountNumber) throws InvalidAccountException {
		return accountDao.deleteAccount(accountNumber);
	}
}