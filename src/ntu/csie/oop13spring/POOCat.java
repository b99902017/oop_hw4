package ntu.csie.oop13spring;

public class POOCat extends POOPet {
	protected int opponent;
	private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	public POOCat() {
		setHP(7);
		setMP(3);
		setAGI(2);
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
			if (getMP() > 0) {
				if (ran < 2) {
					ran = POORandom.getInt(10);
					action.skill = new POONothing(); // 0.2
					if (ran < 8 && getMP() >= 2) {
						action.skill = new POOStrongStrike(); // 0.16
					}
				}
				else if (ran < 8) {
					action.skill = new POOStrike(); // 0.6
				}
				else {
					action.skill = new POOBasicRecover(); // 0.2
					action.dest = this;
				}
			}
			else {
				if (ran < 3) action.skill = new POONothing(); // 0.3
				else if (ran < 6) {
					action.skill = new POOBasicRecover(); // 0.3
					action.dest = this;
				}
				else action.skill = new POOStrike(); // 0.4
			}
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
