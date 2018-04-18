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
	
	public Turret(TwoDimCoordinate pos, int range, int attackSpeed, int damage){
		//System.out.println("turret placed");
		this.pos = pos;
		this.range = range;
		this.attackSpeed = attackSpeed;
		this.damage = damage;
		refreshRange();
		//System.out.println(squaresInRange.size());
	}
	
	private void refreshRange(){
		squaresInRange = new HashSet<Square>((range*range+(range+1)*(range+1))*4/3+1);
		for(int i = -range;i<=range;i++){
			for(int j = -range;j<=range;j++){
				int s = i+j;
				int d = i-j;
				if(s<=range && s>=-range && d <=range && d>=-range){
					try {
						
						//System.out.println("bob1");
						squaresInRange.add(t.getCell(new TwoDimCoordinate(pos.row()+i, pos.column()+j)));
						//System.out.println("position : row "+(pos.row()+i)+", column "+(pos.column()+j));
						//System.out.println("bob2");
					} catch (OutOfFieldException e) {
						//si la case est en dehors du terrain, on ne l'ajoute pas -> rien à faire
					}
				}
			}
		}
	}
	
	@Override
	public void attack(){
		
		MovingTarget<Path2DCoord> cible = t.getTarget(squaresInRange);
		if(cible != null){
			//System.out.println("turret attack");
			t.addAttack((Attack) new LaserBeam(pos, cible.getPos(), cible.getDistance()));
			cible.takeDamage(damage);
		}
	}

	@Override
	public int getAttackPeriod() {
		return attackSpeed;
	}
	
	@Override
	public TwoDimCoordinate getPos(){
		return pos;
	}
	
}
