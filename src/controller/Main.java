package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import ihm.CyclingPathException;
import ihm.PathOutOfField;
import ihm.VueJeu;
import ihm.VueMenu;
import model.ConcreteModel;
import model.Model;
import model.MovingObject;
import model.Path;
import model.Path2DCoord;
import model.PositioningSystem;
import model.TwoDimCoordinate;

public class Main {
	
	public static ArrayList<TwoDimCoordinate> buildPath(int [] tabRow, int [] tabCol){
		if(tabRow.length != tabCol.length) {
			System.out.println("les deux tableaux de path ont des tailles différentes");
			return null;
		}
		ArrayList<TwoDimCoordinate> l = new ArrayList<TwoDimCoordinate>();
		for(int i = 0; i<tabCol.length; i++) {
			l.add(new TwoDimCoordinate(tabRow[i], tabCol[i]));
		}
		return l;
	}
	public static boolean isIn(int row, int col, int nbRow, int nbCol) {
		return col<nbCol && 0<=col && 0<=row && row <nbRow;
	}
	
	public static ArrayList<TwoDimCoordinate> buildRandomPath(int nbRow, int nbCol) {
		Random r = new Random();
		
		ArrayList<TwoDimCoordinate> l = new ArrayList<TwoDimCoordinate>();
		TwoDimCoordinate pos = new TwoDimCoordinate(0,0);
		while(Main.isIn(pos.row(), pos.column(), nbRow, nbCol)) {
			//System.out.println("bob");
			l.add(pos);
			if(r.nextBoolean()) {
				if(r.nextBoolean()) {
					pos = new TwoDimCoordinate(pos.row()+1, pos.column());
				} else {
					pos = new TwoDimCoordinate(pos.row()+1, pos.column());
				}
				
			} else {
				pos = new TwoDimCoordinate(pos.row(), pos.column()+1);
			}
		}
		return l;
	}
	
		public static void main(String args[]){
			
			//Path2DCoord c1 = new Path2DCoord(1, 0);
			//Path2DCoord c2 = new Path2DCoord(1, 0);
			//System.out.println(c1.equals(c2));
			
			int nbRow = 30;
			int nbCol = 30;
			int tabRowPath[] = {0,0,0,0,1,2,3,4,5,5,4,4,5,5,5,5,6,7,8,9,9,9};
			int tabColPath[] = {0,1,2,3,3,3,3,3,3,4,4,5,5,6,7,8,8,8,8,8,9,10};
			
			ConcreteModel m;
			ArrayList<TwoDimCoordinate> l = Main.buildRandomPath(nbRow, nbCol);
			//ArrayList<TwoDimCoordinate> l = Main.buildPath(tabRowPath, tabColPath);
			try {
				m = new ConcreteModel(nbRow, nbCol, l);
			} catch (CyclingPathException e) {
				m=null;
				e.printStackTrace();
				System.exit(1);
			}
			
			try {
				VueJeu v = new VueJeu(m, m.getPosSystem(),m.getPath());
			} catch (PathOutOfField e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				System.exit(1);
			}
			//VueMenu v = new VueMenu();
		}
		public Collection<MovingObject<Path2DCoord>> launchWave() {
			// TODO Auto-generated method stub
			return null;
		}
}
