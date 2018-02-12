package Model;

public class Terrain implements Field {
	private int nbCol;
	private int nbRow;
	private Case[][] tab;
	private int colStart;
	private int rowStart;
	private int colEnd;
	private int rowEnd;
	
	
	private Terrain(int nbRow, int nbCol){
		this.nbRow = nbRow;
		this.nbCol = nbCol;
		tab = new Case[nbRow][];
		for(int i = 0;i<nbRow;i++){
			tab[i] = new Case[nbCol];
			for(int j =0;j<nbCol;j++){
				Case c = new Case();
				tab[i][j] = c;
				if(i!=0){
					c.setNorth(tab[i-1][j]);
					tab[i-1][j].setSouth(c);
				}
				if(j!=0){
					c.setWest(tab[i][j-1]);
					tab[i][j-1].setEast(c);
				}
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
		Case c = t.tab[t.rowStart][t.colStart];
		int rowIMoinsUn = t.rowStart;
		int colIMoinsUn = t.colStart;
		int rowI;
		int colI;
		for(int i=1;i<length;i++){
			rowI = tabRowPath[i];
			colI = tabRowPath[i];
			if(!t.isInField(rowI, colI) || !t.isNeighbour(rowIMoinsUn, colIMoinsUn, rowI, colI)){
				return null;
			}
			Case tmp = t.tab[rowI][colI];
			c.setNext(tmp);
			c = tmp;
			rowIMoinsUn = rowI;
			colIMoinsUn = colI;
		}
		t.colEnd = tabColPath[length-1];
		t.rowEnd = tabRowPath[length-1];
		return t;
	}
	
	@Override
	public FieldCell getCell(int row, int col) throws OutOfFieldException {
		if(isInField(row, col)){
			return tab[row][col];
		} else {
			throw new OutOfFieldException();
		}
	}
	
	private boolean isInField(int row, int col){
		return col<nbCol && 0<=col && 0<=row && row <=nbRow;
	}
	
	private boolean isNeighbour(int row1, int col1, int row2, int col2){
		return (row1 == row2 && (col1+1 == col2 || col1-1 == col2) || (col1 == col2 && (row1+1 == row2 || row1-1 == row2)));
	}
}
