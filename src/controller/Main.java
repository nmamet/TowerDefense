package controller;

import ihm.VueJeu;
import ihm.VueMenu;
import model.Terrain;

public class Main {
		public static void main(String args[]){
			int tabRowPath[] = {0,0,0,0,1,2,3,4,5,5,4,4,5,5,5,5,6,7,8,8,8,9};
			int tabColPath[] = {0,1,2,3,3,3,3,3,3,4,4,5,5,6,7,8,8,8,8,7,6,6};
			Terrain t = Terrain.buildTerrain(30, 30, tabRowPath, tabColPath);
			if(t==null) {
				System.out.println("t est null");
			}
			//VueJeu v = new VueJeu(30,30,tabRowPath,tabColPath);
			VueMenu v = new VueMenu();
		}
}
