package Controller;

import IHM.Vue;
import Model.Terrain;

public class Main {
		public static void main(String args[]){
			//Vue v = new Vue();
			int tabRowPath[] = {0,0,0,0,1,2,3,4,5,5,4};
			int tabColPath[] = {0,1,2,3,3,3,3,3,3,4,4};
			System.out.println(tabRowPath.length);
			Terrain t = Terrain.buildTerrain(30, 30, tabRowPath, tabColPath);
			Vue v = new Vue(30,30,tabRowPath,tabColPath);
		}
}
