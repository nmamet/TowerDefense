package ihm;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FieldWrapper extends JPanel {
	
	private Terrain t;
	
	public FieldWrapper(Terrain t) {
		super(null);
		this.t = t;
		super.add(t);
	}
	
	//Setup the field location and size (the frame must be visible)
	public void setField(){
		Insets insets = this.getInsets();
		t.setBounds(insets.left, insets.top, 
					this.getWidth() - insets.left - insets.right, 
					this.getHeight() - insets.top - insets.bottom
				   	);
		t.repaint();
	}
	
	@Override
	public Component add(Component c){
		if(c instanceof MovingGraphic){
			super.add(c);
			moveGraphic((MovingGraphic)c);
			return c;
		} else {
			System.out.println("ajout d'un composant incorrect sur le terrain");
			return null;
		}
	}
	
	public void moveGraphic(MovingGraphic mv){
		System.out.println("déplacement d'une unité graphique");
		//
		
		
		
		//100,100 à changer
		mv.setBounds(mv.getGraphicPosition().x, mv.getGraphicPosition().y, 100,100);
		if(mv instanceof Unit){
			//((Unit) mv).repaint();
		}
		
		System.out.println(mv.getLocation());
	}
	/*
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Component c : this.getComponents()){
			if(c instanceof MovingGraphic){
				c.repaint();
				System.out.println("movable graphics repaint");
			} else {
				System.out.println("field repaint");
				c.repaint();;
			}
		}
	}
	*/
}
