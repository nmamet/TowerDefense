package model;

class Square implements Cell<TwoDimCoordinate> {

	private PositioningSystem<TwoDimCoordinate> ps;
	private TwoDimCoordinate c;
	
	public Square(TwoDimArraySystem ps, TwoDimCoordinate c){
		this.ps = ps;
		this.c = c;
	}
	
	@Override
	public PositioningSystem<TwoDimCoordinate> getSystem() {
		return ps;
	}

	@Override
	public TwoDimCoordinate pos() {
		return c;
	}
}
