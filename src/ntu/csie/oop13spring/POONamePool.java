package ntu.csie.oop13spring;

import java.io.*;
import java.util.*;

public final class POONamePool {
	private static String[] names = makeNameList();
	private static int[] check = makeCheck();
	protected static int[] makeCheck() {
		int[] tmp = new int[names.length];
		for (int i = 0; i < tmp.length; i++)
			tmp[i] = 0;
		return tmp;
	}
	protected static String[] makeNameList() {
		ArrayList<String> tmp = new ArrayList<String>(0);
		File file = new File("name.txt");
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext() == true) {
				String name = scanner.next();
				tmp.add(name);
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		String[] str = new String[0];
		return tmp.toArray(str);
	}
	public static String getName() {
		int res;
		do {
			res = POORandom.getInt(names.length);
		} while (check[res] != 0);
		check[res] = 1;
		return names[res];
	}
}
