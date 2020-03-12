package ch.linvogel.rsg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class RandomProductGenerator {

	private static File thingsFile = new File("data/things.txt");
	private static File moviesFile = new File("data/movies.txt");
	
	private static String[] productNames = {};
	
	private static Random random = new Random();
	
	static {
		LinkedList<String> tmpSerialNumbers = new LinkedList<String>();
		
		File dir = new File("data");
		
		if (!dir.exists()) dir.mkdirs();
		try {
			if (!thingsFile.exists()) thingsFile.createNewFile();
			if (!moviesFile.exists()) moviesFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			Scanner pn1 = new Scanner(thingsFile);
			
			while (pn1.hasNextLine()) {
				String t = pn1.nextLine();
				if (!t.equals("")) tmpSerialNumbers.add(t);
			}
			
			pn1.close();
			
			Scanner pn2 = new Scanner(moviesFile);
			
			while (pn2.hasNextLine()) {
				String t = pn2.nextLine();
				if (!t.equals("")) tmpSerialNumbers.add(t);
			}
			
			pn2.close();
			
			productNames = (String[])tmpSerialNumbers.toArray(new String[0]);
		} catch (FileNotFoundException e) {}
		
	}
	
	public static LinkedList<Product> generateProducts(int i, long seed) {
		i = Math.min(i, productNames.length);
		LinkedList<Product> out = new LinkedList<Product>();
		
		String[] arr = Arrays.copyOf(productNames, i);
		Random r = new Random(seed);
		for (int j = 0; j < arr.length; j++) {
			int k = r.nextInt(arr.length);
			String a = arr[j];
			String b = arr[k];
			arr[j] = b;
			arr[k] = a;
		}
		
		for (String name : arr) {
			int price = Math.min((int) (2500 + Math.round(random.nextGaussian()*500)), 50);
			int stock = 1 + r.nextInt(50) + Math.min((int) (15 + Math.round(random.nextGaussian()*50)), 0);
			out.add(new Product(name, price, stock));
		}
		
		return out;
	}
	
}
