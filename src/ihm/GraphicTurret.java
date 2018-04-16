package ihm;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import model.AttackingObject;
import model.TwoDimCoordinate;

public class GraphicTurret {
	private static CoordinateConverter cc;
	AttackingObject turret;
	Timer t;
	Image sprite;
	
	private GraphicTurret(AttackingObject turret, Image sprite) {
		if(turret != null){
			this.turret = turret;
			this.sprite = sprite;
			t = new Timer(true);
			t.schedule(new TimerTask() {
				
				@Override
				public void run() {
					turret.attack();
				}
			}, 0, turret.getAttackPeriod());
		}
	}
	
	public static GraphicTurret buildTurret(AttackingObject turret, Image sprite){
		GraphicTurret tu = new GraphicTurret(turret, sprite);
		if(tu.t == null){
			return null;
		}
		return tu;
	}
	
	public static void setConverter(CoordinateConverter cc){
		GraphicTurret.cc = cc;
	}
	
	public void paintTurret(Graphics g){
		//System.out.println(g);
		//System.out.println(turret);
		
		TwoDimCoordinate pos = turret.getPos();
		Point p = cc.fieldToGraphic(pos);
		g.drawImage(sprite, p.x, p.y, null);
	}
}
