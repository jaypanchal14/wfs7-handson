package com.hsbc.accDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hsbc.accCustomException.InsufficientBalanceException;
import com.hsbc.accCustomException.InvalidAccountException;
import com.hsbc.accModel.Account;

public class CollectionAccountDao implements AccountDao{

	private static List<Account> database = new ArrayList<Account>();
	@Override
	public Account createAccount(Account acc) {
		database.add(acc);
		return acc;
	}

	@Override
	public Account updateBalance(int accNumber, double amount) {
		Account acc = getAccount(accNumber);
		acc.setBalance(amount);
		database.set(accNumber-1, acc);
		return acc;
	}
	
	@Override
	public double getBalance(int accNumber) {
		return database.get(accNumber-1).getBalance();
	}

	@Override
	public Account getAccount(int accNumber) {
		return database.stream().filter(acc -> acc.getAccountNum() == accNumber).findAny().orElse(null);
	}

	@Override
	public List<Account> getAccounts() {
		return database;
	}

	@Override
	public Account deleteAccount(int accNumber) throws InvalidAccountException {
		int size = database.size();
		List<Account> accounts = database.stream().filter(acc->acc.getAccountNum()!=accNumber).collect(Collectors.toList());
		Account acc = this.getAccount(accNumber);
		if(accounts.size()==size){
			throw new InvalidAccountException();
		}
		return acc;
	}

	@Override
	public Account debit(int accNum, double amount) throws InvalidAccountException, InsufficientBalanceException {
		for(Account acc: database){
			if(acc.getAccountNum()==accNum){
				if(acc.getBalance()<amount){
					throw new InsufficientBalanceException();
				}
				acc.setBalance(acc.getBalance()-amount);
			}
		}
		throw new InvalidAccountException();
	}

	@Override
	public Account credit(int accNum, double amount) throws InvalidAccountException {
		for(Account acc:database){
			if(acc.getAccountNum()==accNum){
				acc.setBalance(acc.getBalance()+amount);
				return acc;
			}
		}
		throw new InvalidAccountException();
	}

}