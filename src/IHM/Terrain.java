package IHM;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import IHM.Case.CaseType;

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
			return null;
		}
		int length = tabColPath.length;
		if(length != tabRowPath.length){
			return null;
		}
		Terrain t = new Terrain(nbRow, nbCol);
		t.colStart = tabColPath[0];
		t.rowStart = tabRowPath[0];
		if(!t.isInField(t.rowStart, t.colStart)){
			return null;
		}
		int rowIMoinsUn = t.rowStart;
		int colIMoinsUn = t.colStart;
		int rowI;
		int colI;
		Case c = t.cellList[t.rowStart][t.colStart];
		for(int i=1;i<length;i++){
			rowI = tabRowPath[i];
			colI = tabRowPath[i];
			if(!t.isInField(rowI, colI) || !t.isNeighbour(rowIMoinsUn, colIMoinsUn, rowI, colI)){
				return null;
			}
			Case tmp = t.cellList[tabRowPath[i]][tabColPath[i]];
			int dRow = rowI-rowIMoinsUn;
			int dCol = colI-colIMoinsUn;
			switch(dRow){
			case 1: 
			}
			c.setCaseType(CaseType.EAST_NORTH);
			c = tmp;
		}
		t.colEnd = tabColPath[length-1];
		t.rowEnd = tabRowPath[length-1];
		c.setCaseType(CaseType.EAST_NORTH);
		return t;
	}
	
	private boolean isInField(int row, int col){
		return col<colCount && 0<=col && 0<=row && row <=rowCount;
	}
	
	private boolean isNeighbour(int row1, int col1, int row2, int col2){
		return (row1 == row2 && (col1+1 == col2 || col1-1 == col2) || (col1 == col2 && (row1+1 == row2 || row1-1 == row2)));
	}
}
