package model;

class Terrain implements Field<TwoDimCoordinate, Square> {
	private Square[][] tab;
	private TwoDimArraySystem ps;
	
	
	private Terrain(TwoDimArraySystem ps){
		this.ps = ps;
		int nbRow = ps.nbOfRows();
		int nbCol = ps.nbOfColumns();
		tab = new Square[nbRow][];
		for(int i = 0;i<nbRow;i++){
			tab[i] = new Square[nbCol];
			for(int j =0;j<nbCol;j++){
				tab[i][j] = new Square(ps, new TwoDimCoordinate(i, j));
			}
		}
	}
	
	public static Terrain buildTerrain(TwoDimArraySystem ps){
		if(ps.nbOfRows() <= 0 || ps.nbOfColumns() <= 0){
			return null;
		}
		Terrain t = new Terrain(ps);
		return t;
	}
	
	@Override
	public Square getCell(TwoDimCoordinate c) throws OutOfFieldException {
		if(ps.isInSystem(c)){
			return tab[c.row()][c.column()];
		} else {
			throw new OutOfFieldException();
		}
	}

	@Override
	public PositioningSystem<TwoDimCoordinate> getSystem() {
		return ps;
	}
}
