package bank;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Course: CS 3331 Advanced Object-Oriented Programming
 * Author: Valeria Macias
 * Assignment: Bank App 
 * Instructor: Daniel Mejia
 * Date of Last Modification: 12/15/19
 * Purpose: Bank Statement is a Queue containing a record of a customer's transactions during their session. 
 *		Bank statements are stored as objects inside of each Customer's object.
 *		An option to print a Customer's bank statement in a .txt file or on the console exists in the Customer Menu.
 */

public class BankStatement {
	
	Queue<String> checkingStatement;
	Queue<String> savingsStatement;
	Queue<String> creditStatement;
	
	// Constructor
	public BankStatement() {
		this.checkingStatement = new LinkedList<>();
		this.savingsStatement = new LinkedList<>();
		this.creditStatement = new LinkedList<>();
	}
	
	/**
	 * Prints the Customer's Checking account transactions, Savings account transactions, then Credit account transactions.
	 * The Queue is fully restored after printing to retain the order of future transactions with previous transactions.
	 */
	public void print() {
		Queue<String> qCh = new LinkedList<>();
		Queue<String> qSv = new LinkedList<>();
		Queue<String> qCr = new LinkedList<>();
		
		int size = this.checkingStatement.size();
		for (int i = 0; i < size; i++) {
			String r = (String) this.checkingStatement.remove();
			System.out.println(r);
			if (i < size-1) {
				qCh.add(r);	
			}
		}
		this.checkingStatement = qCh;
		
		size = this.savingsStatement.size();
		for (int i = 0; i < size; i++) {
			String r = (String) this.savingsStatement.remove();
			System.out.println(r);
			if (i < size-1) {
				qSv.add(r);	
			}
		}
		this.savingsStatement = qSv;
		
		size = this.creditStatement.size();
		for (int i = 0; i < size; i++) {
			String r = (String) this.creditStatement.remove();
			System.out.println(r);
			if (i < size-1) {
				qCr.add(r);	
			}
		}
		this.creditStatement = qCr;
	}
	
	/**
	 * Returns the Customer's Checking account transactions, Savings account transactions and Credit account transactions.
	 * The Queue is fully restored before returning retain the order of future transactions with previous transactions.
	 * This method is used by the BufferedWriter to print to a .txt file when Customer prints from Customer menu.
	 */
	public String toString() {
		Queue<String> qCh = new LinkedList<>();
		Queue<String> qSv = new LinkedList<>();
		Queue<String> qCr = new LinkedList<>();
		String qString = "";
		
		int size = this.checkingStatement.size();
		for (int i = 0; i < size; i++) {
			String r = (String) this.checkingStatement.remove();
			qString += "\n" + r;
			if (i < size-1) {
				qCh.add(r);	
			}
		}
		this.checkingStatement = qCh;	
		
		size = this.savingsStatement.size();
		for(int i = 0; i < size; i++) {
			String r = (String) this.savingsStatement.remove();
			qString += "\n" + r;
			if (i < size-1) {
				qSv.add(r);	
			}
		}
		this.savingsStatement = qSv;
		
		size = this.creditStatement.size();
		for(int i = 0; i < size; i++) {
			String r = (String) this.creditStatement.remove();
			qString += "\n" + r;
			if (i < size-1) {
				qCr.add(r);	
			}
		}
		this.creditStatement = qCr;
		
		return qString;
	}
	
	/**
	 * Adds header information to each Queue when the BankStatement object is created.
	 */
	public void firstCall(String memberInfo) {
		this.checkingStatement.add(memberInfo);
		this.checkingStatement.add("\n CHECKING: ");
		this.checkingStatement.add("Date and Time\t\tTransaction\tBalance");
		this.savingsStatement.add("\n SAVINGS:");
		this.savingsStatement.add("Date and Time\t\tTransaction\tBalance");
		this.creditStatement.add("\n CREDIT:");
		this.creditStatement.add("Date and Time\t\tTransaction\tBalance");
	}
	
	/**
	* Logs each withdrawal by inserting a string containing the account date, the amount withdrawn and the remaining balance.
	*/
	public void logWithdraw(String account, Account acc, double money) {
		String balanceFormat = String.format("%.2f", acc.getBalance());
		String moneyFormat = String.format("%.2f",money);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
		String dateFormat = LocalDateTime.now().format(formatter);
		String s = (String) ("\n" + dateFormat +"\t -$" + moneyFormat + "\t$" + balanceFormat);
		if (account.equals("Checking")) {
			this.checkingStatement.add(s);
		}
		else if (account.equals("Savings")) {
			this.savingsStatement.add(s);
		}
		else if (account.equals("Credit")) {
			this.creditStatement.add(s);
		}
	}
	
	/**
	* Logs each deposit by inserting a string containing the account date, the amount deposited and the remaining balance.
	*/
	public void logDeposit(String account, Account acc, double money) {
		String balanceFormat = String.format("%.2f", acc.getBalance());
		String moneyFormat = String.format("%.2f",money);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
		String dateFormat = LocalDateTime.now().format(formatter);
		String s = (String) ("\n" + dateFormat +"\t +$" + moneyFormat + "\t$" + balanceFormat);
		if (account.equals("Checking")) {
			this.checkingStatement.add(s);
		}
		else if (account.equals("Savings")) {
			this.savingsStatement.add(s);
		}
		else if (account.equals("Credit")) {
			this.creditStatement.add(s);
		}
	}
}
