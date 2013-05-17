package ntu.csie.oop13spring;

import java.util.Random;

public final class POORandom {
	private static Random random = new Random(); 
	protected static final int getInt(int i) {
		return random.nextInt(i);
	}
}
