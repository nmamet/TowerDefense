package ihm;

import java.awt.Point;

import javax.swing.JComponent;

import model.Field;

public abstract class MovingGraphic extends JComponent{
	public abstract Point getGraphicPosition();
}
