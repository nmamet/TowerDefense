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
			System.out.println("warning : unite initialisee avec une pos nulle");
		}
	}
	
	@Override
	public void move() {
		int parcouru = 0;
		while(parcouru<speed){
			if(distance+speed-parcouru<pos.maxDist()) {
				distance += speed-parcouru;
				parcouru = speed;
			}else {
				parcouru = distance - pos.maxDist();
				distance = 0;
				pos = pos.nextPos();
			}
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
