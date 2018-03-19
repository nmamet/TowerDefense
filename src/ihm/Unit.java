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

public class Unit extends MovingGraphic{
	
	private MovingObject<Path2DCoord> unit;
	private Timer timer;
	private static Terrain field;
	
	public Unit(MovingObject<Path2DCoord> unit){
		super();
		this.unit = unit;
	}
	
	public PathPosition getPos(){
		return unit.getPos();
	}

	public static void setField(Terrain t){
		field = t;
	}
	
	@Override
	public void paintComponent(Graphics g){
		System.out.println("paint unit");
		System.out.println(this.getLocation());
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(2, 2, 70, 70);
	}

	@Override
	public Point getGraphicPosition() {
		try {
			System.out.println("getGraphicsPosition");
			return field.getCell(unit.getPos()).getLocation();
		} catch (OutOfFieldException e) {
			
			e.printStackTrace();
			System.out.println("Ce n'est pas cense arriver. Erreur dans getGraphicsPosition; le path est en dehors du systeme");
			System.exit(1);
			return null;
		}
	}
}
