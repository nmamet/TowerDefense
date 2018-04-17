package ihm;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import model.Model;
import model.Path2DCoord;
import model.TwoDimCoordinate;

public class GoldLabel extends JLabel {
	GoldLabel(Model<Path2DCoord> m){
		super();
		JLabel l = this;

		Timer t = new Timer(true);
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				l.setText(""+m.getGold());
			}
		}, 0,10);
	}
}
