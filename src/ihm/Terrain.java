package ihm;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ihm.Case.Direction;
import model.Cell;
import model.Field;
import model.OutOfFieldException;
import model.Path;
import model.Path2DCoord;
import model.PathPosition;
import model.Position;
import model.PositioningSystem;
import model.TwoDimArraySystem;
import model.TwoDimCoordinate;

public class Terrain extends JPanel implements Field<TwoDimCoordinate, Case>{
	private Case[][] cellList;
	private PositioningSystem<TwoDimCoordinate> ps;
	private Path<Path2DCoord> path;
	private int colEnd;
	private int rowEnd;
	
	Terrain(TwoDimArraySystem ps, Path<Path2DCoord> path) throws PathOutOfField, CyclingPathException{
		super();
		this.ps = ps;
		this.path = path;
		int rowCount = ps.nbOfRows();
		int colCount = ps.nbOfColumns();
		cellList  = new Case[rowCount][];
		setLayout(new GridLayout(rowCount, colCount, -1, -1));
		for(int row = 0;row<rowCount;row++){
			cellList[row] = new Case[colCount];
			for(int col = 0;col<colCount;col++){
				Case c = new Case(ps, new TwoDimCoordinate(row, col));
				c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				add(c);
				cellList[row][col] = c;
			}
		}

		Path2DCoord pos = path.startingPos();
		Path2DCoord nextPos = pos.nextPos();
		while(!pos.equals(nextPos)) {
			//System.out.println("bob");
			try {
				if(nextPos.equals(pos.north())) {
					getCell(pos).setDirectionOut(Direction.NORTH);
					getCell(nextPos).setDirectionIn(Direction.SOUTH);
				} else if(nextPos.equals(pos.south())) {
					getCell(pos).setDirectionOut(Direction.SOUTH);
					getCell(nextPos).setDirectionIn(Direction.NORTH);
				} else if(nextPos.equals(pos.east())) {
					getCell(pos).setDirectionOut(Direction.EAST);
					getCell(nextPos).setDirectionIn(Direction.WEST);
				} else if(nextPos.equals(pos.west())) {
					getCell(pos).setDirectionOut(Direction.WEST);
					getCell(nextPos).setDirectionIn(Direction.EAST);
				}
			} catch(OutOfFieldException e) {
				throw new PathOutOfField();
			}
			pos = nextPos;
			nextPos = pos.nextPos();
		}
		
	}
	/*	
	public static Terrain buildTerrain(TwoDimArraySystem ps, Path<Path2DCoord> path) throws PathOutOfField {
		
		Terrain t = new Terrain(ps, path);
		
		/*
		for(int i=1;i<path;i++){
			rowI = tabRowPath[i];
			colI = tabColPath[i];
			if(!t.isInField(rowI, colI) || !t.isNeighbour(rowIMoinsUn, colIMoinsUn, rowI, colI)){
				return null;
			}
			Case tmp = t.cellList[tabRowPath[i]][tabColPath[i]];
			int dRow = rowI-rowIMoinsUn;
			int dCol = colI-colIMoinsUn;
			switch(dRow){
			case 1: 
				tmp.setDirectionIn(Direction.NORTH);
				c.setDirectionOut(Direction.SOUTH);
				break;
			case -1: 
				tmp.setDirectionIn(Direction.SOUTH);
				c.setDirectionOut(Direction.NORTH);
				break;
			case 0 : 
				switch(dCol) {
				case 1:
					tmp.setDirectionIn(Direction.WEST);
					c.setDirectionOut(Direction.EAST);
					break;
				case -1:
					tmp.setDirectionIn(Direction.EAST);
					c.setDirectionOut(Direction.WEST);
					break;
				}
			}
			//c.setCaseType(CaseType.EAST_NORTH);
			c = tmp;
			rowIMoinsUn = rowI;
			colIMoinsUn = colI;
		}
		t.colEnd = tabColPath[length-1];
		t.rowEnd = tabRowPath[length-1];
		
		return t;
	}*/

	@Override
	public Case getCell(TwoDimCoordinate p) throws OutOfFieldException {
		if(!ps.isInSystem(p)) {
			throw new OutOfFieldException();
		}
		return cellList[p.row()][p.column()];
	}

	@Override
	public PositioningSystem<TwoDimCoordinate> getSystem() {
		return ps;
	}
}
