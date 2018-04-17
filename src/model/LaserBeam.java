package model;

class LaserBeam implements Attack{
	private TwoDimCoordinate origin;
	private TwoDimCoordinate target;
	private int distance;
	private int lifeSpan = 10;
	
	public LaserBeam(TwoDimCoordinate origin, TwoDimCoordinate target, int distance) {
		this.origin = origin;
		this.target = target;
		this.distance = distance;
	}

	@Override
	public TwoDimCoordinate getOrigin() {
		return origin;
	}

	@Override
	public TwoDimCoordinate getTarget() {
		return target;
	}

	@Override
	public int getDistance() {
		return distance;
	}

	@Override
	public int decLifeSpan() {
		lifeSpan--;
		return lifeSpan;
	}
}
