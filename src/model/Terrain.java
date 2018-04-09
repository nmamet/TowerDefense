package model;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Semaphore;

class Terrain implements Field<TwoDimCoordinate, Square> {
	private Square[][] tab;
	private TwoDimArraySystem ps;
	private ArrayList<MovingObject<Path2DCoord>> units;
	private Semaphore semUnits;
	
	
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
		units = new ArrayList<MovingObject<Path2DCoord>>();
		semUnits = new Semaphore(1);
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

	public void addUnits(Collection<MovingObject<Path2DCoord>> units) {
		this.units.addAll(units);
	}
	
	public Collection<MovingObject<Path2DCoord>> getUnits(){
		return units;
	}
	
	public void removeUnit(Unit u){
		units.remove(u);
	}
}
