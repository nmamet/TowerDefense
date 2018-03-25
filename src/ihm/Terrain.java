package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
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
	private TwoDimArraySystem ps;
	private Path<Path2DCoord> path;
	private int colEnd;
	private int rowEnd;
	private ArrayList<Unit> units;
	
	Terrain(TwoDimArraySystem ps, Path<Path2DCoord> path) throws PathOutOfField, CyclingPathException{
		super();
		this.ps = ps;
		this.path = path;
		int rowCount = ps.nbOfRows();
		int colCount = ps.nbOfColumns();
		cellList  = new Case[rowCount][];
		GridLayout layout = new GridLayout(rowCount, colCount, 0, 0);
		setLayout(layout);
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
	
	public Dimension getCellSize() {
		this.getLayout().layoutContainer(this);
		System.out.println("calcul de la taille des cases");
		System.out.println("width : " + getWidth()/ps.nbOfColumns());
		System.out.println("height : " + getHeight()/ps.nbOfRows());
		System.out.println("insets : " + getInsets());
		//System.out.println(getWidth()/30);
		//System.out.println(getHeight()/30);
		//System.out.println(ps.nbOfColumns());
		//System.out.println(ps.nbOfRows());
		return new Dimension(getWidth()/ps.nbOfColumns(), getHeight()/ps.nbOfRows());
	}
}
