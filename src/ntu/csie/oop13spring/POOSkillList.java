package ntu.csie.oop13spring;

public class POOSkillList {}

final class Utility {
	protected static int min(int x, int y) {
		if (x < y) return x;
		return y;
	}
	protected static int max(int x, int y) {
		if (x > y) return x;
		return y;
	}
	protected static boolean DeadCheck(POOPet pet) {
		return pet.getHP() == 0;
	}
}

final class POONothing extends POOSkill {
	public void act(POOPet pet) {
		System.out.println("do \033[38;5;12mnothing\033[m.");
		return ;
	}
}

final class POODead extends POOSkill {
	public void act(POOPet pet) {
		System.out.println("\033[38;5;172mdead\033[m.");
		return ;
	}
}

final class POOSplash extends POOSkill {
	public void act(POOPet pet) {
		int hp = pet.getHP();
		int set_hp = Utility.max(0, hp - 1);
		pet.setHP(set_hp);
		System.out.println("\033[38;5;12msplash\033[m to " + pet.getName() + " and it \033[38;5;196m-1\033[m HP.");
		System.out.println(POOPlain.system_info + pet.getName() + " remains " + pet.getHP() + " HP.");
		return ;
	}
}

final class POOStrongSplash extends POOSkill {
	public void act(POOPet pet) {
		int hp = pet.getHP();
		int set_hp = Utility.max(0, hp - 2);
		pet.setHP(set_hp);
		System.out.println("\033[38;5;12mstrong splash\033[m to " + pet.getName() + " and it \033[38;5;196m-2\033[m HP.");
		System.out.println(POOPlain.system_info + pet.getName() + " remains " + pet.getHP() + " HP.");
		return ;
	}
}

final class POOStrike extends POOSkill {
	public void act(POOPet pet) {
		int hp = pet.getHP();
		int set_hp = Utility.max(0, hp - 1);
		pet.setHP(set_hp);
		System.out.println("\033[38;5;12mstrike\033[m to " + pet.getName() + " and it \033[38;5;196m-1\033[m HP.");
		System.out.println(POOPlain.system_info + pet.getName() + " remains " + pet.getHP() + " HP.");
		return ;
	}
}

final class POOStrongStrike extends POOSkill {
	public void act(POOPet pet) {
		int hp = pet.getHP();
		int set_hp = Utility.max(0, hp - 3);
		pet.setHP(set_hp);
		System.out.println("\033[38;5;12mstrong strike\033[m to " + pet.getName() + " and it \033[38;5;196m-3\033[m HP.");
		System.out.println(POOPlain.system_info + pet.getName() + " remains " + pet.getHP() + " HP.");
		return ;
	}
}

final class POOBasicRecover extends POOSkill {
	public void act(POOPet pet) {
		int mp = pet.getMP();
		int set_mp = Utility.min(1023, mp + 1);
		pet.setMP(set_mp);
		System.out.println("\033[38;5;12mrecover\033[m to " + pet.getName() + " and it \033[38;5;196m+1\033[m MP.");
		System.out.println(POOPlain.system_info + pet.getName() + " remains " + pet.getMP() + " MP.");
		return ;
	}
}
