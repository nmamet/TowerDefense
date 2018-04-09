package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

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
		timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	if(unit.isAtTheEnd()) {
            		timer.cancel();
            	} else {
            		unit.move();	
            	}
            }
        }, 0, 100);
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

	public void paintUnit(Graphics g) {
		Rectangle r;
		try {
			///System.out.println("position : row "+unit.getPos().row()+" column "+unit.getPos().column());
			r = field.getCell(unit.getPos()).movingObjectGraphicFrame(unit);
		} catch (OutOfFieldException e) {
			System.out.println("Erreur : unite hors du terrain lors du dessin de l'unite");
			e.printStackTrace();
			System.exit(1);
			r = null;
		}
		//System.out.println("Dessin de l'unite dans la case a la position "+ position);
		//Dimension cellSize = converter.getCellSize();
		g.fillRect(r.x, r.y, r.width, r.height);
	}
	
	public void stop() {
		timer.cancel();
	}
}
