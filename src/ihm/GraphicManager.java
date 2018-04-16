package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Model;
import model.MovingTarget;
import model.Path2DCoord;
import model.PathPosition;
import model.TwoDimCoordinate;

public class GraphicManager extends JComponent {
	private ArrayList<Unit> units;
	private ArrayList<GraphicTurret> turrets;
	private Terrain field;
	private Timer timer;
	private Semaphore sem;
	private Object locker = null;
	private Model<Path2DCoord> m;
	
	public GraphicManager(Terrain field, Model m) {
		super();
		this.m = m;
		sem = new Semaphore(1);
		this.field = field;
		units = new ArrayList<Unit>();
		turrets = new ArrayList<GraphicTurret>();
		timer = new Timer();
		GraphicManager gm = this;
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				gm.repaint();
			}
		}, 10, 10);
	}
	
	private void lockUnitsList(){
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			System.out.println("Thread interrompu : verrouillage unites (ihm)");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void releaseUnitsList(){
		sem.release();
	}
	
	@Override
	public void paintComponent(Graphics g){
		//System.out.println("Dessin du manager");
		//dessin des cases
		field.paintField(g);
		
		//dessin des tours
		ListIterator<GraphicTurret>itt = turrets.listIterator();
		while(itt.hasNext()) {
			GraphicTurret u = itt.next();
			u.paintTurret(g);
		}
		
		//Collections.reverse(units);
		lockUnitsList();
		ListIterator<Unit> itu = units.listIterator();
		while(itu.hasNext()) {
			Unit u = itu.next();
			try {
				u.paintUnit(g);
			} catch (DeathException e) {
				//System.out.println("unit removed ");
				m.removeUnit(u.getUnit());
				itu.remove();
			}
		}
		releaseUnitsList();
		//Collections.reverse(units);
	}
	
	public void add(Unit u) {
		lockUnitsList();
		units.add(u);
		releaseUnitsList();
	}

	public void addTurret(TwoDimCoordinate pos, Image i) {
		//on ajoute une tour a la position indiquee
		GraphicTurret gt = GraphicTurret.buildTurret(m.placeTurret(new Path2DCoord(pos)), i);
		if(gt != null){
			turrets.add(gt);
		}
	}
}
