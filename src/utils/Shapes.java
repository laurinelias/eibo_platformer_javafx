package utils;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

/**
 * Utilitie class for holding helpfull tools
 * @author kchri,laurinelias
 *
 */
public class Shapes {

	/**
	 * Getting the x and y coordinate from the desired point of the rectangle
	 * @param rectangle
	 * @param point "top_left","top_right","bottom_left", "bottom_right"
	 * @return desired point
	 */
	public static Point2D getPointFromRec(Rectangle rectangle, String point) {		
		switch(point) {
			case "top_left" : return new Point2D((int)rectangle.getTranslateX(),(int)rectangle.getTranslateY());
			case "top_right" : return new Point2D((int)rectangle.getTranslateX() + (int)rectangle.getWidth(),(int)rectangle.getTranslateY());
			case "bottom_left" : return new Point2D((int)rectangle.getTranslateX(),(int)rectangle.getTranslateY() + (int)rectangle.getHeight());
			case "bottom_right" : return new Point2D((int)rectangle.getTranslateX() + (int)rectangle.getWidth(),(int)rectangle.getTranslateY() + (int)rectangle.getHeight());
			default : return new Point2D((int)rectangle.getTranslateX(),(int)rectangle.getTranslateY());
		}

	}
}
