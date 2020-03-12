package ch.linvogel.rsg;

import java.util.LinkedList;

public class RandomCustomerGenerator {
	
	public static LinkedList<Customer> generateRandomCustomers(int i, long seed) {
		RandomNameGenerator.setSeed(seed);
		LinkedList<Customer> out = new LinkedList<Customer>();
		for (int j = 0; j < i; j++) {
			String[] name = RandomNameGenerator.generateFirstAndLastName().split(" ");
			out.add(new Customer(name[0], name[1]));
		}
		return out;
	}

}
