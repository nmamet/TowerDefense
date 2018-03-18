package model;

public interface Cell<T extends Position> {
	public PositioningSystem<T> getSystem();
	public T pos();
}
