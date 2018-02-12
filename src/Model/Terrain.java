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
		for(int i=1;i<length;i++){
			if(!t.isInField(tabRowPath[i], tabColPath[i])){
				return null;
			}
			Case tmp = t.tab[tabRowPath[i]][tabColPath[i]];
			c.setNext(tmp);
			c = tmp;
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
}
