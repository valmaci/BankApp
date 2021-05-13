package bank;

/**
 * Course: CS 3331 Advanced Object-Oriented Programming
 * Author: Valeria Macias
 * Assignment: Bank App 
 * Instructor: Daniel Mejia
 * Date of Last Modification: 12/15/19
 * Purpose: Inherits the Account class and contains the Savings account information of a Customer.
 */

public class Savings extends Account {

	// Constructor
	public Savings(int a, double b) {
		setAccountNo(a);
		setBalance(b);
	}
	
	// Returns a string containing the Savings account information
	public String toString() {
		return ("Savings " + super.toString());
	}
}
