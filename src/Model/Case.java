package Model;

import java.util.ArrayList;

public class Case implements FieldCell, PathCell {
	private FieldCell north;
	private FieldCell west;
	private FieldCell east;
	private FieldCell south;
	private PathCell next;
	
	public Case(){
		
	}
	
	public boolean setNorth(FieldCell tab){
		boolean r = false;
		if(north == null && next == null){
			north = tab;
			r = true;
		}
		return r;
	}
	
	public boolean setWest(FieldCell n){
		boolean r = false;
		if(west == null && next == null){
			west = n;
			r = true;
		}
		return r;
	}
	
	public boolean setEast(FieldCell n){
		boolean r = false;
		if(east== null && next == null){
			east = n;
			r = true;
		}
		return r;
	}
	
	public boolean setSouth(FieldCell n){
		boolean r = false;
		if(south == null && next == null){
			south = n;
			r = true;
		}
		return r;
	}
	
	@Override
	public ArrayList<FieldCell> getNeighbour() {
		ArrayList<FieldCell> l = new ArrayList<FieldCell>();
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
	public PathCell getNext() {
		return next;
	}

	public boolean setNext(PathCell n){
		boolean r = false;
		if(next == null && n != null && getNeighbour().contains(n)){
			next = n;
			r = true;
		}
		return r;
	}
}
