package model;

public class TwoDimCoordinate implements Position{
	private int row;
	private int column;
	
	public TwoDimCoordinate(int r, int c){
		row = r;
		column = c;
	}
	
	public int column() {return column;}
	public int row() {return row;}
	public TwoDimCoordinate north() {
		return new TwoDimCoordinate(row-1, column);
	}
	public TwoDimCoordinate south() {
		return new TwoDimCoordinate(row+1, column);
	}
	public TwoDimCoordinate west() {
		return new TwoDimCoordinate(row, column-1);
	}
	public TwoDimCoordinate east() {
		return new TwoDimCoordinate(row, column+1);
	}
	
	@Override
	public final boolean equals(Object o) {
		if(o==null) {
			return false;
		}
		if(this==o) {
			return true;
		}
		if(!(o instanceof TwoDimCoordinate)) {
			return false;
		}
		TwoDimCoordinate pos = (TwoDimCoordinate) o;
		return (row == pos.row && column == pos.column);
	}
}
