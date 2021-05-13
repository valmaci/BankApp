package bank;

/**
 * Course: CS 3331 Advanced Object-Oriented Programming
 * Author: Valeria Macias
 * Assignment: Bank App 
 * Instructor: Daniel Mejia
 * Date of Last Modification: 12/15/19
 * Purpose: Abstract class inherited by the Customer class
 */

public abstract class Person {
	
	private String fullName = "";
	private String dob = "";
	private int identificationNo = -1;
	private String address = "";
	private String phoneNo = "";
	
	// Setters
	public void setName(String fn) {
		this.fullName = fn.replaceAll("^\"|\"$", "");
	}
	public void setDob(String dob) {
		this.dob = dob.replaceAll("^\"|\"$", "");
	}
	public void setIdentificationNo(int id) {
		this.identificationNo = id;
	}
	
	public void setAddress(String add) {
		this.address = add.replaceAll("^\"|\"$", "");
	}
	
	public void setPhoneNo(String pn) {
		this.phoneNo = pn.replaceAll("^\"|\"$", "");
	}
	
	// Getters
	public String getName() {
		return this.fullName;
	}
	public String getDob() {
		return this.dob;
	}
	public int getID() {
		return this.identificationNo;
	}
	public String getAddress() {
		return this.address;
	}
	public String getPhoneNo() {
		return this.phoneNo;
	}
	
	// Returns a string containing all of the Person's information
	public String toString() {
		return ("Name: " + fullName + "\n" +
				"Date of Birth: " + dob + "\n" +
				"Identification Number: " + identificationNo + "\n" +
				"Address: " + address + "\n" +
				"Phone Number: " + phoneNo);
	}
}
