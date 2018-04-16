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
	//(java doesn't allow me to get a superype of T that is a position so it's safer to take t here)
	public AttackingObject placeTurret(T pos);
}
