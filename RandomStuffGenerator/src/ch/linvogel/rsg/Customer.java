package ch.linvogel.rsg;

public class Customer {
	
	public String id;
	public String firstName;
	public String lastName;
	
	public Customer(String firstName, String lastName) {
		this.id = UUID.v4();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String toCSV() {
		return new StringBuilder(5).append(id).append(",").append(firstName).append(",").append(lastName).toString();
	}
	
}
