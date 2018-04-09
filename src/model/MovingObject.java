package model;

public interface MovingObject<T extends PathPosition> {
	public void move();
	public T getPos();
	public int getDistance();
	public boolean isAtTheEnd();
	
	
	//autre interface séparée à faire si j'ai le temps 
	//(+propre mais pas vraiment d'interet)
	public void takeDamage(int dmg) throws UnitDeathException;
	
}
