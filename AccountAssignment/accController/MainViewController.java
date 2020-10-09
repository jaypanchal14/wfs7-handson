package com.hsbc.accController;

import java.util.List;
import java.util.Scanner;

import com.hsbc.accCustomException.InvalidAccountException;
import com.hsbc.accModel.Account;
import com.hsbc.accService.AccountService;
import com.hsbc.accUtil.ObjectFactory;

public class MainViewController {

	public static void main(String[] args) {

	try{	
		int option = 0;
		Scanner s = new Scanner(System.in);
		AccountService service = ObjectFactory.getAccountServiceInstance();
		do {
			List<Account> list = null;
			System.out.println("1: Create Account");
			System.out.println("2: Check Balance");
			System.out.println("3: Tranfer Amount");
			System.out.println("4: Display after Sorting by Name");
			System.out.println("5: Display after Sorting by AccountNumber");
			System.out.println("6: Delete Account");
			System.out.println("0: To EXIT ");
			option = s.nextInt();
			switch(option){
			case 1:
				System.out.println("Enter Name: ");
				Account account = new Account(s.next());
				Account createdAcc = service.createAccount(account);
				System.out.println(createdAcc);
				break;
			case 2:
				System.out.println("Enter AccountNumber: ");
				int accNum = s.nextInt();
				System.out.println("Your balance: "+service.getBalance(accNum));
				break;
			case 3:
				System.out.println("Enter AccountNumber of Source: ");
				int accSource = s.nextInt();
				System.out.println("Enter AccountNumber of Destination: ");
				int accDest = s.nextInt();
				System.out.println("Enter Amount to be transferred: ");
				double amount = s.nextDouble();
				service.transfer(accSource, accDest, amount);
				break;
			case 4:
				list = service.getAccountsSortByName();
				list.forEach(acc -> System.out.println(acc));
				break;
			case 5:
				list = service.getAccountsSortByAccoutNumber();
				list.forEach(acc -> System.out.println(acc));
				break;
			case 6:
				System.out.println("Enter AccountNumber to be deleted: ");
				int acc = s.nextInt();
				try{
					Account acc1 = service.deleteAccount(acc);
					System.out.println(acc1+"  Account Deleted!");
				}
				catch(InvalidAccountException e){
					System.out.println(e.getMessage());
				}
				break;
			}	
		} while (option != 0);
		s.close();
	}
	catch(Exception e){
		System.out.println("Something went wrong");
	}
	}
}