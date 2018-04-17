package model;

class Square implements Cell<TwoDimCoordinate> {

	private PositioningSystem<TwoDimCoordinate> ps;
	private TwoDimCoordinate c;
	private boolean isFree;
	
	public Square(TwoDimArraySystem ps, TwoDimCoordinate c){
		this.ps = ps;
		this.c = c;
		isFree = true;
	}
	
	@Override
	public PositioningSystem<TwoDimCoordinate> getSystem() {
		return ps;
	}

	@Override
	public TwoDimCoordinate pos() {
		return c;
	}
	
	public boolean isFree(){
		return isFree;
	}

	public AttackingObject placeTurret() {
		if(isFree){
			isFree = false;
			return new Turret(c,3,120,5);
		}
		return null;
	}
	
	public void setCrossable(boolean b){
		isFree = b;
	}
}
