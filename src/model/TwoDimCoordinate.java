package model;

public class TwoDimCoordinate implements Position{
	private int row;
	private int column;
	
	public TwoDimCoordinate(int r, int c){
		row = r;
		column = c;
	}
	
	public int column(){return column;}
	public int row(){return row;}
}
