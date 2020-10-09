package com.hsbc.accDAO;

import java.util.List;

import com.hsbc.accCustomException.InsufficientBalanceException;
import com.hsbc.accCustomException.InvalidAccountException;
import com.hsbc.accModel.Account;

public interface AccountDao {

	public Account createAccount(Account acc);
	public Account updateBalance(int accNumber,double amount);
	public Account getAccount(int accNumber);
	public Account deleteAccount(int accNumber) throws InvalidAccountException;
	public List<Account> getAccounts();
	public double getBalance(int accNumber);
	public Account debit(int accNum,double amount) throws InvalidAccountException,InsufficientBalanceException;
	public Account credit(int accNum,double amount) throws InvalidAccountException;
}