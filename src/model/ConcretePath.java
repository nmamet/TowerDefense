package model;

import java.util.ArrayList;
import java.util.List;

import ihm.CyclingPathException;

public class ConcretePath implements Path<Path2DCoord>{
	
	private List<Path2DCoord> path;
	
	public Path2DCoord startingPos() {
		if(path.size() == 0) {
			System.out.println("path null returned on startingPos() call");
			return null;
		}
		return path.get(0);
	}
	
	private ConcretePath(ArrayList<TwoDimCoordinate> tabPath) {
		path = new ArrayList<Path2DCoord>();
		Path2DCoord prec = new Path2DCoord(tabPath.get(0));
		Path2DCoord ci;
		for(int i =0;i<tabPath.size()-1;i++) {
			ci = new Path2DCoord(tabPath.get(i+1));
			prec.setNextPos(ci);
			path.add(prec);
			prec = ci;
		}
		prec.setNextPos(prec);
		path.add(prec);
	}
	
	protected static ConcretePath buildPath(TwoDimArraySystem ps, ArrayList<TwoDimCoordinate> tabPath) {
		if(!ps.isInSystem(tabPath.get(0))){
			System.out.println("path null");
			return null;
		}
		TwoDimCoordinate prec = tabPath.get(0);
		TwoDimCoordinate ci;
		for(int i=1;i<tabPath.size();i++){
			ci = tabPath.get(i);
			if(!ps.isInSystem(ci) || !ps.areNeighbours(prec, ci)){
				System.out.println("path null 2");
				System.out.println("row : "+ci.row() + ", col : "+ci.column());
				return null;
			}
			prec = ci;
		}
		return new ConcretePath(tabPath);
	}
}
