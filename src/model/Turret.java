package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

class Turret implements AttackingObject{
	private static Terrain t;
	private TwoDimCoordinate pos;
	private int range;
	private HashSet<Square> squaresInRange;
	private int attackSpeed;
	private int damage;
	
	public static void setField(Terrain t){
		Turret.t = t;
	}
	
	public Turret(TwoDimCoordinate pos, int range){
		this.pos = pos;
		this.range = range;
		refreshRange();
	}
	
	private void refreshRange(){
		squaresInRange = new HashSet<Square>((range*range+(range+1)*(range+1))*4/3+1);
		for(int i = -range;i<=range;i++){
			for(int j = -range;j<=range;j++){
				int s = i+j;
				int d = i-j;
				if(s<=range && s>=-range && d <=range && d>=-range){
					try {
						squaresInRange.add(t.getCell(new TwoDimCoordinate(pos.row()+i, pos.column()+j)));
					} catch (OutOfFieldException e) {
						//si la case est en dehors du terrain, on ne l'ajoute pas -> rien à faire
					}
				}
			}
		}
	}
	
	private MovingObject<Path2DCoord> cible(){
		boolean found = false;
		Collection<MovingObject<Path2DCoord>> units = t.getUnits();
		Iterator<MovingObject<Path2DCoord>> i = units.iterator();
		MovingObject<Path2DCoord> tmp = null;
		while(!found && i.hasNext()){
			tmp = i.next();
			found = squaresInRange.contains(tmp);
		}
		if(!found){
			tmp = null;
		}
		return tmp;
	}

	@Override
	public void attack() throws UnitDeathException {
		//une seule tour peut attaquer a la fois
		synchronized(Turret.class){
			MovingObject<Path2DCoord> cible = cible();
			if(cible != null){
				cible.takeDamage(damage);
			}
		}
	}

	@Override
	public int getAttackPeriod() {
		return attackSpeed;
	}
	
}
