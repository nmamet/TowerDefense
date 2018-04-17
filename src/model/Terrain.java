package model;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

class Terrain implements Field<TwoDimCoordinate, Square> {
	private Square[][] tab;
	private TwoDimArraySystem ps;
	private ArrayList<MovingTarget<Path2DCoord>> units;
	private Semaphore semUnits;
	private ArrayList<Attack> attacks;
	private Semaphore semAttacks;
	
	
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
		units = new ArrayList<MovingTarget<Path2DCoord>>();
		semUnits = new Semaphore(1);
		attacks = new ArrayList<Attack>();
		semAttacks = new Semaphore(1);
	}
	
	public static Terrain buildTerrain(TwoDimArraySystem ps){
		if(ps.nbOfRows() <= 0 || ps.nbOfColumns() <= 0){
			return null;
		}
		Terrain t = new Terrain(ps);
		return t;
	}
	
	public static Terrain buildTerrain(TwoDimArraySystem ps, ArrayList<TwoDimCoordinate> path){
		if(ps.nbOfRows() <= 0 || ps.nbOfColumns() <= 0){
			return null;
		}
		Terrain t = new Terrain(ps);
		for(TwoDimCoordinate pos : path){
			try {
				t.getCell(pos).setCrossable(false);
			} catch (OutOfFieldException e) {
				System.out.println("Should not happen cause the path has benn checked already (buildTerrain)");
				e.printStackTrace();
				System.exit(1);
			}
		}
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

	private void lockUnitsList(){
		try {
			semUnits.acquire();
		} catch (InterruptedException e) {
			System.out.println("Thread interrompu : verrouillage unites (model)");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void releaseUnitsList(){
		semUnits.release();
	}
	
	private void lockAttacksList(){
		try {
			semAttacks.acquire();
		} catch (InterruptedException e) {
			System.out.println("Thread interrompu : verrouillage attaques (model)");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void releaseAttacksList() {
		semAttacks.release();
	}
	
	public void addUnits(Collection<MovingTarget<Path2DCoord>> units) {
		lockUnitsList();
		this.units.addAll(units);
		releaseUnitsList();
	}
	
	public MovingTarget<Path2DCoord> getTarget(HashSet<Square> squaresInRange){
		boolean found = false;
		lockUnitsList();
		Iterator<MovingTarget<Path2DCoord>> i = units.iterator();
		MovingTarget<Path2DCoord> tmp = null;
		while(!found && i.hasNext()){
			tmp = i.next();
			//System.out.println("position : row "+tmp.getPos().row()+", column "+tmp.getPos().column());
			
			try {
				found = squaresInRange.contains(getCell(tmp.getPos()));
			} catch (OutOfFieldException e) {
				System.out.println("ce n'est pas cense arriver (unite hors du terrain dans getTarget)");
				e.printStackTrace();
				System.exit(1);
			}
			//System.out.println(found);
		}
		releaseUnitsList();
		if(!found){
			tmp = null;
		}
		return tmp;
	}
	
	public void removeUnit(MovingTarget<Path2DCoord> unit) {
		lockUnitsList();
		units.remove(unit);
		releaseUnitsList();
	}

	public void addAttack(Attack laserBeam) {
		lockAttacksList();
		attacks.add(laserBeam);
		releaseAttacksList();
	}
	
	public Collection<Attack> getAllAttacks() {
		lockAttacksList();
		Collection<Attack> l = new ArrayList<Attack>(attacks);
		attacks.clear();
		releaseAttacksList();
		return l;
	}
}
