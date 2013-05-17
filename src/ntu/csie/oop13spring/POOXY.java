package ntu.csie.oop13spring;

public class POOXY extends POOCoordinate {
	protected POOXY(int _x, int _y) {
		x = _x;
		y = _y;
	}
	public boolean equals(POOCoordinate other) {
		return x == other.x && y == other.y;
	}
	private int abs(int x) {
		if (x < 0) return -x;
		return x;
	}
	public int distance(POOCoordinate other) {
		return abs(x - other.x) + abs(y - other.y);
	}
	public String toString() {
		return "\033[38;5;13m(" + x + "," + y + ")\033[m";
	}
}
