/** Title: Transaction.java
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

public class Transaction {
	private int accountNum;
	private String type;
	private String timeStamp;
	
	public Transaction(int accountNum, String type, String date)
	{
		this.accountNum = accountNum;
		this.type = type;
		this.timeStamp = date;
	}
	
	public Transaction(int accNum)
	{
		this.accountNum = accNum;
	}

	public String getType()
	{
		return this.type;
	}

	public int getaccNum()
	{
		return this.accountNum;
	}	
	public String getDate()
	{
		return this.timeStamp;
	}
    public boolean equals(Object obj)
    {
    	if(obj instanceof Transaction)
    	{
    		Transaction another = (Transaction) obj;
    		
    	return this.accountNum == another.accountNum;
    	}
    	return false;
    }
	
	public String toString ()
	{
		return "- Account Number: "  + this.accountNum + ": " + this.type + " " 
				+ this.timeStamp ;
	}
}
