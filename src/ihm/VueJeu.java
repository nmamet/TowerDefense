package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;

import javax.sound.midi.ControllerEventListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Main;
import model.Model;
import model.MovingObject;
import model.Path;
import model.Path2DCoord;
import model.TwoDimArraySystem;
import model.TwoDimCoordinate;
	

public class VueJeu extends JFrame{
	
	
	
	public VueJeu(Model<Path2DCoord> m, TwoDimArraySystem ps, Path<Path2DCoord> path) throws PathOutOfField{
		
		int widthWindow = 1000;
		int heigthWindow = 800;
		JPanel fieldPanel;
		//The place where you'll setup your turrets
		try {
			fieldPanel = new Terrain(ps, path);
		} catch (CyclingPathException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.exit(1);
			fieldPanel = null;
		}
		
		this.getContentPane().add(fieldPanel, BorderLayout.CENTER);
		
		// The place where you'll select turrets to place and stuff
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BorderLayout());
		
		JPanel turretMenu = new JPanel();
		SpriteBuilder sb;
		try {
			sb = new SpriteBuilder(64, 2, 5, 8, "turrets_units.png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
			sb = null;
		}
		turretMenu.setLayout(new BoxLayout(turretMenu,BoxLayout.PAGE_AXIS));
		try {
			turretMenu.add(new BoutonTour(sb.getSprite(0)));
			turretMenu.add(new BoutonTour(sb.getSprite(1)));
			turretMenu.add(new BoutonTour(sb.getSprite(2)));
			turretMenu.add(new BoutonTour(sb.getSprite(3)));
			turretMenu.add(new BoutonTour(sb.getSprite(4)));
			turretMenu.add(new BoutonTour(sb.getSprite(5)));
			turretMenu.add(new BoutonTour(sb.getSprite(6)));
			turretMenu.add(new BoutonTour(sb.getSprite(7)));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sidePanel.add(turretMenu, BorderLayout.CENTER);
		
		JPanel otherStuff = new JPanel();
		sidePanel.add(otherStuff, BorderLayout.SOUTH);
		JButton buttonLaunch = new JButton("Ready");
		buttonLaunch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Collection<? extends MovingObject<Path2DCoord>> l = m.launchWave();
				for( MovingObject<Path2DCoord> unit : l) {
					
				}
			}
		});
		otherStuff.add(buttonLaunch);
		otherStuff.add(new JButton("Sell"));
		
		getContentPane().add(sidePanel, BorderLayout.EAST);
		//JFrame parameters
		setTitle("Tower Defense");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(widthWindow, heigthWindow);
		setVisible(true);
	}
}
