package obstacles;

import application.Settings;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

/**
 * GameObject -> an obstacle like a spike, death on collision
 * @author kchri,laurinelias
 *
 */
public class Spike extends GameObject {
	
	/**
	 * Constructor
	 * @param spriteUrl
	 * @param x spawn x
	 * @param y spawn y
	 */
	public Spike(String spriteUrl,int x, int y) {
		super(createTriangle(new Point2D(Settings.PIXEL_SIZE/2,0),new Point2D(0,Settings.PIXEL_SIZE), new Point2D(Settings.PIXEL_SIZE,Settings.PIXEL_SIZE)), spriteUrl, x, y);
	
	}
	
	/**
	 * creating an triangle
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return
	 */
	static Polygon createTriangle(Point2D p1, Point2D p2, Point2D p3){
	    Point2D centre = p1.midpoint(p2).midpoint(p3);
	    Point2D p1Corrected = p1.subtract(centre);
	    Point2D p2Corrected = p2.subtract(centre);
	    Point2D p3Corrected = p3.subtract(centre);
	    Polygon polygon = new Polygon(
	            p1Corrected.getX(), p1Corrected.getY(),
	            p2Corrected.getX(), p2Corrected.getY(),
	            p3Corrected.getX(), p3Corrected.getY()
	    );
	    polygon.setLayoutX(centre.getX());
	    polygon.setLayoutY(centre.getY());
	    return polygon;
	}
	
}
