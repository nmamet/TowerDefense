package model;

public interface Field {
	public Cell getCell(Position p) throws OutOfFieldException;
	public PositionningSystem getSystem();
}
