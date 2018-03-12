package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.CoordPath2D;
import model.TwoDimCoordinate;
	

public class VueJeu extends JFrame{
	
	
	
	public VueJeu(int rowCount, int colCount, int [] tabRowPath, int[] tabColPath){
		
		int widthWindow = 1000;
		int heigthWindow = 800;
		Terrain fieldPanel;
		//The place where you'll setup your turrets
		JPanel centerPanel = new JPanel();
		fieldPanel = Terrain.buildTerrain(rowCount, colCount, tabRowPath, tabColPath);
		if(fieldPanel == null) {
			System.out.println("Erreur lors de la construction du terrain");
			System.exit(1);
		}
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.add(fieldPanel);
		centerPanel.setLayout(new CustomLayout());
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
			turretMenu.add(new BoutonTour(turretBuilder.getSprite(0)));
			turretMenu.add(new BoutonTour(turretBuilder.getSprite(1)));
			turretMenu.add(new BoutonTour(turretBuilder.getSprite(2)));
			turretMenu.add(new BoutonTour(turretBuilder.getSprite(3)));
			turretMenu.add(new BoutonTour(turretBuilder.getSprite(4)));
			turretMenu.add(new BoutonTour(turretBuilder.getSprite(5)));
			turretMenu.add(new BoutonTour(turretBuilder.getSprite(6)));
			turretMenu.add(new BoutonTour(turretBuilder.getSprite(7)));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SpriteBuilder unitBuilder;
		try{
			unitBuilder = new SpriteBuilder(64, 3, 5, 15, "15_tank_set.png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
			unitBuilder = null;
		}
		
		Unit u = new Unit(new CoordPath2D(new TwoDimCoordinate(0, 0)));
		//fieldPanel.add(u);
		
		sidePanel.add(turretMenu, BorderLayout.CENTER);
		
		JPanel otherStuff = new JPanel();
		sidePanel.add(otherStuff, BorderLayout.SOUTH);
		otherStuff.add(new JButton("Ready"));
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
