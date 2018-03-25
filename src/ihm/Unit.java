package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Timer;

import javax.swing.JComponent;

import model.MovingObject;
import model.OutOfFieldException;
import model.Path2DCoord;
import model.PathPosition;
import model.TwoDimCoordinate;

public class Unit extends MovingGraphic{
	
	private MovingObject<Path2DCoord> unit;
	private Timer timer;
	private static Terrain field;
	
	public Unit(MovingObject<Path2DCoord> unit){
		super();
		this.unit = unit;
		this.setSize(field.getCellSize());
		//this.setPreferredSize(field.getCellSize());
		//this.setSize(5,5);
	}
	
	public PathPosition getPos(){
		return unit.getPos();
	}

	public static void setField(Terrain t){
		field = t;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		System.out.println("paint unit");
		System.out.println(this.getBounds());
		System.out.println("x : " + getX() + " y : "+getY());
		g.setColor(Color.BLACK);
		g.fillRect(getX(),getY(), getWidth(), getHeight());
		g.setColor(Color.RED);
		g.fillRect(getX(), getY(), getWidth()/2, getHeight()/2);
	}

	@Override
	public Point getGraphicPosition() {
		try {
			System.out.println("getGraphicsPosition");
			System.out.println("position de la première case");
			System.out.println((field.getCell(new TwoDimCoordinate(1, 1))).getLocation());
			//System.out.println(new TwoDimCoordinate(5, 3).row());
			//return field.getCell(unit.getPos()).getLocation();
			return field.getCell(new TwoDimCoordinate(0, 0)).getLocation();
		} catch (OutOfFieldException e) {
			
			e.printStackTrace();
			System.out.println("Ce n'est pas cense arriver. Erreur dans getGraphicsPosition; le path est en dehors du systeme");
			System.exit(1);
			return null;
		}
	}
}
