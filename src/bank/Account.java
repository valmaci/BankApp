package bank;
/**
 * Course: CS 3331 Advanced Object-Oriented Programming
 * Author: Valeria Macias
 * Assignment: Bank App 
 * Instructor: Daniel Mejia
 * Date of Last Modification: 12/15/19
 * Purpose: Abstract account class methods and attributes are inherited by the Checking, Credit and Savings classes.
 */

public abstract class Account {
	
	private int accountNo = -1;
	private double balance = 0.0;
	
	//SETTERS
	public void setAccountNo(int a) {
		this.accountNo = a;
	}
	public void setBalance(double b) {
		this.balance = b;
	}
	
	//GETTERS
	public int getAccountNo() {
		return this.accountNo;
	}
	public double getBalance() {
		return this.balance;
	}
	
	//Returns String of account information
	public String toString() {
		String balanceFormat = String.format("%.2f", this.balance);
		return ("Account Number: " + this.accountNo + " Balance: $" + balanceFormat);
	}
	
	/**
	 * Deposits by increasing the value of the account balance.
	 * Only deposits when the monetary value is a positive double.
	 * The user is notified of transaction success/failure.
	 * Returns a boolean value used by the bankstatement log. If false, will not log.
	*/
	public boolean deposit(double money) {
		if (money > 0) {
			this.balance += money;
			System.out.println("Deposit Successful.");
			return true;
		}
		else {
			System.out.println("(!) Invalid Deposit Amount");
			return false;
		}
	}
	
	/**
	 * Withdraws by decreasing the value of the account balance.
	 * Only withdraws when the monetary value is a positive double and is less than or equal to the account balance.
	 * The user is notified of transaction success/failure.
	 * Returns a boolean value used by the bankstatement log. If false, will not log.
	*/
	public boolean withdraw(double money) {
		if (money <= this.balance && money > 0) {
			this.balance -= money;
			System.out.println("Withdraw Successful.");
			return true;
		}
		else {
			System.out.println("(!) Insufficient Funds");
			return false;
		}
	}
	
	/**
	 * Transers by decreasing the value of the giving account balance and increasing the value of the receiving account balance.
	 * Only transfesr when the monetary value is a positive double and is less than or equal to the giving account balance.
	 * The user is notified of transaction success/failure.
	 * Returns a boolean value used by the bankstatement log. If false, will not log.
	*/
	public boolean transfer(double money, Account acc) {
		if (money <= this.balance && money > 0) {
			this.balance -= money;
			acc.setBalance(acc.getBalance() + money);
			System.out.println("Transfer Successful");
			return true;
		}
		else {
			System.out.println("(!) Invalid Transfer Amount");
			return false;
		}
	}
}
