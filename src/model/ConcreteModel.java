package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Semaphore;

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
		t = Terrain.buildTerrain(ps,path);
		Turret.setField(t);
	}

	@Override
	public Path<Path2DCoord> getPath() {
		return path;
	}

	@Override
	public Collection<MovingTarget<Path2DCoord>> launchWave() {
		ArrayList<MovingTarget<Path2DCoord>> l = new ArrayList<MovingTarget<Path2DCoord>>();
		for(int i = 0; i<10; i++) {
			l.add(new Unit(path.startingPos(), 10));
		}
		return l;
	}

	@Override
	public void addUnits(Collection<MovingTarget<Path2DCoord>> units) {
		t.addUnits(units);
	}

	@Override
	public void removeUnit(MovingTarget<Path2DCoord> unit) {
		t.removeUnit(unit);
		
	}
	
	@Override
	public AttackingObject placeTurret(Path2DCoord pos){
		AttackingObject ret;
		
		try {
			ret = t.getCell(pos).placeTurret();
		} catch (OutOfFieldException e) {
			System.out.println("erreur : la tour ne peut etre placee hors du terrain");
			e.printStackTrace();
			System.exit(1);
			ret = null;
		}
		return ret;
	}
	
}
