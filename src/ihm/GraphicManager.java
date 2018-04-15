package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.MovingObject;
import model.Path2DCoord;

public class GraphicManager extends JComponent {
	private ArrayList<Unit> units;
	private Terrain field;
	private Timer timer;
	
	
	public GraphicManager(Terrain field) {
		super();
		this.field = field;
		units = new ArrayList<Unit>();
		timer = new Timer();
		GraphicManager gm = this;
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				gm.repaint();
			}
		}, 10, 10);
	}
	
	@Override
	public void paintComponent(Graphics g){
		//System.out.println("Dessin du manager");
		field.paintField(g);
		Collections.reverse(units);
		for(Unit u : units) {
			u.paintUnit(g);
		}
		Collections.reverse(units);
	}
	
	public void add(Unit u) {
		units.add(u);
	}
}
