package bank;

import java.io.*;
import java.util.*;

/**
 * Course: CS 3331 Advanced Object-Oriented Programming
 * Author: Valeria Macias
 * Assignment: Bank App 
 * Instructor: Daniel Mejia
 * Date of Last Modification: 12/15/19
 * Purpose: Contains the Main which runs the Main Menu, Bank Manager Menu and Customer Menu.
 */

public class RunBank {
	/**
	 * The account ID's and Customer ID's are stored in the class to create unique ID's for each new customer and accounts.
	 */
	public static int accID = 0;
	public static int custID = 0;
	
	/**
	 * Main method contains the HashMap of the bank. The createBank method reads the text file containing all of the Customer information and stores it in a HashMap
	 * The HashMap is passed to the main menu for user interactions. The main method ends when the user exists the Main Menu.
	 */
	public static void main(String[] args) throws Exception {
		HashMap<String,Object> bankMap = new HashMap<String,Object>();
		
		System.out.println("\nWELCOME TO AAOOPBANK\n" +
			"\nPlease select menu options by inputting the number of the option." +
			"\nEnjoy!");
		
		createBank("BankUsers.txt", bankMap);
		mainMenu(bankMap);
		System.out.println("Goodbye");
	}
	/**
	 * Reads a file containing customer information, stores that information in Customer objects and stores the Customer objects in the HashMap.
	 * The HashMap key is the Customer's name.
	 */
	public static void createBank(String filename, HashMap map) throws Exception {
		HashMap<String,Integer> indexMap = new HashMap<String,Integer>();			
		File file = new File(filename);
		Scanner input = new Scanner(file);
		String row0 = input.nextLine();
		String[] row0Data = row0.split("\t");
		for (int i=0; i<row0Data.length;i++) {
			indexMap.put(row0Data[i],i);
		}
		while(input.hasNext()) {
			String row = input.nextLine();
			String[] data = row.split("\t");
			int idno = map.size()+1;
			int creditAccNo = -1;
			double creditBalance = 0;
			int checkingAccNo = -1;
			double checkingBalance = 0;
			int savingsAccNo = -1;
			double savingsBalance = 0;
			if (data[indexMap.get("Identification Number")] != null) {
				idno = Integer.valueOf(data[indexMap.get("Identification Number")]);
			}
			if (data[indexMap.get("Credit Account Number")] != null) {
				creditAccNo = Integer.valueOf(data[indexMap.get("Credit Account Number")]);
			}
			if (data[indexMap.get("Credit Starting Balance")] != null) {
				creditBalance = Double.valueOf(data[indexMap.get("Credit Starting Balance")]);
			}
			if (data[indexMap.get("Checking Account Number")] != null) {
				checkingAccNo = Integer.valueOf(data[indexMap.get("Checking Account Number")]);
			}
			if (data[indexMap.get("Checking Starting Balance")] != null) {
				checkingBalance = Double.valueOf(data[indexMap.get("Checking Starting Balance")]);
			}
			if (data[indexMap.get("Savings Account Number")] != null) {
				savingsAccNo = Integer.valueOf(data[indexMap.get("Savings Account Number")]);
			}
			if (data[indexMap.get("Savings Starting Balance")] != null) {
				savingsBalance = Double.valueOf(data[indexMap.get("Savings Starting Balance")]);
			}
			Customer customer = new Customer((data[indexMap.get("First Name")] + " " + data[indexMap.get("Last Name")]), data[indexMap.get("Date of Birth")], idno, data[indexMap.get("Address")],data[indexMap.get("Phone Number")], creditAccNo, creditBalance, checkingAccNo, checkingBalance, savingsAccNo, savingsBalance);
			map.put(customer.getName(),customer);
			if (creditAccNo > accID) {
				accID = creditAccNo;
			}
			if (checkingAccNo > accID) {
				accID = creditAccNo;
			}
			if (savingsAccNo > accID) {
				accID = savingsAccNo;
			}
			if (idno > custID) {
				custID = idno;
			}
		}
		input.close();
	}
	/**
	 * Contains a path to the Bank Manager Menu, the Customer Menu and the Transaction Reader. Exiting this method, ends the program session.
	 */
	public static void mainMenu(HashMap map) throws Exception {
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println("\n=========================\n" +
					"\tMain Menu\n" + 
					"=========================\n" +
					"Please select an option or input '0' to end the session\n" +
					"1. Bank Manager Menu\n" +
					"2. Customer Menu\n" +
					"3. Transaction Reader Menu\n");
			String menuType = in.nextLine();
			if (menuType.equals("0")) {
				break;
			}
			else if (menuType.equals("1")) {
				bankManagerMenu(in,map);
			}
			else if (menuType.equals("2")) {
				customerMenu(in,map);
			}
			else if (menuType.equals("3")) {
				transactionReaderMenu(in, map);
			}
			else {
				System.out.println("\n(!) Invalid input.");
			}
		}
	}
	/**
	 * Allows Bank Manager to conduct individual account inquiries, all bank user inquiries and add a new Customer to the bank system.
	 */
	public static void bankManagerMenu(Scanner in, HashMap map) {
		while(true) {
			System.out.println("\n=========================\n" +
					"    Bank Manager Menu\n" + 
					"=========================\n" +
					"Please select an option or input '0' to return to the Main Menu\n" +
					"1. Individual Customer Account Inquiry\n" + 
					"2. All Bank Users Inquiry\n" +
					"3. Add New Customer\n");
			String managerChoice = in.nextLine();
			if (managerChoice.equals("0")) {
				break;
			}
			else if (managerChoice.equals("1")) {
				customerInquiry(in,map,null);
			}
			else if (managerChoice.equals("2")) {
				allInquiry(map);
			}
			else if (managerChoice.equals("3")) {
				addNewCustomer(in,map);
			}
			else {
				System.out.println("\n(!) Invalid input.\n");
			}
		}
	}
	/**
	 * Allows Customer to conduct personal account inquiries, withdraw, deposit, transfer and print bank statements.
	 */
	public static void customerMenu(Scanner in, HashMap map) throws Exception {
		Customer user = null;
		while(true) {
			System.out.println("\n=========================\n" +
					"    Customer Login\n" + 
					"=========================\n" +
					"Please input your full name or input '0' to return to the Main Menu\n");
			String userName = in.nextLine();
			if (userName.equals("0")) {
				break;
			}
			user = (Customer) map.get(userName);
			if (user == null) {
				System.out.println("\n(!) User does not exist.\n");
			}
			else {
				break;
			}
		}
		if(user != null) {
			while(true) {
				System.out.println("\n=========================\n" +
						"    " + user.getName() + "  Menu\n" + 
						"=========================\n" +
						"Please select an option or input '0' to return to the Main Menu\n" +
						"1. Account Inquiry\n" + 
						"2. Withdraw\n" +
						"3. Deposit\n" +
						"4. Transfer\n" +
						"5. Print Bank Statement\n");
				String userChoice = in.nextLine();
				if (userChoice.equals("0")) {
					break;
				}
				else if (userChoice.equals("1")) {
					customerInquiry(in,map,user);
				}
				else if (userChoice.equals("2")) {
					withdraw(in,user, "", -1.0);
				}
				else if (userChoice.equals("3")) {
					deposit(in,user, "", -1.0);
					in.nextLine();
				}
				else if (userChoice.equals("4")) {
					transfer(in, user, "", null, "", false, -1.0,map);
					in.nextLine();
				}
				else if (userChoice.equals("5")) {
					printBankStatement(user);
				}
				else {
					System.out.println("\n(!) Invalid input\n");
				}
			}
		}
	}
	/**
	 * Reads premade transactions in a text file. Inputting "Transactions.txt" in the console will conduct the premade instructions automatically.
	 */
	public static void transactionReaderMenu(Scanner in, HashMap map) throws Exception {
		String fromName = "";
		Customer fromCust = null;
		String fromAccount = "";
		String toName = "";
		Customer toCust = null;
		String toAccount = "";
		Double money = -1.0;
		HashMap<String,Integer> indexTrans = new HashMap<String,Integer>();
		System.out.println("================================\n" +
				"Transaction Reader\n" +
				"================================\n" +
				"Please input the name of the text file containing the transactions or input '0' to return to the Main Menu");
		String filename = in.nextLine();
		if (filename.equals("0")) {
			return;
		}
		try {
			File file = new File(filename);
			Scanner input = new Scanner(file);
			String row0 = input.nextLine();
			String[] row0Data = row0.split("\t");
			for(int i=0;i<row0Data.length;i++) {
				indexTrans.put(row0Data[i],i);
			}
			while(input.hasNext()) {
				String row = input.nextLine();
				String[] data = row.split("\t");
				String action = data[indexTrans.get("Action")];
				
				if(!action.equals("deposits")) {
					fromName = data[indexTrans.get("From First Name")] + " " + data[indexTrans.get("From Last Name")];
					fromCust = (Customer) map.get(fromName);
					fromAccount = data[indexTrans.get("From Where")];
				}		
				if (!action.equals("inquires") && !action.equals("withdraws")) {
					toName = data[indexTrans.get("To First Name")] + " " + data[indexTrans.get("To Last Name")];
					toCust = (Customer) map.get(toName);
					toAccount = data[indexTrans.get("To Where")];
					
				}
				if (!action.equals("inquires")) {
					money = Double.valueOf(data[indexTrans.get("Action Amount")]);
				}
					
				if(action.equals("pays")) {
					transfer(in, fromCust, fromAccount, toCust, toAccount, true, money, map);
				}
				else if(action.equals("transfers")) {
					transfer(in, fromCust, fromAccount, toCust, toAccount, false, money, map);
				}
				
				else if(action.equals("inquires")) {
					customerInquiry(in,map,fromCust);
				}
				else if(action.equals("deposits")) {
					deposit(in, toCust, toAccount, money); 
				
				}
				else if(action.equals("withdraws")) {
					withdraw(in,fromCust,fromAccount,money);
				}
			}
			System.out.println("Transactions Successful");
			input.close();
		}
		catch (Exception e) {
			System.out.println("File not found\n");
		}
	}
	/**
	 * Displays the user's account information
	 */
	public static void customerInquiry(Scanner in, HashMap map, Customer cust) {
		System.out.println("\n==========================\n" +
				"    Account Inquiry\n" +
				"==========================\n");
		if (cust == null) {
			while(true) {
				System.out.println("\nPlease input the name of the customer or '0' to return to the Bank Manager Menu:\n");
				String name = in.nextLine();
				if (name.equals("0")) {
					break;
				}
				Customer custInq = (Customer) map.get(name);
				if (custInq != null) {
					System.out.println(custInq.toString());
					break;
				}
				else {
					System.out.println("(!) User not found\n");
				}
			}
		}
		else {
			System.out.println(cust.toString());
		}
	}
	/**
	 * Displays the account information of every customer in the bank system.
	 */
	public static void allInquiry(HashMap map) {
		System.out.println("\n==========================\n" +
				"\t All Accounts\n" +
				"==========================\n");
		Iterator mapIterator = map.entrySet().iterator();
		while(mapIterator.hasNext()) {
			Map.Entry m = (Map.Entry) mapIterator.next();
			Customer cust = (Customer) m.getValue();
			System.out.print("\n" + cust.toString() + "\n");
		}
	}
	/**
	 * Adds a new customer to the bank system.
	 */
	public static void addNewCustomer(Scanner in, HashMap map) {
		while(true) {
			System.out.println("\n==============================\n" +
					"\tAdd New Customer\n" +
					"==============================\n" +
					"Please input the full name of the new customer or input '0' to return to the Bank Manager Menu\n");
			String name = in.nextLine();
			if (name.equals("0")) {
				break;
			}
			System.out.println("\nPlease input the date of birth of the customer or input '0' to return to the Bank Manager Menu\n");
			String dob = in.nextLine();
			if(dob.equals("0")) {
				break;
			}
			System.out.println("\nPlease input the address of the customer or input '0' to return to the Bank Manager Menu\n");
			String address = in.nextLine();
			if(address.equals("0")) {
				break;
			}
			System.out.println("\nPlease input the phone number of the customer or input '0' to return to the Bank Manager Menu\n");
			String phno = in.nextLine();
			if(phno.equals("0")) {
				break;
			}
			custID++;
			accID++;
			int crID = accID;
			accID++;
			int chID = accID;
			accID++;
			int svID = accID;
			Customer newCust = new Customer(name, dob, custID, address, phno, crID, 0.0, chID, 0.0, svID, 0.0);
			map.put(name, newCust);
			System.out.println("New Customer Added");
			break;
		}
	}
	/**
	 * Withdraws money from specified customer account. Prompts the user for instructions or reads from the transaction reader.
	 */
	public static void withdraw(Scanner in, Customer cust, String fromAccount, Double money) {
		if(fromAccount.length() == 0) {
			while(true) {
				System.out.println("\n==========================\n" +
						"\tWithdraw\n" +
						"==========================\n" +
						"Please select the account you would like to withdraw from or input '0' to return to the previous Menu:\n" +
						"1. Checking\n" +
						"2. Savings\n" +
						"3. Credit\n");
				fromAccount = in.nextLine();
				if (fromAccount.equals("0")) {
					System.out.println(fromAccount);
					return;
				}
				else if (fromAccount.equals("1") ||fromAccount.equals("2") ||fromAccount.equals("3")) {
					break;
				}
				else {
					System.out.println("\n(!) Invalid input");
				}
			}
		}
		if(fromAccount.equals("1") || fromAccount.equals("Checking")) {
			if (money == -1.0) {
				System.out.println("Please input the amount you would like to withdraw:\n");
				while (true) {
					try {
						money = Double.valueOf(in.nextLine());
						break;
					}
					catch (Exception E) {
						System.out.println("\n(!) Invalid input. Please input the amount you would like to withdraw.");
					}
				}
			}
			if(cust.getChecking().withdraw(money)) {
				cust.getBankStatement().logWithdraw("Checking", cust.getChecking(), money);
			}
		}
		else if (fromAccount.equals("2") || fromAccount.equals("Savings")) {
			if (money == -1.0) {
				System.out.println("Please input the amount you would like to withdraw:\n");
				while (true) {
					try {
						money = Double.valueOf(in.nextLine());
						break;
					}
					catch (Exception e){
						System.out.println("\n(!) Invalid input. PLease input the amount you would like to withdraw.");
					}
				}	
			}
			if(cust.getSavings().withdraw(money)) {
				cust.getBankStatement().logWithdraw("Savings", cust.getSavings(), money);
			}
		}
		else if (fromAccount.equals("3") || fromAccount.equals("Credit")) {
			if (money == -1.0) {
				System.out.println("\nPlease input the amount you would like to withdraw:\n");
				while (true) {
					try {
						money = Double.valueOf(in.nextLine());
						break;
					}
					catch (Exception e) {
						System.out.println("\n(!) Invalid input. PLease input the amount you would like to withdraw.");
					}
				}	
			}
			if(cust.getCredit().withdraw(money)) {
				cust.getBankStatement().logWithdraw("Credit", cust.getCredit(), money);
			}
		}
	}
	/**
	 * Deposits money into specified customer account. Prompts the user for instructions or reads from the transaction reader.
	 */
	public static void deposit(Scanner in, Customer cust, String toAccount, Double money) {
		if (toAccount.length() == 0) {
			while(true) {
				System.out.println("\n==========================\n" +
						"\tDeposit\n" +
						"==========================\n" +
						"Please select the account you would like to deposit into or input '0' to return to the previous Menu:\n" +
						"1. Checking\n" +
						"2. Savings\n" +
						"3. Credit\n");
				toAccount = in.nextLine();
				if(toAccount.equals("0")) {
					return;
				}
				else if (toAccount.equals("1") || toAccount.equals("2") || toAccount.equals("3")) {
					break;
				}
				else {
					System.out.println("\n(!) Invalid input");
				}
			}
		}
		if(toAccount.equals("1") || toAccount.equals("Checking")) {
			if(money == -1.0) {
				System.out.println("Please input the amount you would like to deposit:\n");
				while(true) {
					try {
						money = Double.valueOf(in.nextLine());
						break;
					}
					catch (Exception e) {
						System.out.println("(!) Invalid input. PLease input the amount you would like to deposit.");
					}
				}
			}
			if(cust.getChecking().deposit(money)) {
				cust.getBankStatement().logDeposit("Checking", cust.getChecking(), money);
			}
		}
		else if (toAccount.equals("2") || toAccount.equals("Savings")) {
			if(money == -1.0) {
				System.out.println("Please input the amount you would like to deposit:\n");
				while(true) {
					try {
						money = Double.valueOf(in.nextLine());
						break;
					}
					catch (Exception e) {
						System.out.println("(!) Invalid input. PLease input the amount you would like to deposit.");
					}
				}
			}
			if(cust.getSavings().deposit(money)) {
				cust.getBankStatement().logDeposit("Savings", cust.getSavings(), money);
			}
		}
		else if (toAccount.equals("3") || toAccount.equals("Credit")) {
			if(money == -1.0) {
				System.out.println("Please input the amount you would like to deposit:\n");
				while(true) {
					try {
						money = Double.valueOf(in.nextLine());
						break;
					}
					catch (Exception e) {
						System.out.println("(!) Invalid input. PLease input the amount you would like to deposit.");
					}
				}
			}
			if(cust.getCredit().deposit(money)) {
				cust.getBankStatement().logDeposit("Credit", cust.getCredit(), money);
			}
		}
	}
	/**
	 * Transfers money from specified customer account to other specified customer account. Prompts the user for instructions or reads from the transaction reader.
	 */
	public static void transfer(Scanner in, Customer fromCust, String fromAcc, Customer toCust, String toAcc, boolean pay, Double money, HashMap map) {
			if (fromAcc.length() == 0) {
				while(true) {
					System.out.println("==========================\n" +
							"\tTransfer\n" +
							"==========================\n" +
							"Please select the account you would like to transfer from or input '0' to return to the previous Menu:\n" +
							"1. Checking\n" +
							"2. Savings\n");
					fromAcc = in.nextLine();
					if(fromAcc.equals("0")) {
						return;
					}
					else if (fromAcc.equals("1") || fromAcc.equals("2")) {
						break;
					} 
					else {
						System.out.println("(!) Invalid Input");
					}
				}
			}
			if(fromAcc.equals("1") || fromAcc.equals("Checking") ||pay) {
				Checking fromAccount = fromCust.getChecking();
				if (toAcc.length() == 0) {
					while(true) {
						System.out.println("\n==========================\n" +
								"\tTransfer\n" +
								"==========================\n" +
								"Please select the account you would like to transfer to or input '0' to return to the previous Menu:\n" +
								"1. Checking\n" +
								"2. Savings\n" +
								"3. Credit\n" +
								"4. Bank User");
						toAcc = in.nextLine();
						if(toAcc.equals("0")) {
							return;
						}
						else if (toAcc.equals("1") ||toAcc.equals("2") ||toAcc.equals("3") ||toAcc.equals("4")) {
							break;
						}
						else {
							System.out.println("\n(!) Invalid Input");
						}
					}
				}
				if(toAcc.equals("1") || toAcc.equals("Checking") && !pay) {
					Checking toAccount = fromCust.getChecking();
					if (money == -1.0) {
						System.out.println("Please input the amount you would like to transfer");
						while(true) {
							try {
								money = in.nextDouble();
								break;
							}
							catch (Exception e) {
								System.out.println("(!) Invalid input. Please input the amount you would like to transfer");
							}
						}	
					}
					if(fromAccount.transfer(money, toAccount)) {
						fromCust.getBankStatement().logWithdraw("Checking", fromCust.getChecking(),money);
						fromCust.getBankStatement().logDeposit("Checking", toCust.getChecking(), money);
					}
				}
				else if (toAcc.equals("2") || toAcc.equals("Savings")) {
					Savings toAccount = fromCust.getSavings();
					if (money == -1.0) {
						System.out.println("Please input the amount you would like to transfer");
						while(true) {
							try {
								money = in.nextDouble();
								break;
							}
							catch (Exception e) {
								System.out.println("(!) Invalid input. Please input the amount you would like to transfer");
							}
						}
					}
					if(fromAccount.transfer(money, toAccount)) {
						fromCust.getBankStatement().logWithdraw("Checking", fromCust.getChecking(),money);
						fromCust.getBankStatement().logDeposit("Savings", toCust.getSavings(), money);
					}
				}
				else if (toAcc.equals("3") || toAcc.equals("Credit")) {
					Credit toAccount = fromCust.getCredit();
					if (money == -1.0) {
						System.out.println("Please input the amount you would like to transfer");
						while(true) {
							try {
								money = in.nextDouble();
								break;
							}
							catch (Exception e) {
								System.out.println("(!) Invalid input. Please input the amount you would like to transfer");
							}
						}
					}
					if(fromAccount.transfer(money, toAccount)) {
						fromCust.getBankStatement().logWithdraw("Checking", fromCust.getChecking(),money);
						fromCust.getBankStatement().logDeposit("Credit", toCust.getCredit(), money);
					}
				}
				else if (toAcc.equals("4") || pay) {
					if (toCust == null) {
						toAcc = "";
						while(true) {
							System.out.println("Please input the name of the user you would like to transfer to or input '0' to return to the previous menu");
							String toName = in.nextLine();
							if (toName.equals("0")) {
								break;
							}
							toCust = (Customer) map.get(toName);
							if (toCust == null) {
								System.out.println("(!) User not found");
							}
							if (toCust != null) {
								break;
							}
						}
					}
					if (toCust != null) {
						if (toAcc.length() == 0) {
							while(true) {
								System.out.println("\n==========================\n" +
										"\tTransfer to " + toCust.getName() + "\n" +
										"==========================\n" +
										"Please select the account you would like to transfer to or input '0' to return to the previous Menu:\n" +
										"1. Checking\n" +
										"2. Savings\n");
								toAcc = in.nextLine();
								if (toAcc.equals("0")) {
									return;
								}
								else if (toAcc.equals("1") ||toAcc.equals("2")) {
									break;
								}
								else {
									System.out.println("\n(!) Invalid Input");
								}
							}
						}
						if (toAcc.equals("1") || toAcc.equals("Checking") ||pay) {
							Checking toAccount = toCust.getChecking();
							if (money == -1.0) {
								System.out.println("Please input the amount you would like to transfer");
								while(true) {
									try {
										money = in.nextDouble();
										break;
									}
									catch (Exception e) {
										System.out.println("(!) Invalid input. Please input the amount you would like to transfer");
									}
								}
							}
							if(fromAccount.transfer(money, toAccount)) {
								fromCust.getBankStatement().logWithdraw("Checking", fromCust.getChecking(),money);
								toCust.getBankStatement().logDeposit("Checking", toCust.getChecking(), money);
							}
						}
						if (toAcc.equals("2") || toAcc.equals("Savings")) {
							Savings toAccount = toCust.getSavings();
							if (money == -1.0) {
								System.out.println("Please input the amount you would like to transfer");
								while(true) {
									try {
										money = in.nextDouble();
										break;
									}
									catch (Exception e) {
										System.out.println("(!) Invalid input. Please input the amount you would like to transfer");
									}
								}
							}
							if(fromAccount.transfer(money, toAccount)) {
								fromCust.getBankStatement().logWithdraw("Checking", fromCust.getChecking(),money);
								toCust.getBankStatement().logDeposit("Savings", toCust.getSavings(), money);
							}
						}
					}
				}
			}
			if(fromAcc.equals("2") || fromAcc.equals("Savings")) {
				Savings fromAccount = fromCust.getSavings();
				if (toAcc.length() == 0) {
					while(true) {
						System.out.println("==========================\n" +
								"\tTransfer\n" +
								"==========================\n" +
								"Please select the account you would like to transfer to or input '0' to return to the previous Menu:\n" +
								"1. Checking\n" +
								"2. Savings\n" +
								"3. Credit\n" +
								"4. Bank User");
						toAcc = in.nextLine();
						if(toAcc.equals("0")) {
							return;
						}
						else if(toAcc.equals("1")||toAcc.equals("2")||toAcc.equals("3")||toAcc.equals("4")) {
							break;
						}
						else {
							System.out.println("(!) Invalid Input");
						}
					}
				}
				if(toAcc.equals("1") || toAcc.equals("Checking")) {
					Checking toAccount = fromCust.getChecking();
					if (money == -1.0) {
						System.out.println("Please input the amount you would like to transfer");
						while(true) {
							try {
								money = in.nextDouble();
								break;
							}
							catch (Exception e) {
								System.out.println("(!) Invalid input. Please input the amount you would like to transfer");
							}
						}
					}
					if(fromAccount.transfer(money, toAccount)) {
						fromCust.getBankStatement().logWithdraw("Savings", fromCust.getSavings(),money);
						fromCust.getBankStatement().logDeposit("Checking", toCust.getChecking(), money);
					}
				}
				else if (toAcc.equals("2") || toAcc.equals("Savings")) {
					Savings toAccount = fromCust.getSavings();
					if (money == -1.0) {
						System.out.println("Please input the amount you would like to transfer");
						while(true) {
							try {
								money = in.nextDouble();
								break;
							}
							catch (Exception e) {
								System.out.println("(!) Invalid input. Please input the amount you would like to transfer");
							}
						}
					}
					if(fromAccount.transfer(money, toAccount)) {
						fromCust.getBankStatement().logWithdraw("Savings", fromCust.getSavings(),money);
						fromCust.getBankStatement().logDeposit("Savings", toCust.getSavings(), money);
					}
				}
				else if (toAcc.equals("3") || toAcc.equals("Credit")) {
					Credit toAccount = fromCust.getCredit();
					if (money == -1.0) {
						System.out.println("Please input the amount you would like to transfer");
						while(true) {
							try {
								money = in.nextDouble();
								break;
							}
							catch (Exception e) {
								System.out.println("(!) Invalid input. Please input the amount you would like to transfer");
							}
						}
					}
					if(fromAccount.transfer(money, toAccount)) {
						fromCust.getBankStatement().logWithdraw("Savings", fromCust.getSavings(),money);
						fromCust.getBankStatement().logDeposit("Credit", toCust.getCredit(), money);
					}
				}
				else if (toAcc.equals("4")) {
					if (toCust == null) {
						toAcc = "";
						while (true) {
							System.out.println("Please input the name of the user you would like to transfer to or input '0' to return to the previous menu");
							String toName = in.nextLine();
							if (toName.equals("0")) {
								return;
							}
							toCust = (Customer) map.get(toName);
							if (toCust == null) {
								System.out.println("(!) User not found");
							}
							if (toCust != null) {
								break;
							}
						}
					}	
					if (toCust != null) {
						while (true) {
							System.out.println("==========================\n" +
									"\tTransfer to " + toCust.getName() + "\n" +
									"==========================\n" +
									"Please select the account you would like to transfer to or input '0' to return to the previous Menu:\n" +
									"1. Checking\n" +
									"2. Savings\n");
								toAcc = in.nextLine();
								if (toAcc.equals("0")) {
									return;
								}
								else if (toAcc.equals("1") || toAcc.equals("2")) {
									break;
								}
								else {
									System.out.println("\n(!) Invalid Input");
								}
						}
						if (toAcc.equals("1")) {
							Checking toAccount = toCust.getChecking();
							if (money == -1.0) {
								System.out.println("Please input the amount you would like to transfer");
								while(true) {
									try {
										money = in.nextDouble();
										break;
									}
									catch (Exception e) {
										System.out.println("(!) Invalid input. Please input the amount you would like to transfer");
									}
								}
							}
							if(fromAccount.transfer(money, toAccount)) {
								fromCust.getBankStatement().logWithdraw("Savings", fromCust.getSavings(),money);
								toCust.getBankStatement().logDeposit("Checking", toCust.getChecking(), money);
							}
						}
						if (toAcc.equals("2")) {
							Savings toAccount = toCust.getSavings();
							if (money == -1.0) {
								System.out.println("Please input the amount you would like to transfer");
								while(true) {
									try {
										money = in.nextDouble();
										break;
									}
									catch (Exception e) {
										System.out.println("(!) Invalid input. Please input the amount you would like to transfer");
									}
								}
							}
							if(fromAccount.transfer(money, toAccount)) {
								fromCust.getBankStatement().logWithdraw("Savings", fromCust.getSavings(),money);
								toCust.getBankStatement().logDeposit("Savings", toCust.getSavings(), money);
							}
						}
					}
				}	
			}
		}
	/**
	 * Creates a new text file containing the specified customer's bank statement.
	 */
	public static void printBankStatement(Customer customer) throws Exception {
		PrintWriter bankStatement = new PrintWriter(customer.getName() + " Bank Statement.txt");
		bankStatement.println(customer.getBankStatement().toString());
		bankStatement.close();
	}
}
