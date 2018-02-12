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
		Case c = t.cellList[t.rowStart][t.colStart];
		for(int i=1;i<length;i++){
			if(!t.isInField(tabRowPath[i], tabColPath[i])){
				return null;
			}
			Case tmp = t.cellList[tabRowPath[i]][tabColPath[i]];
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
}
