package ntu.csie.oop13spring;

import java.util.*;

public class POOPlain extends POOArena {
	private static Scanner scanner = new Scanner(System.in);
	private static int default_x = 10, default_y = 10;
	protected static String system_info = "\033[38;5;11m[System info]\033[m ";
	
	private int round;
	protected int count;
	
	protected int x_len, y_len;
	private ArrayList<POOXY> petpos = new ArrayList<POOXY>(0);
	protected int[][] map;
	
	public POOPlain() {
		round = 0;
		count = 0;
		
		x_len = default_x;
		y_len = default_y;
		map = new int[x_len][y_len];
		for (int i = 0; i < x_len; i++)
			for (int j = 0; j < y_len; j++)
				map[i][j] = -1;
		
		System.out.println(system_info + "Generate a plain arena with x = " + x_len + ", y = " + y_len);
	}
	public void addPet(POOPet p) {
		POOXY cor;
		do {
			cor = new POOXY(POORandom.getInt(x_len), POORandom.getInt(y_len));
		} while (map[cor.x][cor.y] != -1);
		
		super.addPet(p);
		petpos.add(cor);

		map[cor.x][cor.y] = petpos.size() - 1;
		count++;
		
		System.out.println(system_info + "Add a new pet called " + p.getName() + " at " + cor + ".");
		System.out.println(system_info + "There is/are " + count + " pet(s).");
	}
	public boolean fight() {
		if (round == 0) {
			System.out.println("");
			System.out.println(this);
		}
		round++;
		System.out.println("");
		System.out.println("\033[38;5;14mRound " + round + "\033[m");
		System.out.println("");
		int i;
		POOXY cor;
		POOAction action;
		POOPet[] parr = super.getAllPets();
		for (i = 0; i < parr.length; i++) {
			if (parr[i].getHP() > 0) {
				cor = (POOXY)parr[i].move(this);
				System.out.print(system_info + "pet " + parr[i].getName() + " moves ");
				System.out.println("from " + petpos.get(i) + " to " + cor + ".");
				map[petpos.get(i).x][petpos.get(i).y] = -1;
				map[cor.x][cor.y]= i; 
				petpos.set(i, cor);
				
				action = parr[i].act(this);
				System.out.print(POOPlain.system_info + "pet " + parr[i].getName() + " take actions: ");
				action.skill.act(action.dest);
				
				int op = -1;
				if (parr[i] instanceof POOSmallFish) op = ((POOSmallFish)parr[i]).opponent;
				else if (parr[i] instanceof POOCat) op = ((POOCat)parr[i]).opponent;
				if (Utility.DeadCheck(action.dest) == true) {
					System.out.println(POOPlain.system_info + "pet " + action.dest.getName() + " is \033[38;5;172mdead\033[m.");
					map[petpos.get(op).x][petpos.get(op).y] = -1;
					petpos.set(op, null);
					count--;
					System.out.println(system_info + "There remains " + count + " pet(s).");
				}
				if (action.skill instanceof POOStrongStrike) {
					int mp = parr[i].getMP();
					parr[i].setMP(mp - 2);
					System.out.println(system_info + parr[i].getName() + " remains " + parr[i].getMP() + " MP.");
				}
				else if (action.skill instanceof POODead) {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
				System.out.println("");
			}
		}
		if (count == 1) {
			System.out.println(system_info + "Game end.");
			for (i = 0; i < parr.length; i++)
				if (parr[i].getHP() > 0) {
					System.out.println(system_info + "pet " + parr[i].getName() + " win the game!!!!");
					return false;
				}
		}
		return count > 1;
	}
	public void show() {
		System.out.println("");
		System.out.println("\033[38;5;14mRound " + round + " result:\033[m");
		System.out.println("");
		System.out.println(this);
		int i;
		POOPet[] parr = super.getAllPets();
		for (i = 0; i < petpos.size(); i++) {
			if (parr[i].getHP() > 0) {
				System.out.println("");
				System.out.println("pet" + i);
				System.out.println("Name = " + parr[i].getName() + ", Type = " + parr[i].getClass().getName());
				System.out.println("HP = " + parr[i].getHP() + ", MP = " + parr[i].getMP() + ", AGI = " + parr[i].getAGI());
				System.out.println("Coordinate = " + petpos.get(i));
			}
		}
		
		System.out.println("");
		System.out.println("\033[38;5;15m<ENTER to continue>\033[m");
		scanner.nextLine();
	}
	public POOCoordinate getPosition(POOPet p) {
		int i;
		POOPet[] parr = super.getAllPets();
		for (i = 0; i < parr.length; i++) {
			if (parr[i].equals(p))
				return petpos.get(i);
		}
		return null;
	}
	public String toString() {
		String str = "";
		int i, j;
		str += "\033[38;5;196mCurrent board:\033[m\n";
		for (j = y_len - 1; j >= 0; j--) {
			for (i = 0; i < x_len; i++) {
				if (i > 0) str += ' ';
				if (map[i][j] == -1) str += "....";
				else str += "pet" + map[i][j];
			}
			if (j > 0) str += '\n';
		}
		return str;
	}
}