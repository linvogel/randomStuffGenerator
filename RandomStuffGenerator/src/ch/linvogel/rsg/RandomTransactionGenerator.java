package ch.linvogel.rsg;

import java.util.Random;

public class RandomTransactionGenerator {
	
	private static Random random = new Random();
	
	public static Transaction generateTransaction(Employee[] emp, Customer[] cust, Product[] prod) {
		int items = random.nextInt((int)Math.round(Math.abs(random.nextGaussian()*30) + 1));
		Product[] p = new Product[items];
		Employee e = emp[random.nextInt(emp.length)];
		Customer c = cust[random.nextInt(cust.length)];
		for (int i = 0; i < items; i++) {
			p[i] = prod[random.nextInt(prod.length)];
		}
		
		return new Transaction(e, c, p);
	}
	
}
