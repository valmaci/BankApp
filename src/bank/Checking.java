package bank;

/**
 * Course: CS 3331 Advanced Object-Oriented Programming
 * Author: Valeria Macias
 * Assignment: Bank App 
 * Instructor: Daniel Mejia
 * Date of Last Modification: 12/15/19
 * Purpose: Checking inherits Account class and contains a Customer's Checking account information.
 */

public class Checking extends Account{

	// Constructor
	public Checking(int a, double b) {
		setAccountNo(a);
		setBalance(b);
	}
	
	// Returns Checking account information
	public String toString() {
		return ("Checking " + super.toString());
	}
}
