package me.hope.asd;

public class Num {
	private int x, y, value;

	public Num(int i, int i2, int j) {
		this.x = i;
		this.y = i2;
		this.value = j;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "x=" + x + ",y=" + y + ",value=" + value;
	}
}