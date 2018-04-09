package model;

import java.util.Collection;

public interface Model<T extends PathPosition> {
	
	public PositioningSystem<? super T> getPosSystem();
	public Path<T> getPath();
	
	//gets the units to be sent in the next wave
	public Collection<MovingObject<T>> launchWave();
	//actually adds the units to the field;
	//units can't be "seen" by the turrets if they aren't added
	public void addUnits(Collection<MovingObject<T>> units);
}
