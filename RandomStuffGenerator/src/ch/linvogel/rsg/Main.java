package ch.linvogel.rsg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Employee[] e = RandomEmployeeGenerator.generateEmployees(100, System.currentTimeMillis()).toArray(new Employee[0]);
		Customer[] c = RandomCustomerGenerator.generateRandomCustomers(100, System.currentTimeMillis()+1).toArray(new Customer[0]);
		Product[] p = RandomProductGenerator.generateProducts(256, System.currentTimeMillis()+2).toArray(new Product[0]);
		Transaction[] t = new Transaction[5*1024];
		
		for (int i = 0; i < t.length; i++) {
			t[i] = RandomTransactionGenerator.generateTransaction(e, c, p);
		}
		
		File outDir = new File("out");
		if (!outDir.exists()) outDir.mkdirs();
		
		PrintWriter empOut = new PrintWriter(new File("out/employees.txt"));
		PrintWriter custOut = new PrintWriter(new File("out/customers.txt"));
		PrintWriter prodOut = new PrintWriter(new File("out/products.txt"));
		PrintWriter tHeadOut = new PrintWriter(new File("out/transactionHead.txt"));
		PrintWriter tDataOut = new PrintWriter(new File("out/transactionData.txt"));

		for (Employee emp : e) empOut.println(emp.toCSV());
		for (Customer cust : c) custOut.println(cust.toCSV());
		for (Product prod : p) prodOut.println(prod.toCSV());
		for (Transaction trans : t) {
			tHeadOut.println(trans.getHeaderLine());
			tDataOut.print(trans.getData());
		}
		
		empOut.close();
		custOut.close();
		prodOut.close();
		tHeadOut.close();
		tDataOut.close();

	}

}
