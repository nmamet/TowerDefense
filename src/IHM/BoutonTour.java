package IHM;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class BoutonTour extends JButton{
	
	private BufferedImage sprite; 
	
	public BoutonTour(int row, int column) throws IOException{
		super();	
		sprite = sprite.getSubimage(64*column, 64*row, 64, 64);
		//ImageIcon i = new ImageIcon(sprite);
		
		setIcon(new ImageIcon(sprite));
		//Color c = getBorder().getBorderInsets(c).getLineColor();
		//setBorder(BorderFactory.createLineBorder(new Color(0f,0f,0f, 1f), 10));
		//setBorderPainted(false);
		setFocusPainted(false);
		//setContentAreaFilled(false);
	}
}
