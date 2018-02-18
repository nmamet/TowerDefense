package model;

public interface Field {
	public FieldCell getCell(int row, int col) throws OutOfFieldException;
}
