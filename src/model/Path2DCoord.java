package model;

public class Path2DCoord extends TwoDimCoordinate implements PathPosition {

	private Path2DCoord nextPos;
	private static int maxDistance = 100;
	
	public Path2DCoord(int r, int c) {
		super(r, c);
		nextPos = null;
	}
	
	public Path2DCoord(TwoDimCoordinate c) {
		super(c.row(), c.column());
		nextPos = null;
	}
	
	@Override
	public Path2DCoord nextPos() {return nextPos;}

	protected boolean setNextPos(Path2DCoord p) {
		//System.out.println("Modification du path");
		//System.out.println();
		boolean r = false;
		//System.out.println("");
		if(nextPos == null) {
			//System.out.println("redef de nextpos (ok)");
			nextPos = p;
			r = true;
		}
		return r;
	}

	@Override
	public int maxDist() {
		return maxDistance;
	}
}
