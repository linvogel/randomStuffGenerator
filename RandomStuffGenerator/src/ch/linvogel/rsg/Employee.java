package ch.linvogel.rsg;

public class Employee {
	
	public String id;
	public String firstName;
	public String lastName;
	public int salary;
	
	public Employee(String firstName, String lastName, int salary) {
		this.id = UUID.v4();
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}
	
	public String toCSV() {
		return id + "," + firstName + "," + lastName + "," + salary;
	}
	
}
