package ch.linvogel.rsg;

import java.util.LinkedList;

public class RandomEmployeeGenerator {
	
	public static LinkedList<Employee> generateEmployees(int i, long seed) {
		RandomNameGenerator.setSeed(seed);
		LinkedList<Employee> out = new LinkedList<Employee>();
		for (int j = 0; j < i; j++) {
			String[] name = RandomNameGenerator.generateFirstAndLastName().split(" ");
			out.add(new Employee(name[0], name[1], 4500 + (int) Math.round(Math.random() * 8000)));
		}
		return out;
	}

}
