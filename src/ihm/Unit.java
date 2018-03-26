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

public class Unit{
	
	private MovingObject<Path2DCoord> unit;
	private Timer timer;
	private static Terrain field;
	private static CoordinateConverter converter;
	
	public Unit(MovingObject<Path2DCoord> unit){
		this.unit = unit;
	}
	
	public static void setField(Terrain t){
		field = t;
	}
	
	public static void setConverter(CoordinateConverter cc){
		converter = cc;
	}
	
	public PathPosition getPos(){
		return unit.getPos();
	}

	
	/*
	public Point getGraphicPosition() {
		try {
			//System.out.println("getGraphicsPosition");
			//System.out.println("position de la premiere case");
			//System.out.println((field.getCell(new TwoDimCoordinate(0, 0))).getLocation());
			//System.out.println(new TwoDimCoordinate(5, 3).row());
			//return field.getCell(unit.getPos()).getLocation();
			return field.getCell(unit.getPos());
		} catch (OutOfFieldException e) {
			
			e.printStackTrace();
			System.out.println("Ce n'est pas cense arriver. Erreur dans getGraphicsPosition; le path est en dehors du systeme");
			System.exit(1);
			return null;
		}
	}*/
}
