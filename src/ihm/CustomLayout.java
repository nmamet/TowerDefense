package ihm;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import model.CoordPath2D;

public class CustomLayout implements LayoutManager {
	
	ArrayList<Unit> units;
	GridLayout gl;;
	
	public CustomLayout(int rows, int cols, int hgap, int vgap){
		gl = new GridLayout(rows,cols,hgap,vgap);
		units = new ArrayList<Unit>();
	}
	
	@Override
	public void addLayoutComponent(String name, Component comp){
		if(comp instanceof Unit){
			System.out.println("unit");
			units.add((Unit)comp);
		}else{
		gl.addLayoutComponent(name, comp);
		}
	}
	
	@Override
	public void layoutContainer(Container c){
		
		System.out.println("layout");
		//System.out.println("debut " + getRows() + " " + getColumns());
		
		for (Unit u : units){
			//super.removeLayoutComponent(u);
			
		}
		//System.out.println("1 " + getRows() + " " + getColumns());
		gl.layoutContainer(c);
		//System.out.println("2 " + getRows() + " " + getColumns());
		for(Unit u : units){
			//super.addLayoutComponent(null, u);
			u.setBounds(u.getPos().column(), u.getPos().row(), 200, 200);
			
		}
		//System.out.println("fin " + getRows() + " " + getColumns());
	}
	
	public void addUnit(Unit u){
		System.out.println("unit");
		units.add(u);
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		gl.removeLayoutComponent(comp);
		
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return null;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return null;
	}
}
