package ihm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


//Découpe une image en carré selon une grille
public class SpriteBuilder {
	private int spriteSize;
	private int nbSprites;
	private BufferedImage[] sprites;
	
	//sprSize : taille ds sous carrés
	//nbRow : number of rows
	//nbCol : number of columns
	//nb : number of squares (if nb < nbRow*nbCol, a few squares will be ignored
	//filename : name of the image file
	public SpriteBuilder(int sprSize, int nbRow, int nbCol, int nb, String fileName) throws IOException {
		spriteSize = sprSize;
		if(nb>nbRow*nbCol || nb<=(nbRow-1)*nbCol){
			System.out.print("Erreur lors de la découpe de l'image : ");
			System.out.print(fileName);
			System.out.println("; taille incohérente.");
			System.exit(1);
		}
		nbSprites = nb;
		sprites = new BufferedImage[nb];
		BufferedImage tmp = ImageIO.read(new File(fileName));
		for(int i = 0;i<nbRow-1;i++){
			for (int j =0;j<nbCol;j++){
				sprites[i*nbCol + j] = tmp.getSubimage(j*sprSize, i*sprSize, sprSize, sprSize);
			}
		}
		for(int j=0;j<nbCol + nb - nbCol*nbRow;j++){
			sprites[(nbRow-1)*nbCol + j] = tmp.getSubimage(j*sprSize, (nbRow-1)*sprSize, sprSize, sprSize);
		}
	}
	
	public BufferedImage getSprite(int n){
		return sprites[n];
	}
	
}
