package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

import model.Cell;
import model.MovingObject;
import model.Path2DCoord;
import model.Position;
import model.PositioningSystem;
import model.TwoDimCoordinate;

class Case implements Cell<TwoDimCoordinate>{
	
	private Direction in;
	private Direction out;
	private PositioningSystem<TwoDimCoordinate> ps;
	private TwoDimCoordinate pos;
	private static CoordinateConverter converter;
	
	public enum Direction{
		NORTH,WEST,SOUTH,EAST,NONE
	}
	
	public Case(PositioningSystem<TwoDimCoordinate> ps, TwoDimCoordinate pos){
		super();
		in = Direction.NONE;
		out = Direction.NONE;
		this.ps = ps;
		this.pos = pos;
	}
	
	public static void setConverter(CoordinateConverter cc){
		converter = cc;
	}
	
	public void setDirectionIn(Direction in) throws CyclingPathException{
		if(this.in != Direction.NONE) {
			throw new CyclingPathException();
		}
		this.in = in;
	}
	
	public void setDirectionOut(Direction out) throws CyclingPathException{
		if(this.out != Direction.NONE) {
			throw new CyclingPathException();
		}
		this.out = out;
	}
	/*
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//System.out.println("paint case");
		g.setColor(Color.GRAY);
		g.fillRect(1, 1, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		//g.fillRect(getWidth()/4, getHeight()/4, 3*getWidth()/4, getHeight()/2);
		
		switch(in){
		case NORTH: g.fillRect(getWidth()/4, 0, getWidth()/2, 3*getHeight()/4); break;
		case SOUTH: g.fillRect(getWidth()/4, getHeight()/4, getWidth()/2, 3*getHeight()/4);break;
		case WEST: g.fillRect(0, getHeight()/4, 3*getWidth()/4, getHeight()/2); break;
		case EAST: g.fillRect(getWidth()/4, getHeight()/4, 3*getWidth()/4, getHeight()/2);break;
		default: ;
		}
		switch(out){
		case NORTH: g.fillRect(getWidth()/4, 0, getWidth()/2, 3*getHeight()/4); break;
		case SOUTH: g.fillRect(getWidth()/4, getHeight()/4, getWidth()/2, 3*getHeight()/4);break;
		case WEST: g.fillRect(0, getHeight()/4, 3*getWidth()/4, getHeight()/2); break;
		case EAST: g.fillRect(getWidth()/4, getHeight()/4, 3*getWidth()/4, getHeight()/2);break;
		default: ;
		}
		
	}
	*/
	
	public void paintCase(Graphics g){
		//les différents -1 sont là pour ne pas écrire dans la case suivante
		Point location = converter.fieldToGraphic(pos);
		System.out.println("location : "+location);
		Dimension dimension  = converter.getCellSize();
		System.out.println("dimension : " + dimension);
		g.setColor(Color.BLACK);
		g.drawRect(location.x, location.y, dimension.width-1, dimension.height-1);
		g.setColor(Color.GRAY);
		g.fillRect(location.x, location.y, dimension.width-1, dimension.height-1);
		g.setColor(Color.WHITE);
		switch(in){
		case NORTH: g.fillRect(location.x+dimension.width/4, location.y, dimension.width/2, 3*dimension.height/4); break;
		case SOUTH: g.fillRect(location.x+dimension.width/4, location.y+dimension.height/4, dimension.width/2, 3*dimension.height/4-1);break;
		case WEST: g.fillRect(location.x, location.y+dimension.height/4, 3*dimension.width/4, dimension.height/2); break;
		case EAST: g.fillRect(location.x+dimension.width/4, location.y+dimension.height/4, 3*dimension.width/4-1, dimension.height/2);break;
		default: ;
		}
		switch(out){
		case NORTH: g.fillRect(location.x+dimension.width/4, location.y, dimension.width/2, 3*dimension.height/4); break;
		case SOUTH: g.fillRect(location.x+dimension.width/4, location.y+dimension.height/4, dimension.width/2, 3*dimension.height/4-1);break;
		case WEST: g.fillRect(location.x, location.y+dimension.height/4, 3*dimension.width/4, dimension.height/2); break;
		case EAST: g.fillRect(location.x+dimension.width/4, location.y+dimension.height/4, 3*dimension.width/4-1, dimension.height/2);break;
		default: ;
		}
		//trace la première ligne et colonne noires
		g.setColor(Color.BLACK);
		if(location.x == 0){
			g.drawLine(0, location.y, 0, location.y+dimension.height-1);
		}
		if(location.y == 0){
			g.drawLine(location.x, 0, location.x+dimension.width-1, location.y);
		}
		//g.drawLine(location.x+dimension.height-1, y1, x2, y2);
	}
	
	@Override
	public PositioningSystem<TwoDimCoordinate> getSystem() {
		return ps;
	}

	@Override
	public TwoDimCoordinate pos() {
		return pos;
	}
	
	public Point movingObjectPosition(MovingObject<Path2DCoord> mo){
		
		int maxDist = mo.getPos().maxDist();
		int dist = mo.getDistance();
		
		if(in == null){
			
		if(out==null){
			
		}
		return null;
		
	}
}
