package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;

import model.MovingTarget;
import model.OutOfFieldException;
import model.Path2DCoord;
import model.PathPosition;
import model.TwoDimCoordinate;

public class Unit{
	
	private MovingTarget<Path2DCoord> unit;
	private Timer timer;
	private static Terrain field;
	private static CoordinateConverter converter;
	private static SpriteBuilder sb = null;
	private boolean isDead = false;
	static {
		try {
			sb = new SpriteBuilder(64, 3, 5, 15, "15_tank_set.png");
		} catch (IOException e) {
			System.out.println("erreur dans le fichier image des unites");
			e.printStackTrace();
			System.exit(1);
		}
	}
	private static Image sprite = null;
	
	public Unit(MovingTarget<Path2DCoord> unit){
		this.unit = unit;
		timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	if(unit.isAtTheEnd()) {
            		timer.cancel();
            		isDead = true;
            	} else {
            		unit.move();
            		if(unit.isAtTheEnd()) {
                		timer.cancel();
                		isDead = true;
                	}
            	}
            }
        }, 0, 100);
        
       
	}
	
	public static void setField(Terrain t){
		field = t;
	}
	
	public static void setConverter(CoordinateConverter cc){
		converter = cc;
		sprite = sb.getSprite(0, cc.getCellSize().width/2, cc.getCellSize().height/2, 0);
	}
	
	public PathPosition getPos(){
		return unit.getPos();
	}

	public void paintUnit(Graphics g) throws DeathException{
		SpriteInfo si;
		if(isDead || unit.isAtTheEnd()){
			isDead = true;
			throw new DeathException();
		}
		try {
			///System.out.println("position : row "+unit.getPos().row()+" column "+unit.getPos().column());
			si = field.getCell(unit.getPos()).paintMovingObject(unit);
		} catch (OutOfFieldException e) {
			System.out.println("Erreur : unite hors du terrain lors du dessin de l'unite");
			e.printStackTrace();
			System.exit(1);
			si = null;
		}
		
		//System.out.println("Dessin de l'unite dans la case a la position "+ position);
		//Dimension cellSize = converter.getCellSize();
		//g.fillRect(r.x, r.y, r.width, r.height);
		Graphics2D g2d = (Graphics2D) g;
		/*double rotationRequired = Math.toRadians (si.rotation);
		switch((int)si.rotation){
		case 90:
		case 270:
			g2d.rotate(rotationRequired,sprite.getWidth(null)/2,sprite.getHeight(null)/2);
			g2d.drawImage(sprite, si.frame.y, si.frame.x, null);
			g2d.rotate(-rotationRequired, sprite.getWidth(null)/2,sprite.getHeight(null)/2);
			break;
		case 0:
		case 180:
			g2d.rotate(rotationRequired,sprite.getWidth(null)/2,sprite.getHeight(null)/2);*/
			g2d.drawImage(sprite, si.frame.x, si.frame.y, null);
			/*g2d.rotate(-rotationRequired, sprite.getWidth(null)/2,sprite.getHeight(null)/2);
		}
		
		*/
		g.setColor(Color.RED);
		g.fillRect(si.frame.x, si.frame.y - 6, (int)(si.frame.width*unit.getHealthPercentage()), 6);
		// Drawing the rotated image at the required drawing locations
		
		//g2d.drawImage(sprite.getScaledInstance(si.frame.width, si.frame.height, Image.SCALE_DEFAULT), si.frame.x, si.frame.y, null);
		
		
		
	}
	/*
	public void stop() {
		timer.cancel();
	}
*/
	public MovingTarget<Path2DCoord> getUnit() {
		return unit;
	}
}
