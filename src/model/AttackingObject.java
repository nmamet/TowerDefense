package model;

public interface AttackingObject {
	
	//Ordonne une attaque
	public void attack();
	
	//renvoie le délai entre deux attaques 
	//(valeur arbitraire, la signification est decide par l'interface)
	public int getAttackPeriod();
	
	public TwoDimCoordinate getPos();
}
