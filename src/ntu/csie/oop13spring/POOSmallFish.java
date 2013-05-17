package ntu.csie.oop13spring;

public class POOSmallFish extends POOPet {
	protected int opponent;
	private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	public POOSmallFish() {
		setHP(5);
		setMP(2);
		setAGI(1);
		setName("\033[38;5;10m" + POONamePool.getName() + "\033[m");
		opponent = -1;
	}
	private void getOpponent(POOArena arena) {
		POOPet[] parr = arena.getAllPets();
		if (opponent >= 0 && parr[opponent].getHP() > 0)
			return ;
		do {
			int i, n = 0;
			if (arena instanceof POOPlain) n = POORandom.getInt(((POOPlain)arena).count - 1) + 1;
			for (i = 0; i < parr.length && n > 0; i++) {
				if (parr[i].getHP() > 0 && !(this == parr[i])) {
					if (n == 1) opponent = i;
					n--;
				}
			}
		} while (opponent < 0);
		System.out.println(POOPlain.system_info + "pet " + getName() + " select a new opponent " + parr[opponent].getName());
		return ;
	}
	public final POOAction act(POOArena arena) {
		POOAction action = new POOAction();
		POOPet[] parr = arena.getAllPets();
		getOpponent(arena);
		action.dest = parr[opponent];
		if (getHP() <= 0) {
			action.skill = new POODead();
		}
		else if (action.dest.getHP() > 0) {
			int ran = POORandom.getInt(10);
			if (ran < 1) action.skill = new POOStrongSplash(); // 0.1
			else if (ran < 3) action.skill = new POONothing(); // 0.2
			else action.skill = new POOSplash(); // 0.7
		}
		return action;
	}
	public final POOCoordinate move(POOArena arena) {
		POOCoordinate tmp = arena.getPosition(this);
		POOXY cor = new POOXY(tmp.x, tmp.y);
		if (arena instanceof POOPlain) {
			POOPlain plain = (POOPlain) arena;
			int dir;
			for (int i = 0; i < getAGI(); i++) {
				int counter = 0;
				do {
					counter++;
					dir = POORandom.getInt(dx.length);
				} while ( counter < 10000 && ((cor.x + dx[dir] < 0 || cor.x + dx[dir] >= plain.x_len) || (cor.y + dy[dir] < 0 || cor.y + dy[dir] >= plain.y_len) || plain.map[cor.x + dx[dir]][cor.y + dy[dir]] != -1) );
				if (counter < 10000) {
					cor.x += dx[dir];
					cor.y += dy[dir];
				}
			}
		}
		return cor;
	}
}
