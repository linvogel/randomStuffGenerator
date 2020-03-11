package ch.linvogel.rsg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class RandomSerialNumberGenerator {
	
	private static File serialNumbersFile = new File("data/SerialNumbers.txt");
	
	private static String[] serialNumbers = {};
	
	private static Random random = new Random();
	
	static {
		LinkedList<String> tmpSerialNumbers = new LinkedList<String>();
		
		File dir = new File("data");
		
		if (!dir.exists()) dir.mkdirs();
		try {
			if (!serialNumbersFile.exists()) serialNumbersFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			Scanner fn = new Scanner(serialNumbersFile);
			
			while (fn.hasNextLine()) {
				String t = fn.nextLine();
				if (!t.equals("")) tmpSerialNumbers.add(t);
			}
			
			fn.close();
			
			serialNumbers = (String[])tmpSerialNumbers.toArray(new String[0]);
		} catch (FileNotFoundException e) {}
		
	}
	
	private static boolean contains(LinkedList<String> arr, String s) {
		for (String e : arr)
			if (e.equals(s)) return true;
		return false;
	}
	
	public static void setSeed(long seed) {
		random.setSeed(seed);
	}
	
	public static void addSerialNumbers(File input) {
		try {
			Scanner scanner = new Scanner(input);

			LinkedList<String> tmpSerialNumbers = new LinkedList<String>();

			for (String s : serialNumbers) tmpSerialNumbers.add(s);
			
			String line = null;
			while (scanner.hasNextLine())
				if (!(line = scanner.nextLine()).equals("")) {
				String[] l = line.split(" ");
				if (!contains(tmpSerialNumbers, l[0])) tmpSerialNumbers.add(l[0]);
			}
			
			scanner.close();

			serialNumbers = (String[])tmpSerialNumbers.toArray(new String[0]);

			if (!serialNumbersFile.exists()) serialNumbersFile.createNewFile();

			PrintWriter f = new PrintWriter(serialNumbersFile);
			
			printSerialNumbers(f);
			
			f.flush();
			f.close();
			
			
		} catch (IOException e) {
			System.err.println("ERROR: File not found!");
		}
	}
	
	public static void printSerialNumbers(PrintWriter out) {
		for (String s : serialNumbers) out.println(s);
	}
	
	public static String generateSerialNumber() {
		int index = random.nextInt(serialNumbers.length);
		
		return serialNumbers[index];
	}

	public static void printDataSize() {
		System.out.println("Serial Numbers: " + serialNumbers.length);
	}
}
