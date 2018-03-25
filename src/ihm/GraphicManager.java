package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GraphicManager extends JPanel {
	
	private Terrain t;
	
	public GraphicManager(Terrain t) {
		super(null);
		this.t = t;
		super.add(this.t);
		//super.setComponentZOrder(this.t, Integer.MAX_VALUE);
	}
	
	//Setup the field location and size (the frame must be visible)
	public void setField(){
		Insets insets = this.getInsets();
		t.setBounds(insets.left, insets.top, 
					this.getWidth() - insets.left - insets.right, 
					this.getHeight() - insets.top - insets.bottom
				   	);
		t.repaint(this.getBounds());
		System.out.println(this.getBounds());
	}
	
	@Override
	public Component add(Component c){
		if(c instanceof MovingGraphic){
			super.remove(t);
			super.add(c);
			super.add(t);
			moveGraphic((MovingGraphic)c);
			return c;
		} else {
			System.out.println("ajout d'un composant incorrect sur le terrain");
			return null;
		}
	}
	
	public void moveGraphic(MovingGraphic mv){
		System.out.println("deplacement d'une unite graphique");
		mv.setLocation(mv.getGraphicPosition().x, mv.getGraphicPosition().y);
		//mv.getGraphicPosition();
		//mv.setLocation(0,0);
		//System.out.println(mv.getLocation());
	}
	/*
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//[x=257,y=382,width=85,height=76]
		System.out.println("paintmanager");
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 5, 5);
	}
	*/
}
