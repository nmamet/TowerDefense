package model;

class Unit implements MovingTarget<Path2DCoord>{

	private Path2DCoord pos;
	private int distance;
	private int speed;
	private boolean atTheEnd;
	private boolean dead;
	private int hp = 100;
	private int maxHp = 100;
	
	public Unit(Path2DCoord pos, int speed) {
		this.distance = 0;
		this.pos = pos;
		this.speed = speed;
		if(pos == null) {
			System.out.println("warning : unite initialisee avec une pos nulle");
		}
		atTheEnd = false;
		dead = false;
	}
	
	@Override
	public void move() {
		//System.out.println("move");
		//System.out.println("position : "+pos.column() + ", " + pos.row() + "; distance : "+distance);
		int parcouru = 0;
		while(parcouru<speed && !atTheEnd){
			if(distance+speed-parcouru<=pos.maxDist()) {
				distance += speed-parcouru;
				parcouru = speed;
			}else {
				if(pos.equals(pos.nextPos())) {
					atTheEnd = true;
				} else {
					//System.out.println("parcouru : "+parcouru+"; distance : "+distance);
					parcouru = pos.maxDist() - distance;
					distance = 0;
					//System.out.println("parcouru : "+parcouru+"; distance : "+distance);
					pos = pos.nextPos();
				}
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
	
	@Override
	public float getHealthPercentage(){
		return hp/ (float) maxHp;
	}
	
	@Override
	public boolean isAtTheEnd() {
		return atTheEnd;
	}

	@Override
	public void takeDamage(int dmg){
		System.out.println("unit taking damage; hp = "+hp);
		hp -=dmg;
		if(hp<=0){
			dead = true;;
		}
	}

	@Override
	public boolean isDead() {
		return dead;
	}

}
