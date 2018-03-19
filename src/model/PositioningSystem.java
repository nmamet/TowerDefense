package model;

public interface PositioningSystem<T extends Position> {
	
	public boolean isInSystem(T p);
	public Iterable<T> neighbours(T p);
	
}
