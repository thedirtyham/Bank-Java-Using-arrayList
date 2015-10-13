/** Title: Customer.java
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


public class Customer {
	private String name;
	private String ssn;
	private String adress;
	private int zip;
	
	public Customer (String name, String adress, int zip, String ssn) {
		this.name = name;
		this.ssn = ssn;
		this.adress = adress;
		this.zip = zip;
	}
	public Customer(String ssn)
	{
		this.ssn = ssn;
		
	}
	
	public int getZip()
	{
		return this.zip;
	}
	public String getSSN()
	{
		return this.ssn;
	}
	
	public String getAdress()
	{
		return this.adress;
	}
	
	public String getName()
	{
		return this.name;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof Customer)
    	{
    		Customer another = (Customer) obj;
    		
    	return this.ssn.equals(another.ssn);
    	}
		return false;
	}
	public String toString()
	{			
		return this.name + ": " + this.ssn;

	}
}
