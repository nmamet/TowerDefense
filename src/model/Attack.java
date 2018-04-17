package model;

public interface Attack {
	public TwoDimCoordinate getOrigin();
	public TwoDimCoordinate getTarget();
	public int getDistance();
	//appel a chaque affichage; 
	//renvoie zero si l'affichage ne doit plus avoir lieu
	public int decLifeSpan();
}
