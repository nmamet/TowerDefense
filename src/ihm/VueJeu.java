package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Path2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.ControllerEventListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Model;
import model.MovingTarget;
import model.Path;
import model.Path2DCoord;
import model.TwoDimArraySystem;
import model.TwoDimCoordinate;


public class VueJeu extends JFrame{
	
	Model<Path2DCoord> model;
	GraphicManager centerPanel;
	public boolean buttonclicked = false;
	
	private void launchWave() {
		JFrame f = this;
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
        	
        	Iterator<MovingTarget<Path2DCoord>> it = model.launchWave().iterator();
        	
            @Override
            public void run() {
            	if(it.hasNext()) {
            		MovingTarget<Path2DCoord> mt = it.next();
            		Unit u = new Unit(mt);
            		ArrayList<MovingTarget<Path2DCoord>> mtlist = new ArrayList<MovingTarget<Path2DCoord>>();
            		mtlist.add(mt);
            		model.addUnits(mtlist);
            		centerPanel.add(u);
            		f.repaint();
            	} else {
            		this.cancel();
            	}
            }
        }, 0, 1000);
	}
	
	public VueJeu(Model<Path2DCoord> m, TwoDimArraySystem ps, Path<Path2DCoord> path) throws PathOutOfField{
		
		model = m;
		int widthWindow = 1000;
		int heightWindow = 700;
		Terrain field;
		//The place where you'll setup your turrets
		
		try {
			field = new Terrain(ps, path);
		} catch (CyclingPathException e2) {
			System.out.println("Erreur lors de la construction du terrain");
			e2.printStackTrace();
			System.exit(1);
			field = null;
		}
		//Unit.setField(fieldPanel);
		centerPanel = new GraphicManager(field, model);
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		/*MovingObject<Path2DCoord> mo = m.launchWave().iterator().next();
		Unit u = new Unit(mo);
		centerPanel.add(u);*/
		//centerPanel.add(fieldPanel);
		//centerPanel.setLayout(null);

		// The place where you'll select turrets to place and stuff
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BorderLayout());
		
		JPanel turretMenu = new JPanel();
		SpriteBuilder turretBuilder;
		try {
			turretBuilder = new SpriteBuilder(64, 2, 5, 8, "turrets_units.png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
			turretBuilder = null;
		}
		turretMenu.setLayout(new BoxLayout(turretMenu,BoxLayout.PAGE_AXIS));
		try {
			turretMenu.add(new BoutonTour(turretBuilder.getSprite(0),this));
			//turretMenu.add(new BoutonTour(turretBuilder.getSprite(1),this));
			//turretMenu.add(new BoutonTour(turretBuilder.getSprite(2),this));
			//turretMenu.add(new BoutonTour(turretBuilder.getSprite(3),this));
			//turretMenu.add(new BoutonTour(turretBuilder.getSprite(4),this));
			//turretMenu.add(new BoutonTour(turretBuilder.getSprite(5),this));
			//turretMenu.add(new BoutonTour(turretBuilder.getSprite(6),this));
			//turretMenu.add(new BoutonTour(turretBuilder.getSprite(7),this));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		SpriteBuilder unitBuilder;
		try{
			unitBuilder = new SpriteBuilder(64, 3, 5, 15, "15_tank_set.png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
			unitBuilder = null;
		}
		*/
		//Unit u = new Unit(new Path2DCoord(new TwoDimCoordinate(0, 0)));
		//fieldPanel.add(u);
		
		sidePanel.add(turretMenu, BorderLayout.CENTER);
		
		JPanel otherStuff = new JPanel();
		sidePanel.add(otherStuff, BorderLayout.SOUTH);
		JButton buttonLaunch = new JButton("Ready");
		buttonLaunch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				launchWave();
			}
		});
		otherStuff.add(buttonLaunch);
		otherStuff.add(new JButton("Sell"));
		
		getContentPane().add(sidePanel, BorderLayout.EAST);
		//JFrame parameters
		setTitle("Tower Defense");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(widthWindow, heightWindow);
		//this.pack();
		Container cp = getContentPane();
		//System.out.println(getInsets().top);
		int centerWidth = widthWindow-cp.getInsets().top-sidePanel.getPreferredSize().width;
		//System.out.println(centerWidth);
		//System.out.println(centerHeight);
		
		CoordinateConverter cc = new CoordinateConverter(new Dimension(centerWidth-10,heightWindow-33), ps);
		Case.setConverter(cc);
		Unit.setConverter(cc);
		GraphicTurret.setConverter(cc);
		Unit.setField(field);
		
		//System.out.println(cp.getPreferredSize());
		centerPanel.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(buttonclicked){
					buttonclicked = false;
					SpriteBuilder turretBuilder;
					try {
						turretBuilder = new SpriteBuilder(64, 2, 5, 8, "turrets_units.png");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.exit(1);
						turretBuilder = null;
					}
					//System.out.println("turret placed");
					//si le point cliqué est dans la grille
					Point p = e.getPoint();
					Point p2 = new Point(p.y,p.x);
					TwoDimCoordinate pos = cc.graphicToField(p2);
					System.out.println("button clicked at pos "+pos.row()+", "+pos.column());
					if(ps.isInSystem(pos)){
						//System.out.println(cc.graphicToField(e.getPoint()));
						centerPanel.addTurret(pos, turretBuilder.getSprite(0));
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				buttonclicked = false;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//buttonclicked = false;
				
			}
		});
		
		setVisible(true);
		//centerPanel.setField();
		
		//launchWave();
	}
}
