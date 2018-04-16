package model;

public interface PathPosition extends Position {
	public PathPosition nextPos();
	//"size" of the position
	//(the distance that needs to be crossed by the unit 
	//to reach the next position)
	public int maxDist();
}
