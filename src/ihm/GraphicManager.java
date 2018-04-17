package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Attack;
import model.DefeatException;
import model.Model;
import model.MovingTarget;
import model.Path2DCoord;
import model.PathPosition;
import model.TwoDimCoordinate;

public class GraphicManager extends JComponent {
	private CoordinateConverter cc;
	private ArrayList<Unit> units;
	private ArrayList<GraphicTurret> turrets;
	private Terrain field;
	private Timer timer;
	private Semaphore sem;
	private Object locker = null;
	private Model<Path2DCoord> m;
	private ArrayList<Attack> attacks;
	private boolean sendIncome = false;
	private boolean lastWave = false;
	private boolean lost = false;
	private boolean lostDone = false;
	
	public GraphicManager(Terrain field, Model<Path2DCoord> m) {
		super();
		this.cc = null;
		this.m = m;
		sem = new Semaphore(1);
		this.field = field;
		units = new ArrayList<Unit>();
		turrets = new ArrayList<GraphicTurret>();
		attacks = new ArrayList<Attack>();
		timer = new Timer();
		GraphicManager gm = this;
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				gm.repaint();
			}
		}, 10, 10);
	}
	
	public void setCC(CoordinateConverter cc) {
		this.cc = cc;
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
		
		//dessin des unites
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
				m.unitKill();
				itu.remove();
			} catch (LeakException e) {
				m.removeUnit(u.getUnit());
				try {
					
					m.unitLeak();
					
				} catch (DefeatException e1) {
					lost = true;
				}
				itu.remove();
			}
		}
		releaseUnitsList();
		if(lost && !lostDone) {
			lost = false;
			lostDone = true;
			JOptionPane.showMessageDialog(this, "You've lost!","Defeat",JOptionPane.PLAIN_MESSAGE);
		}
		if(units.size() == 0 && sendIncome) {
			sendIncome = false;
			m.income();
			if(lastWave) {
				JOptionPane.showMessageDialog(this, "You've won!","Victory",JOptionPane.PLAIN_MESSAGE);
			}
		}
		//Collections.reverse(units);
		
		//dessin des tirs
		attacks.addAll(m.getAllAttacks());
		Iterator<Attack> ita = attacks.iterator();
		while(ita.hasNext()) {
			Attack a = ita.next();
			//System.out.println("firing a beam");
			/*System.out.println(a.getOrigin().column()+" "+ 
					a.getOrigin().row()+" "+ 
					a.getTarget().column()+" "+
					a.getTarget().row());*/
			Point origin = cc.fieldToGraphic(a.getOrigin());
			Point target = cc.fieldToGraphic(a.getTarget());
			g.setColor(Color.RED);
			g.drawLine(
					origin.x+cc.getCellSize().width/2, 
					origin.y+cc.getCellSize().height/2, 
					target.x+cc.getCellSize().width/2, 
					target.y+cc.getCellSize().height/2
					);
			if(a.decLifeSpan() == 0) {
				ita.remove();
			}
		}
	}
	
	public void add(Unit u) {
		sendIncome = true;
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

	public void lastWave() {
		lastWave = true;
	}
}
