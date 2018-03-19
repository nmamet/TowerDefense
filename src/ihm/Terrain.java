package ihm;

import java.awt.Color;
import java.awt.Component;
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
	private PositioningSystem<TwoDimCoordinate> ps;
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
	/*
	@Override
	public void paintComponent(Graphics g){
		CustomGridLayout l = (CustomGridLayout) getLayout();
		//System.out.println("paint1 "+ l.getRows() + " " + l.getColumns() );
		super.paintComponent(g);
		//System.out.println("paint2 "+ l.getRows() + " " + l.getColumns() );
		
	}
	
	@Override
	public Component add(Component c){
		//System.out.println("terrain.add");
		if(c instanceof Unit){
			System.out.println("terrain.add unit");
			
		}
		
		return super.add(c);
	}
	*/
}
