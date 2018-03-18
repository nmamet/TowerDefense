package model;

class Unit implements MovingObject<Path2DCoord>{

	private Path2DCoord pos;
	private int distance;
	private int speed;
	
	public Unit(Path2DCoord pos, int speed) {
		this.distance = 0;
		this.pos = pos;
		this.speed = speed;
		if(pos == null) {
			System.out.println("warning : unit initialiséeavec une pos nulle");
		}
	}
	
	@Override
	public void move() {
		if(distance+speed<pos.maxDist()) {
			distance += speed;
		}else {
			distance = distance + speed - pos.maxDist();
			pos = pos.nextPos();
		}
	}

	@Override
	public Path2DCoord getPos() {
		return pos;
	}

	@Override
	public int getDistance() {
		return distance;
	}

}
