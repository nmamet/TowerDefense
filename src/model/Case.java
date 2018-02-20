package model;

import java.util.ArrayList;

public class Case implements Cell, Path {
	private Cell north;
	private Cell west;
	private Cell east;
	private Cell south;
	private Path next;
	
	public Case(){
		
	}
	
	public boolean setNorth(Cell tab){
		boolean r = false;
		if(north == null && next == null){
			north = tab;
			r = true;
		}
		return r;
	}
	
	public boolean setWest(Cell n){
		boolean r = false;
		if(west == null && next == null){
			west = n;
			r = true;
		}
		return r;
	}
	
	public boolean setEast(Cell n){
		boolean r = false;
		if(east== null && next == null){
			east = n;
			r = true;
		}
		return r;
	}
	
	public boolean setSouth(Cell n){
		boolean r = false;
		if(south == null && next == null){
			south = n;
			r = true;
		}
		return r;
	}
	
	@Override
	public ArrayList<Cell> getNeighbour() {
		ArrayList<Cell> l = new ArrayList<Cell>();
		if(north != null){
			l.add(north);
		}
		if(west != null){
			l.add(west);
		}
		if(east != null){
			l.add(east);
		}
		if(south != null){
			l.add(south);
		}
		return l;
	}

	@Override
	public Path getNext() {
		return next;
	}

	public boolean setNext(Path n){
		boolean r = false;
		if(next == null && n != null && getNeighbour().contains(n)){
			next = n;
			r = true;
		}
		return r;
	}
}
