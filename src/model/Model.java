package model;

import java.util.Collection;

public interface Model<T extends PathPosition> {
	
	public PositioningSystem<? super T> getPosSystem();
	public Path<T> getPath();
	public Collection<? extends MovingObject<T>> launchWave();
}
