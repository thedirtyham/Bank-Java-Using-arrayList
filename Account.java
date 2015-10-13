/** Title: Account.java
 * 
 * 	Abstract: this program is for the use of the bank class, a bunch of set and get functions 
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


public class Account {
    private String ssn;
    private int accNum;
    private int accType;
    private double balance;
    
    Account(int accNum)
    {
    	this.accNum = accNum;
    }
    Account(String ssn, int accNum, int accType, double balance)
    {
    	this.ssn = ssn;
    	this.accNum = accNum;
    	this.accType = accType;
    	this.balance = balance;
    }

    public void deposit(double balance)
    {
    	this.balance += balance;
    }
    
    public void withdraw(double amount)
    {
    	this.balance -= amount;
    }
    public double getBalance()
    {
    	return this.balance;
    }
    
    public int getNum()
    {
    	return this.accNum;
    }
    
    public String getSsn()
    {
    	return this.ssn;
    }
    
    public double getType()
    {
    	return this.accType;
    }
    public boolean equals(Object obj)
    {
    	if(obj instanceof Account)
    	{
    		Account another = (Account) obj;
    		
    	return this.accNum == another.accNum;
    	}
    	return false;
    }
    public String toString()
    {
    	return this.accNum + ": $" + this.balance;
    }
}
