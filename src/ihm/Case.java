package ihm;

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
}
