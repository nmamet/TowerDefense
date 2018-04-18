package ihm;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class BoutonTour extends JButton{
	
	private BufferedImage sprite; 
	
	public BoutonTour(Image i, VueJeu vj) throws IOException{
		super();	
		setIcon(new ImageIcon(i));
		setFocusPainted(false);
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vj.buttonclicked = true;
				//System.out.println("buttonturret clicked");
			}
		});
	}
}
