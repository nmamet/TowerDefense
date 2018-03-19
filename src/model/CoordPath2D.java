package model;

public class CoordPath2D extends TwoDimCoordinate{
	
	private CoordPath2D nextPos;
	
	public CoordPath2D(TwoDimCoordinate c){
		super(c.row(), c.column());
	}
	
	public void setNextPos(CoordPath2D pos){
		nextPos = pos;
	}
}
