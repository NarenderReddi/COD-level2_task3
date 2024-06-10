package com.bank;

public interface bank {

	int Withdraw(int balance);

	int Deposit(int balance);

	int CheckBalance();
		//interface's implemented by the UserbankAccount 
		//is used becoz anybank should use these methods must
}
