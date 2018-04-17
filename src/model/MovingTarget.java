package model;

public interface MovingTarget<T extends PathPosition> {
	public void move();
	public T getPos();
	public int getDistance();
	public boolean isAtTheEnd();
	public boolean isDead();
	
	public void takeDamage(int dmg);
	public float getHealthPercentage();
	
}
