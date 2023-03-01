package obstacles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;

/**
 * Representing an Object inside the game
 * @author kchri, laurinelias
 *
 */
public abstract class GameObject {

	public Shape shape;
	
	/**
	 * Constructor
	 */
	public GameObject() {
		
	}	
	
	/**
	 * Constructor
	 * @param shape
	 * @param sprite img
	 * @param x spawn x
	 * @param y spawn y
	 */
	public GameObject(Shape shape, String sprite, int x, int y) {
		setShape(shape);
		setPosition(x,y);	
		setSprite(sprite);
		
	}
	
	public Shape getShape() {
		return shape;
	};
	
	public void setShape(Shape shape) {
		this.shape = shape;
	};
	
	/**
	 * Default sprite green
	 * @param url
	 */
	public void setSprite(String url) {
		if(url == "") {
			shape.setFill(Color.GREEN);
		} else {			
			shape.setFill(new ImagePattern(new Image(url)));
		}
	}
	
	public void setPosition(int x, int y) {
		shape.setTranslateX(x);
		shape.setTranslateY(y);
	}
	
	/**
	 * Each gameObject has its own update methode wich can be
	 * used to move it or interact
	 */
	public void update() {
		
	}
 
}
