package model;

public interface MovingObject<T extends PathPosition> {
	public void move();
	public T getPos();
	public int getDistance();
}
