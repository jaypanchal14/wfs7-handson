package com.hsbc.accService;

import java.util.List;

import com.hsbc.accCustomException.InsufficientBalanceException;
import com.hsbc.accCustomException.InvalidAccountException;
import com.hsbc.accModel.Account;

public interface AccountService {
	
	public Account createAccount(Account acc);
	public double getBalance(int accNumber) throws InvalidAccountException;
	
	//Call updateBalance() on sourceAcc and destAcc
	public void transfer(int sourceAcc,int destAcc,double amount) throws InvalidAccountException,InsufficientBalanceException;
	public List<Account> getAccountsSortByName();
	public List<Account> getAccountsSortByAccoutNumber();
	public Account deleteAccount(int accountNumber) throws InvalidAccountException;
}