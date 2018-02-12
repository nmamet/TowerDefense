package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Case extends JComponent{
	public enum CaseType{
		NORTH_EAST, NORTH_WEST, NORTH_SOUTH, WEST_EAST, WEST_SOUTH, WEST_NORTH, SOUTH_WEST, SOUTH_NORTH, SOUTH_EAST, EAST_NORTH, EAST_WEST, EAST_SOUTH, NO_PATH
	}
	public Case(){
		super();
		type = CaseType.NO_PATH;
	}
	
	private CaseType type;
	
	public void setCaseType(CaseType t){
		type = t;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		switch(type){
		case NO_PATH: g.setColor(Color.GRAY); break;
		default: g.setColor(Color.WHITE);
		}
		
		g.fillRect(1, 1, this.getWidth(), this.getHeight());
		
	}
}
