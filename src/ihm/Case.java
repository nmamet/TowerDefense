package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;

import model.Cell;
import model.MovingObject;
import model.Path2DCoord;
import model.Position;
import model.PositioningSystem;
import model.TwoDimCoordinate;

class Case implements Cell<TwoDimCoordinate>{
	
	private Direction in;
	private Direction out;
	//a passer static un jour
	private PositioningSystem<TwoDimCoordinate> ps;
	private TwoDimCoordinate pos;
	private static CoordinateConverter converter;
	
	public enum Direction{
		NORTH,WEST,SOUTH,EAST,NONE
	}
	
	public Case(PositioningSystem<TwoDimCoordinate> ps, TwoDimCoordinate pos){
		super();
		in = Direction.NONE;
		out = Direction.NONE;
		this.ps = ps;
		this.pos = pos;
	}
	
	public static void setConverter(CoordinateConverter cc){
		converter = cc;
	}
	
	public void setDirectionIn(Direction in) throws CyclingPathException{
		if(this.in != Direction.NONE) {
			throw new CyclingPathException();
		}
		this.in = in;
	}
	
	public void setDirectionOut(Direction out) throws CyclingPathException{
		if(this.out != Direction.NONE) {
			throw new CyclingPathException();
		}
		this.out = out;
	}
	
	private static Direction opposite(Direction d) {
		Direction ret;
		switch(d) {
		case NORTH: ret = Direction.SOUTH; break;
		case SOUTH: ret = Direction.NORTH; break;
		case WEST: ret = Direction.EAST; break;
		case EAST: ret = Direction.WEST; break;
		case NONE: ret = Direction.NONE; break;
		default: ret = null;
		}
		return ret;
	}
	
	private Rectangle unitPos(Direction d) {
		Rectangle r;
		Point location = converter.fieldToGraphic(pos);
		Dimension dimension  = converter.getCellSize();
		switch(d) {
		case NORTH: r = new Rectangle(location.x+dimension.width/4,
										location.y, 
										dimension.width/2, 
										dimension.height/4
										); break;
		case SOUTH: r = new Rectangle(location.x+dimension.width/4,
										location.y+dimension.height/4,
										dimension.width/2,
										3*dimension.height/4-1
										); break;
		case WEST: r = new Rectangle(location.x, 
										location.y+dimension.height/4,
										dimension.width/4,
										dimension.height/2
										); break;
		case EAST: r = new Rectangle(location.x+dimension.width/4,
										location.y+dimension.height/4,
										3*dimension.width/4-1,
										dimension.height/2
										);break;
		default: r = null;
		}
		return r;
	}
	
	private Rectangle rectangleSide(Direction d) {
		Rectangle r;
		Point location = converter.fieldToGraphic(pos);
		Dimension dimension  = converter.getCellSize();
		switch(d) {
		case NORTH: r = new Rectangle(location.x+dimension.width/4,
										location.y, 
										dimension.width/2, 
										dimension.height/4
										); break;
		case SOUTH: r = new Rectangle(location.x+dimension.width/4,
										location.y+3*dimension.height/4,
										dimension.width/2,
										dimension.height/4-1
										); break;
		case WEST: r = new Rectangle(location.x, 
										location.y+dimension.height/4,
										dimension.width/4,
										dimension.height/2
										); break;
		case EAST: r = new Rectangle(location.x+3*dimension.width/4,
										location.y+dimension.height/4,
										dimension.width/4-1,
										dimension.height/2
										);break;
		default: r = null;
		}
		return r;
	}
	
	private Rectangle rectangleMiddle() {
		Point location = converter.fieldToGraphic(pos);
		Dimension dimension  = converter.getCellSize();
		return new Rectangle(location.x+dimension.width/4,
								location.y+dimension.height/4,
								dimension.width/2,
								dimension.height/2
								);
	}
	
