package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Timer;

import javax.swing.JComponent;

import model.CoordPath2D;
import model.TwoDimCoordinate;

public class Unit extends JComponent{
	
	private CoordPath2D pos;
	private Timer t;
	
	public Unit(CoordPath2D pos){
		super();
		this.pos = pos;
		
	}
	
	public CoordPath2D getPos(){
		return pos;
	}

	@Override
	public void paintComponent(Graphics g){
		System.out.println("paint unit");
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(2, 2, getWidth(), getHeight());
	}
}
