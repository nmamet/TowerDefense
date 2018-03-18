package model;

import java.util.ArrayList;
import java.util.Collection;

import ihm.CyclingPathException;

public class ConcreteModel implements Model<Path2DCoord>{
	private TwoDimArraySystem ps;
	private Terrain t;
	private ConcretePath path;
	
	public TwoDimArraySystem getPosSystem(){
		return ps;
	}
	
	public int nbOfRows() {
		return ps.nbOfRows();
	}
	
	public int nbOfColumns() {
		return ps.nbOfColumns();
	}
	
	public ConcreteModel(int nbRow, int nbCol, ArrayList<TwoDimCoordinate> path) throws CyclingPathException {
		ps = new TwoDimArraySystem(nbRow, nbCol);
		this.path = ConcretePath.buildPath(ps, path);
		t = Terrain.buildTerrain(ps);
	}

	@Override
	public Path<Path2DCoord> getPath() {
		return path;
	}

	@Override
	public Collection<Unit> launchWave() {
		ArrayList<Unit> l = new ArrayList<Unit>();
		for(int i = 0; i<10; i++) {
			l.add(new Unit(path.startingPos(), i));
		}
		return l;
	}
}
