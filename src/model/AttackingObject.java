package model;

public interface AttackingObject {
	
	//Ordonne une attaque
	public void attack() throws UnitDeathException;
	
	//renvoie le délai entre deux attaques
	public int getAttackPeriod();
}
