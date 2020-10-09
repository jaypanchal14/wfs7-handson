package com.hsbc.accModel;

public class Account {
	
	private int accountNum;
	private String customerName;
	private double balance;
	private static int accountCounter = 0;
	
	public Account(String customerName) {
		super();
		this.customerName = customerName;
		this.balance = 5000;
		this.accountNum = ++accountCounter;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", customerName=" + customerName + ", balance=" + balance + "]";
	}
	
}