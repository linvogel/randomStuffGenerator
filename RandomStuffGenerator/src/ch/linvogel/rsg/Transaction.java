package ch.linvogel.rsg;

import java.util.HashMap;
import java.util.Map.Entry;

public class Transaction {
	
	public String id;
	public Employee employee;
	public Customer customer;
	public HashMap<Product, Integer> products = new HashMap<Product, Integer>();
	public int time;
	
	public Transaction(Employee e, Customer c, Product[] products) {
		this.id = UUID.v4();
		this.employee = e;
		this.customer = c;
		this.time = (int) Math.round(Math.random() * 1000);
		for (Product p : products) {
			int count = 1;
			if (this.products.containsKey(p)) count = this.products.get(p) + 1;
			this.products.put(p, count);
		}
	}
	
	public String getHeaderLine() {
		return this.id + "," + this.employee.id + "," + this.customer.id + "," + this.time;
	}
	
	public String getData() {
		StringBuilder out = new StringBuilder();
		for (Entry<Product, Integer> e : products.entrySet()) {
			out.append(id).append(",").append(e.getKey().id).append(",").append(e.getValue()).append("\n");
		}
		return out.toString();
	}
}
