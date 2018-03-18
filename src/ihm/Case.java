package ihm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import model.Cell;
import model.Position;
import model.PositioningSystem;
import model.TwoDimCoordinate;

public class Case extends JComponent implements Cell<TwoDimCoordinate>{
	
	private Direction in;
	private Direction out;
	private PositioningSystem<TwoDimCoordinate> ps;
	private TwoDimCoordinate pos;
	
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
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.fillRect(2, 2, getWidth(), getHeight());
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

	@Override
	public PositioningSystem<TwoDimCoordinate> getSystem() {
		return ps;
	}

	@Override
	public TwoDimCoordinate pos() {
		return pos;
	}
}