	public void paintCase(Graphics g){
		//les differents -1 sont lÃ  pour ne pas ecrire dans la case suivante
		Point location = converter.fieldToGraphic(pos);
		//System.out.println("location : "+location);
		Dimension dimension  = converter.getCellSize();
		//System.out.println("dimension : " + dimension);
		//System.out.println("Dessin de la case a la position "+location);
		
		g.setColor(Color.BLACK);
		g.drawRect(location.x, location.y, dimension.width-1, dimension.height-1);
		g.setColor(Color.GRAY);
		g.fillRect(location.x, location.y, dimension.width-1, dimension.height-1);
		g.setColor(Color.WHITE);
		if(in != Direction.NONE) {
			Rectangle r = rectangleSide(in);
			g.fillRect(r.x, r.y, r.width, r.height);
			r = rectangleMiddle();
			g.fillRect(r.x, r.y, r.width, r.height);
			if(out != Direction.NONE) {
				r = rectangleSide(out);
				g.fillRect(r.x, r.y, r.width, r.height);
			} else {
				r = rectangleSide(opposite(in));
				g.fillRect(r.x, r.y, r.width, r.height);
			}
		} else if(out != Direction.NONE) {
			Rectangle r = rectangleSide(out);
			g.fillRect(r.x, r.y, r.width, r.height);
			r = rectangleMiddle();
			g.fillRect(r.x, r.y, r.width, r.height);
			r = rectangleSide(opposite(out));
			g.fillRect(r.x, r.y, r.width, r.height);
		}
		
		//trace le contour noir du terrain en haut et a gauche
		g.setColor(Color.BLACK);
		if(location.x == 0){
			g.drawLine(0, location.y, 0, location.y+dimension.height-1);
		}
		if(location.y == 0){
			g.drawLine(location.x, 0, location.x+dimension.width-1, location.y);
		}
	}
	
	@Override
	public PositioningSystem<TwoDimCoordinate> getSystem() {
		return ps;
	}

	@Override
	public TwoDimCoordinate pos() {
		return pos;
	}
	
	//donne le point "le long du rectangle" r, selon l'axe x ou y, en partant du début ou de la fin
	//le rapport k définit le point renvoyé, 0<=k<=1
	private static Point rectanglePart(Rectangle r, float k, boolean axeX) {
		if(k < 0 || k > 1) {
			System.out.println("erreur de rapport dans le calcul de la position");
			System.exit(1);
		}
		Point p;
		if(axeX) {
			p = new Point(r.x + (int) (r.width * k),r.y);
		} else {
			p = new Point(r.x, r.y + (int) (r.height * k));
		}
		return p;
	}
	
	private Point switchOut(float k) {
		Point graphPos = null;
		Rectangle r = unitPos(out);
		switch(out){
		case NORTH: 
			graphPos = rectanglePart(r, 1-k, false);
			//graphPos.y -= converter.getCellSize().height/2-2;
			break;
		case SOUTH: graphPos = rectanglePart(r, k, false);break;
		case WEST: 
			graphPos = rectanglePart(r, 1-k, true);
			//graphPos.x -= converter.getCellSize().width/2-2;
			break;
		case EAST: graphPos = rectanglePart(r, k, true);break;
		default: ;
		}
		return graphPos;
	}
	
	private Point switchIn(float k) {
		Point graphPos = null;
		Rectangle r = unitPos(in);
		switch(in){
		case NORTH: graphPos = rectanglePart(r, k, false);break;
		case SOUTH: 
			graphPos = rectanglePart(r, 1-k, false);
			//graphPos.y -= converter.getCellSize().height/2-2;
			break;
		case WEST: graphPos = rectanglePart(r, k, true);break;
		case EAST: 
			graphPos = rectanglePart(r, 1-k, true);
			//graphPos.x -= converter.getCellSize().width/2-2;
			break;
		default: ;
		}
		return graphPos;
	}
	
	public Rectangle movingObjectGraphicFrame(MovingObject<Path2DCoord> mo){
		int maxDist = mo.getPos().maxDist();
		int dist = mo.getDistance();
		if(dist>maxDist) {
			System.out.println("l'unite aurait du bouger vers la prochaine case");
		}
		Point graphPos = null;
		float k = ((float)dist)/maxDist;
		if(in == Direction.NONE){
			if(out == Direction.NONE) {
				//Cas qui n'est pas cense arriver
				System.out.println("Une unite est sur une case qui n'appartient pas au chemin");
				System.exit(1);
			} else {
				//Premiere case du chemin
				graphPos = switchOut(k);
			}
		} else {
			if(out == Direction.NONE) {
				//Derniere case du chemin
				graphPos = switchIn(k);
			} else {
				//Case quelconque du chemin
				if(k> 0.25) {
					//Deuxieme "moitie" de la case
					k = (dist - (float)(maxDist/4)) / (3*((float)maxDist)/4);
					graphPos = switchOut(k);
				} else {
					//Premiere "moitie" de la case
					k = dist / (((float) maxDist)/4);
					graphPos = switchIn(k);
				}
			}
		}
		Dimension dimension = converter.getCellSize();
		Rectangle r = new Rectangle(graphPos.x+1, graphPos.y+1, dimension.width/2-2, dimension.height/2-2);
		return r;
	}
}
