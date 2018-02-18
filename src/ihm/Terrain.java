package ihm;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ihm.Case.Direction;

public class Terrain extends JPanel{
	int rowCount;
	int colCount;
	private Case[][] cellList;
	private int colStart;
	private int rowStart;
	private int colEnd;
	private int rowEnd;
	
	private Terrain(int rowCount, int colCount){
		super();
		this.rowCount = rowCount;
		this.colCount = colCount;
		cellList  = new Case[rowCount][];
		setLayout(new GridLayout(rowCount, colCount, -1, -1));
		for(int i = 0;i<rowCount;i++){
			cellList[i] = new Case[colCount];
			for(int j = 0;j<colCount;j++){
				Case c = new Case();
				c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				add(c);
				cellList[i][j] = c;
			}
		}
	}
		
	public static Terrain buildTerrain(int nbRow, int nbCol, int[] tabRowPath, int[] tabColPath){
		if(nbRow <= 0 || nbCol <= 0){
			System.out.println("Limites négatives");
			return null;
		}
		int length = tabColPath.length;
		if(length != tabRowPath.length){
			System.out.println("les tableaux du path sont de taille différentes");
			return null;
		}
		Terrain t = new Terrain(nbRow, nbCol);
		t.colStart = tabColPath[0];
		t.rowStart = tabRowPath[0];
		if(!t.isInField(t.rowStart, t.colStart)){
			System.out.println("Erreur lors de l'initialisation du chemin");
			return null;
		}
		int rowIMoinsUn = t.rowStart;
		int colIMoinsUn = t.colStart;
		int rowI;
		int colI;
		Case c = t.cellList[t.rowStart][t.colStart];
		for(int i=1;i<length;i++){
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
	}
	
	private boolean isInField(int row, int col){
		return col<colCount && 0<=col && 0<=row && row <=rowCount;
	}
	
	private boolean isNeighbour(int row1, int col1, int row2, int col2){
		return ((row1 == row2 && (col1+1 == col2 || col1-1 == col2)) || (col1 == col2 && (row1+1 == row2 || row1-1 == row2)));
	}
}
