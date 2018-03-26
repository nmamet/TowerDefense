package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GraphicManager extends JComponent {
	
	private Terrain field;
	
	public GraphicManager(Terrain field) {
		super();
		this.field = field;
	}
	
	@Override
	public void paintComponent(Graphics g){
		field.paintField(g);
	}
}
