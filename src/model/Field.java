package model;

public interface Field<T extends Position, C extends Cell<T>> {
	public C getCell(T p) throws OutOfFieldException;
	public PositioningSystem<T> getSystem();
}
