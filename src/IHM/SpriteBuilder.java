package IHM;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteBuilder {
	private int spriteSize;
	private int nbSprites;
	private BufferedImage[] sprites;
	
	public SpriteBuilder(int sprSize, int nbRow, int nbCol, int nb, String fileName) throws IOException {
		spriteSize = sprSize;
		nbSprites = nb;
		sprites = new BufferedImage[nb];
		BufferedImage tmp = ImageIO.read(new File(fileName));
		for(int i = 0;i<nbRow-1;i++){
			for (int j =0;j<nbCol;i++){
				
			}
		}
	}
	
}
