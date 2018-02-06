package IHM;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Terrain extends JPanel{
	int rowCount = 30;
	int colCount = 30;
	private Case[][] cellList;
	
	public Terrain(){
		super();
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
}
