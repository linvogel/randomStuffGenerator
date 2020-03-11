package ch.linvogel.rsg;

import java.util.Random;

public class UUID {
	
	private static Random random = new Random();
	
	public static String v4() {
		int i1 = (int) (random.nextLong() & 0xffffffff);
		int i2 = random.nextInt() & 0xffff;
		int i3 = random.nextInt() & 0xfff;
		int i4 = random.nextInt() & 0xfff;
		long i5 = (random.nextLong() & 0xffffffffffffL);
		int r = random.nextInt() & 0x3 | 0x8;
		
		return String.format("%08x-%04x-4%03x-%1x%03x-%012x", i1, i2, i3, r, i4, i5);
	}
	
}
