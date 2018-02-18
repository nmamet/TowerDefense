package ihm;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class BoutonTour extends JButton{
	
	private BufferedImage sprite; 
	
	public BoutonTour(BufferedImage i) throws IOException{
		super();	
		setIcon(new ImageIcon(i));
		setFocusPainted(false);
	}
}
