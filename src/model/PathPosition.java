package model;

public interface PathPosition extends Position {
	public PathPosition nextPos();
	public int maxDist();
}
