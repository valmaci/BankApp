package bank;

/**
 * Course: CS 3331 Advanced Object-Oriented Programming
 * Author: Valeria Macias
 * Assignment: Bank App 
 * Instructor: Daniel Mejia
 * Date of Last Modification: 12/15/19
 * Purpose: Inherits the Person class and contains all of the Customer's information.
 */

public class Customer extends Person {
	
	private Credit credit;
	private Checking checking;
	private Savings savings;
	private BankStatement bankStatement;
	
	// Constructor
	public Customer(String fn, String dob, int idno, String add, String pn, int creditAccNo, double creditBalance, int checkingAccNo, double checkingBalance, int savingsAccNo, double savingsBalance) {
		setName(fn);
		setDob(dob);
		setIdentificationNo(idno);
		setAddress(add);
		setPhoneNo(pn);
		credit = new Credit(creditAccNo, creditBalance);
		checking = new Checking(checkingAccNo, checkingBalance);
		savings = new Savings(savingsAccNo, savingsBalance);
		bankStatement = new BankStatement();
		bankStatement.firstCall(this.toString());
	}
	
	// Getters
	public Credit getCredit() {
		return this.credit;
	}
	public Checking getChecking() {
		return this.checking;
	}
	public Savings getSavings() {
		return this.savings;
	}
	public BankStatement getBankStatement() {
		return this.bankStatement;
	}
	
	//Returns a String containing all of the Customer's information
	public String toString() {
		return super.toString() + "\n" +
				checking.toString() + "\n" +
				savings.toString() + "\n" +
				credit.toString();
	}
}
