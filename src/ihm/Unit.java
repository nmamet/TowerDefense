package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Timer;

import javax.swing.JComponent;

import model.MovingObject;
import model.Path2DCoord;
import model.PathPosition;

public class Unit extends JComponent{
	
	private MovingObject unit;
	private Timer t;
	
	public Unit(MovingObject unit){
		super();
		this.unit = unit;
	}
	
	public PathPosition getPos(){
		return unit.getPos();
	}

	@Override
	public void paintComponent(Graphics g){
		System.out.println("paint unit");
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(2, 2, getWidth(), getHeight());
	}
}
