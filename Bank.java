/** Title: Bank.java
 * 
 * 	Abstract: This program is an example of a bank with four different files, bank, customer
 * 				Transaction, and account. In the bank its uses array lists of the three
 * 				other classes
 * 
 * 	Author: Stephan Dubuke
 * 
 * 	ID: 0484
 * 
 * 	Date: 10-8-15
 * 
 * 
 */
package project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Bank {
	// creats the arraylists of classes
	ArrayList<Account> account = new ArrayList<Account>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Transaction> transaction = new ArrayList<Transaction>();
		private int numOfAccounts = 0;
		private int numOfCustomers = 0;
	    private double bankTotal = 0;
	    private String bankName = "Unknown";
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	    // constructor of the bank name 
	   public Bank (String bankName)
	    {
	    	this.bankName = bankName;
	    }
	    //readin file
		public void readData(String file)
		{
			// i use a try catch for buffered reader
	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	    String line = br.readLine();
	    // i get the first line in the file and set it equal to the num of customers
	    numOfCustomers = Integer.parseInt(line);
	    for(int i = 0; i < numOfCustomers; i++) {
	    	line = br.readLine();
	    	// i break the second line up by the commas and put them into an array
	    	String [] values = line.split(",");
	    	// and create a new customer with it
	    	customers.add(new Customer(values[0], values[1], Integer.parseInt(values[2]),
	    			values[3]));
	    }
	    line = br.readLine();
	    // after the loop is done it grabs the next lone number and put it 
	    // to the number of accounts
	    numOfAccounts = Integer.parseInt(line);
	    
	    for(int i = 0; i < numOfAccounts; i++) {
	    	line = br.readLine();
	    	// again i split the lines with the comma into an array
	    	String [] values = line.split(",");
	    	// checks to see if the customer list has the ssn so it can make an account
	    	// with the same ssn conecting the two
	    	if(customers.contains(new Customer(values[0]))){
	    	account.add(new Account(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]),
	    					Double.parseDouble(values[3])));
	    	}
	    	
	    }
	    } catch (Exception e) {			
			e.printStackTrace();
	    		}
	
		}
		// function for bankinfo
		public void bankInfo()
		{
			//prints the bank name
			System.out.println("Bank Name: " + this.bankName);
			System.out.println("Number of Customers: " + this.numOfCustomers);
			// loops though the customers and prints the to string for the class
			for(Customer entry: customers)
			{
				System.out.println(entry);
			}
			System.out.println("Number of Accounts: " + this.numOfAccounts);
			// loops though the accounts and prints the to string for the class
			for(Account entry: account)
			{
				System.out.println(entry);
				this.bankTotal += entry.getBalance();
			}
			System.out.println("Bank Total: " + this.bankTotal);
			
		}
		// function that searches the accounts and checks to see if the account number is real
		// if it is it prints out the information 
		public void accountInfo(int accNum)
		{
			DecimalFormat df = new DecimalFormat("0.00");
			Account check = new Account(accNum);
			if(account.contains(new Account(accNum)))
			{
				System.out.print("- Number: ");
				System.out.println(account.get(account.indexOf(check)).getNum());
				if(account.get(account.indexOf(check)).getType() == 1)
				{
					System.out.println("- Checking ");
				}
				else{
					System.out.println("- Savings ");}
				System.out.print("- Balance: ");
				System.out.println(df.format(account.get(account.indexOf(check)).getBalance()));
				System.out.print("- Customer: ");
				// to get the cutomers name i went though the customers list to get the index
				// of the account 
				System.out.print(customers.get(account.indexOf(check)).getName());
			}
			else
				System.out.println("Account (" + accNum + ") does not exist.");
		}
		
		// boolean function of deposit
		public boolean deposit(int accNum, double balance)
		{
			// checks to find the account in the list throws it into accounts
			// deposit function and creates a new time stamp of it 
			Account check = new Account(accNum);
			if(account.contains(check))
			{
				account.get(account.indexOf(check)).deposit(balance);
				transaction.add(new Transaction(accNum, "Deposit (" + balance + ")", 
						date.format(cal.getTime())));
				return true;
			}			
			return false;
		}
		// boolean for withdrawal checks to see if the account num is in the list
		// checks to see if the funds they want to take out is less than what they have 
		// throws it into the withdrawal function in account and makes a time stamp
		public boolean withdraw(int accNum, double amount)
		{
			Account check = new Account(accNum);
			if(account.contains(check))
			{
				if(amount <= account.get(account.indexOf(check)).getBalance() )
				{
				account.get(account.indexOf(check)).withdraw(amount);
				transaction.add(new Transaction(accNum, "Witchdraw (" + amount + ")", 
						date.format(cal.getTime())));
				return true;
				}
			}
			return false;
		}
		// boolean function of close account checks to see if the acc num is in the list 
		// if it is it closes the account and decriments the counter and removes it from the list
		public boolean closeAccount(int accNum)
		{
			Account check = new Account(accNum);
			if(account.contains(check))
			{
				account.remove(check);
				transaction.add(new Transaction(accNum, "Close Account", 
						date.format(cal.getTime())));
				numOfAccounts--;
				return true;
			}
			return false;
		}
		
		// function for transaction, goes though the transaction list to see if the account num
		// that youre looking up has had a transaction if so it prints all
		public void transaction(int accNum)
		{
			boolean hasaccount = false;
				for(Transaction entry: transaction)
				{
					if(entry.getaccNum() == accNum)
					{
						
					System.out.println((entry));
					hasaccount = true;
					}
				}
				if(!hasaccount)
					System.out.println(accNum + " has no transactions.");
			}
			
		// adds a new customer 
		public void newCustomer(String name, String addr, int zip, String ssn)
		{
			// makes a new customer with the info to check to see if its alreay in the 
			// list iff its not it add the new customer created 
			Customer check = new Customer(name, addr, zip, ssn);
			if(!customers.contains(check))
			{
				customers.add(check);
				numOfCustomers++;
				System.out.println((customers.get(customers.indexOf(check)).getName()) 
				+ " is added.");
			}
			else
				System.out.println(name + "is NOT added - Duplicated SSN.");
		}
		// boolean for adding a new account
		public boolean newAccount(String ssn, int accNum, int type, double balance)
		{
			// loops though the customers list to get the name with the ssn given
			String name = null;
			for(Customer entry: customers)
			{
				if(entry.getSSN().equals(ssn))
				{
					name = entry.getName();
				}
			}
			// loops though the account list with the ssn given to make sure they are in the bank
			// checks to see if they have either a checkings or a savings 
			for(Account entry: account)
			{
				// checking the ssn to the given ssn
				if(entry.getSsn().equals(ssn))
				{
					//checks the type checking == 1 savings == 2
					if(entry.getType() == type)
					{
						System.out.print("Account creation failed – " + name +
								" (" + ssn + ") already has a");
						if(type == 1)
						{
							System.out.println(" checking account");
							return false;
						}
						else{
							System.out.println(" savings account");
							return false;}
					}
				
				}
				// checks to see if the account number already exists
				if(entry.getNum() == accNum)
				{
					System.out.println("Account creation failed – " + name + " " + accNum 
							+ "aready exists");
					return false;
				}
			}
			// if it passes the checks, adds the account and prints out true
			account.add( new Account(ssn, accNum, type, balance));
			System.out.println("Account creation – " + accNum + " Customer: " + name );
			return true;
		}
		
		// boolean function to search the customer with a given ssn
		public boolean customerInfoWithSSN(int ssn)
		{
			// uses two other booleans to keep track if they have an account or if 
			// they are even in the bank
			boolean hasAccount = false;
			boolean noSocail = false;
			DecimalFormat df = new DecimalFormat("0.00");
			// first loop though the customers
			for(Customer entry: customers)
				{
				// i go though the customers to get the full snn, using the substring of 
				// aka the last 4 digits of the ssn.
				if (Integer.parseInt(entry.getSSN().substring(7, 11)) == ssn) 
				{
					// if the customer is in the bank sets nosocail = true
					noSocail = true;
					// adds the full ssn to a new string
					String check = entry.getSSN();
					System.out.println("Name: " + entry.getName() + "\n\t" + entry.getAdress()
					+ "\n\t" + entry.getZip() + "\n\t" + "SSN: " + entry.getSSN());
					// then loop thought accounts with the full ssn to get the account info 
					// of the custoemr 
					for (Account entry1 : account) 
					{
						if (entry1.getSsn().equals(check)) 
						{
							// if checkings prints it out
							// if savings prints it out
							// and sets has account to true
							if (entry1.getType() == 1) 
							{
								System.out.println("Checking (" + entry1.getNum() +
										"), $" + df.format(entry1.getBalance()));
								hasAccount = true;
							} else if (entry1.getType() == 2) 
							{
								System.out.println("Savings (" + entry1.getNum() +
										"), $" + df.format(entry1.getBalance()));
								hasAccount = true;
							}
						}
					}
				}
		}
			// if the customer is not in the bank it displays that
			if(!noSocail)
			{
				System.out.println("No customer with " + ssn);
				return false;
			}
			// if the customer doesnt have an account it displays that as well
			if(!hasAccount)
			{
				System.out.println("\tno account");
				return false;
			}
			return true;
	}
		// boolean function for removing the customer
		public boolean removeCustomer(String ssn)
		{
			DecimalFormat df = new DecimalFormat("0.00");
			// sets an index values for the customer and the accounts to be removed
			// and a boolean for has social 
			int index = -1;
			int index1 = -1;
			boolean noSocail = false;
			// loops though the customers to see if they are in there with thier ssn
			for(Customer entry: customers)
			{
				if(entry.getSSN().equals(ssn))
				{
					// if so it sets has social to true and sets the first index equal to 
					// its place in the list
					noSocail = true;
					System.out.println("￼Customer removed – SSN" + entry.getSSN() 
					+ ", Customer: " + entry.getName());
					index = customers.indexOf(entry);
				}
			}
			//then loops though accounts
			for(Account entry: account)
			{
				// checks to see if the ssn is in the accounts
				if(entry.getSsn().equals(ssn))
				{
					// if so it sets has social to true and sets the second index equal to 
					// its place in the list
					noSocail = true;
					System.out.println("\tAccount closed - Number: " + entry.getNum() 
					+ ", Balance: " + df.format(entry.getBalance()));
					index1 = account.indexOf(entry);
				}
			}
			// if has social is true it removes those places in the list and 
			//brings the counter down by one
			if(noSocail){
				customers.remove(index);
				numOfCustomers--;
				account.remove(index1);
			}
			else if(!noSocail)
			{
				System.out.println("Customer remove failed. SSN does not exist.");
				return false;
			}

			return false;
		}
}