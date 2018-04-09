package ihm;


import java.awt.Dimension;
import java.awt.Point;

import model.PositioningSystem;
import model.TwoDimArraySystem;
import model.TwoDimCoordinate;

class CoordinateConverter {
	private TwoDimArraySystem ps;
	private Dimension cellSize;
	
	public CoordinateConverter(Dimension dim, TwoDimArraySystem s){
		ps = s;
		cellSize = new Dimension(dim.width/s.nbOfColumns(), dim.height/s.nbOfRows());
		//On s'assure que les dimensions d'une case sont multiples de 4 (pour des raisons d'affichages du chemin)
		cellSize = new Dimension(cellSize.width - (cellSize.width%4), cellSize.height - (cellSize.height%4));
	}
	
	public Point fieldToGraphic(TwoDimCoordinate c){
		//System.out.println(new Point(c.column()*cellSize.width,c.row()*cellSize.height));
		return new Point(c.column()*cellSize.width,c.row()*cellSize.height);
	}
	
	//todo
	public TwoDimCoordinate graphicToField(Point p){
		return new TwoDimCoordinate(p.x/cellSize.width, p.y/cellSize.height);
	}
	
	public Dimension getCellSize(){
		return new Dimension(cellSize);
	}
}
