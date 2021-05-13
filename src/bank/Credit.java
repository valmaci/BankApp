package bank;

/**
 * Course: CS 3331 Advanced Object-Oriented Programming
 * Author: Valeria Macias
 * Assignment: Bank App 
 * Instructor: Daniel Mejia
 * Date of Last Modification: 12/15/19
 * Purpose: Inherits the Account class and contains the Customer's Credit account information.
 */

public class Credit extends Account{
	
	// Constructor
	public Credit(int a, double b) {
		setAccountNo(a);
		setBalance(b);
	}
	
	/**
	 * Because Credit is a debt, deposits by decreasing the value of the account balance.
	 * Only deposits when the monetary value is a positive double.
	 * The user is notified of transaction success/failure.
	 * Returns a boolean value used by the bankstatement log. If false, will not log.
	*/
	public boolean deposit(double money) {
		if (money <= 0) {
			System.out.println("(!) Invalid amount");
			return false;
		}
		else {
			super.setBalance(super.getBalance()-money);
			System.out.println("Deposit Successful");
			return true;
		}
	}
	
	/**
	 * Because Credit is a debt, withdraws by increasing the value of the account balance.
	 * Only withdraws when the monetary value is a positive double.
	 * The user is notified of transaction success/failure.
	 * Returns a boolean value used by the bankstatement log. If false, will not log.
	*/
	public boolean withdraw(double money) {
		if (money <= 0 ) {
			System.out.println("(!) Invalid amount");
			return false;
		}
		else {
			super.setBalance(super.getBalance()+money);
			System.out.println("Withdraw Successful");
			return true;
		}
	}

	// Returns a string containing the credit account information
	public String toString() {
		String balanceFormat = String.format("%.2f", super.getBalance());
		return ("Credit Account Number: " + super.getAccountNo() + " Balance: -$" + balanceFormat);
	}
}
