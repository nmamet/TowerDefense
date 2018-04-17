package model;

import java.util.Collection;

public interface Model<T extends PathPosition> {
	
	public PositioningSystem<? super T> getPosSystem();
	public Path<T> getPath();
	
	//gets the units to be sent in the next wave
	public Collection<MovingTarget<T>> launchWave();
	//actually adds the units to the field;
	//units can't be "seen" by the turrets if they aren't added
	public void addUnits(Collection<MovingTarget<T>> units);
	//remove an unit from the field 
	//(usually on death or end of the path)
	public void removeUnit(MovingTarget<T> unit);
	//place a turret a the specified position
	//(java doesn't allow me to get a supertype of T that is a position so it's safer to take T here)
	public AttackingObject placeTurret(T pos);
	//get all the attacks since last time this function was called
	//and clean the buffer
	public Collection<Attack> getAllAttacks();
	//gives the gold for a kill
	public void unitKill();
	//take back some gold
	public void unitLeak() throws DefeatException;
	//generate gold based on gold owned
	public void income();
	
	public int getGold();
}
