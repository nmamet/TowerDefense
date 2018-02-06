package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
	

public class Vue extends JFrame{
	
	
	
	public Vue(){
		
		int widthWindow = 1000;
		int heigthWindow = 1000;
		JPanel fieldPanel;
		//The place where you'll setup your turrets
		fieldPanel = new Terrain();
		
		this.getContentPane().add(fieldPanel, BorderLayout.CENTER);
		
		// The place where you'll select turrets to place and stuff
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BorderLayout());
		
		JPanel turretMenu = new JPanel();
		turretMenu.setLayout(new BoxLayout(turretMenu,BoxLayout.PAGE_AXIS));
		try {
			turretMenu.add(new BoutonTour(0,0));
			turretMenu.add(new BoutonTour(0,1));
			turretMenu.add(new BoutonTour(0,2));
			turretMenu.add(new BoutonTour(0,3));
			turretMenu.add(new BoutonTour(0,4));
			turretMenu.add(new BoutonTour(1,0));
			turretMenu.add(new BoutonTour(1,1));
			turretMenu.add(new BoutonTour(1,2));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	public static void main(String[] args){
		Vue v = new Vue();
	}
}
