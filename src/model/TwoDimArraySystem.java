package model;

import java.util.ArrayList;

public class TwoDimArraySystem implements PositioningSystem<TwoDimCoordinate>{

	private int numberOfRows;
	private int numberOfcolumns;
	
	TwoDimArraySystem(int nbRow, int nbCol) {
		numberOfRows = nbRow;
		numberOfcolumns = nbCol;
	}
	
	public int nbOfRows() {return numberOfRows;}
	public int nbOfColumns() {return numberOfcolumns;}
	
	@Override
	public boolean isInSystem(TwoDimCoordinate c) {
		return c.column()<numberOfcolumns && 0<=c.column() && 0<=c.row() && c.row() <numberOfRows;
	}
	
	//précondition : c est dans le système
	@Override
	public Iterable<TwoDimCoordinate> neighbours(TwoDimCoordinate c) {
		if(!isInSystem(c)) {
			System.out.println("Voisins : case en dehors du systeme");
			return null;
		}
		ArrayList<TwoDimCoordinate> l = new ArrayList<TwoDimCoordinate>();
		//Code pour compter les diagonales comme voisines
		/*
		for(int i =-1;i<=1;i++) {
			for(int j =-1;j<=1;j++) {
				if((i!=0 || j!=0) && isInSystem(c)) {
					l.add(new TwoDimCoordinate(c.row()+1,c.column()+j));
				}
			}
		}*/
		
		if(c.row()>0) {l.add(new TwoDimCoordinate(c.row()-1, c.column()));}
		if(c.row()<numberOfRows-1) {l.add(new TwoDimCoordinate(c.row()+1, c.column()));}
		if(c.column()>0) {l.add(new TwoDimCoordinate(c.row(), c.column()-1));}
		if(c.column()<numberOfcolumns-1) {l.add(new TwoDimCoordinate(c.row(), c.column()+1));}
		return l;
	}
	
	public boolean areNeighbours(TwoDimCoordinate c1, TwoDimCoordinate c2) {
		return ((c1.row() == c2.row() && (c1.column()+1 == c2.column() || c1.column()-1 == c2.column())) 
				|| (c1.column() == c2.column() && (c1.row()+1 == c2.row() || c1.row()-1 == c2.row())));
	}
}
