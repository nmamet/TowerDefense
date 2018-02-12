package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Case extends JComponent{
	public enum Direction{
		NORTH,WEST,SOUTH,EAST,NONE
	}
	public Case(){
		super();
		in = Direction.NONE;
		out = Direction.NONE;
	}
	
	private Direction in;
	private Direction out;
	
	public void setDirectionIn(Direction in){
		this.in = in;
	}
	
	public void setDirectionOut(Direction out){
		this.out = out;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.fillRect(1, 1, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		switch(in){
		case NORTH: g.fillRect(getWidth()/5, 1, 3*getWidth()/5, getHeight()/3); break;
		case SOUTH: g.fillRect(getWidth()/5, 2*getWidth()/3, 4*getWidth()/5, getHeight()/3);break;
		case WEST: g.fillRect(1, getHeight()/5, 1/3*getWidth(), 3*getHeight()/5); break;
		case EAST: g.fillRect(2*getWidth()/3, getHeight()/5, getWidth()/3, 4*getHeight()/5);break;
		default: ;
		}
		switch(out){
		case NORTH: g.fillRect(getWidth()/5, 1, 3*getWidth()/5, getHeight()/3); break;
		case SOUTH: g.fillRect(getWidth()/5, 2*getWidth()/3, 4*getWidth()/5, getHeight()/3);break;
		case WEST: g.fillRect(1, getHeight()/5, 1/3*getWidth(), 3*getHeight()/5); break;
		case EAST: g.fillRect(2*getWidth()/3, getHeight()/5, getWidth()/3, 4*getHeight()/5);break;
		default: ;
		}
		
		g.fillRect(1, 1, this.getWidth(), this.getHeight());
		
	}
}
