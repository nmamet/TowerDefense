package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VueMenu extends JFrame{
	private int defaultWidth = 100;
	private int defaultHeight = 160;
	
	public VueMenu() {
		super();
		JPanel panel = new JPanel();
		Dimension d = new Dimension(100,40);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		JButton buttonGame = new JButton("Lancer une partie");
		buttonGame.setPreferredSize(d);
		JButton buttonLvl = new JButton("Choisir un terrain");
		buttonLvl.setPreferredSize(d);
		JButton buttonBuilder = new JButton("Lancer le créateur de niveau");
		buttonBuilder.setPreferredSize(d);
		JButton buttonSettings = new JButton("Options");
		buttonSettings.setPreferredSize(d);
		panel.add(buttonGame);
		panel.add(buttonLvl);
		panel.add(buttonBuilder);
		panel.add(buttonSettings);
		getContentPane().add(panel, BorderLayout.CENTER);
		setTitle("Tower Defense");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(defaultWidth, defaultHeight);
		setVisible(true);
	}
}
