package ch.linvogel.rsg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class RandomNameGenerator {
	
	private static File firstNamesFile = new File("data/FirstNames.txt");
	private static File lastNamesFile = new File("data/LastNames.txt");
	
	private static String[] firstNames = {};
	private static String[] lastNames = {};
	
	private static Random random = new Random();
	
	static {
		LinkedList<String> tmpFirstNames = new LinkedList<String>();
		LinkedList<String> tmpLastNames = new LinkedList<String>();
		
		File dir = new File("data");
		
		if (!dir.exists()) dir.mkdirs();
		try {
			if (!firstNamesFile.exists()) firstNamesFile.createNewFile();
			if (!lastNamesFile.exists()) lastNamesFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			Scanner fn = new Scanner(firstNamesFile);
			
			while (fn.hasNextLine()) {
				String t = fn.nextLine();
				if (!t.equals("")) tmpFirstNames.add(t);
			}
			
			fn.close();
			
			firstNames = (String[])tmpFirstNames.toArray(new String[0]);
		} catch (FileNotFoundException e) {}
		
		try {
			Scanner ln = new Scanner(lastNamesFile);
			
			while (ln.hasNextLine()) {
				String t = ln.nextLine();
				if (!t.equals("")) tmpLastNames.add(t);
			}
			
			ln.close();

			lastNames = (String[])tmpLastNames.toArray(new String[0]);
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
	
	public static void addCombinedNames(File input) {
		try {
			Scanner scanner = new Scanner(input);

			LinkedList<String> tmpFirstNames = new LinkedList<String>();
			LinkedList<String> tmpLastNames = new LinkedList<String>();

			for (String s : firstNames) tmpFirstNames.add(s);
			for (String s : lastNames) tmpLastNames.add(s);
			
			String line = null;
			while (scanner.hasNextLine())
				if (!(line = scanner.nextLine()).equals("")) {
				String[] l = line.split(" ");
				if (!contains(tmpFirstNames, l[0])) tmpFirstNames.add(l[0]);
				if (!contains(tmpLastNames, l[1])) tmpLastNames.add(l[1]);
			}
			
			scanner.close();

			firstNames = (String[])tmpFirstNames.toArray(new String[0]);
			lastNames = (String[])tmpLastNames.toArray(new String[0]);

			Arrays.sort(firstNames);
			Arrays.sort(lastNames);

			if (!firstNamesFile.exists()) firstNamesFile.createNewFile();
			if (!lastNamesFile.exists()) lastNamesFile.createNewFile();

			PrintWriter f = new PrintWriter(firstNamesFile);
			PrintWriter l = new PrintWriter(lastNamesFile);
			
			printFirstNames(f);
			printLastNames(l);
			
			f.flush();
			f.close();
			
			l.flush();
			l.close();
			
		} catch (IOException e) {
			System.err.println("ERROR: File not found!");
		}
	}
	
	public static void printFirstNames(PrintWriter out) {
		for (String s : firstNames) out.println(s);
	}
	
	public static void printLastNames(PrintWriter out) {
		for (String s : lastNames) out.println(s);
	}
	
	public static String generateFirstAndLastName() {
		int firstIndex = random.nextInt(firstNames.length);
		int lastIndex = random.nextInt(lastNames.length);
		
		return new StringBuilder(3).append(firstNames[firstIndex]).append(" ").append(lastNames[lastIndex]).toString();
	}

	public static void printDataSize() {
		System.out.println("First Names: " + firstNames.length + ", Last Names: " + lastNames.length);
	}
}
