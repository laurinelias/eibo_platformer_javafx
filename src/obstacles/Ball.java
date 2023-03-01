package obstacles;

import application.Settings;
import javafx.scene.shape.Circle;

/**
 * GameObject -> a moving obstacle
 * @author kchri, laurinelias
 *
 */
public class Ball extends GameObject {
	
	public int rotateSpeed = 10;
	public int pixelHeight = 5;
	public int speed = 5;
	public int initalY;
	
	/**
	 * Constructor
	 * @param spriteUrl
	 * @param x spawn x
	 * @param y spawn y
	 */
	public Ball(String spriteUrl,int x, int y) {
		super(new Circle(Settings.PIXEL_SIZE / 4), spriteUrl, x + Settings.PIXEL_SIZE / 2, y + Settings.PIXEL_SIZE / 2);
		initalY = (int)this.shape.getTranslateY();
	}
	
	@Override
	public void update() {
		/* Rotate */
		this.shape.setRotate(this.shape.getRotate() + 10);
		
		
		if((int)this.shape.getTranslateY() < initalY - Settings.PIXEL_SIZE * (pixelHeight - 1)) {
			speed = -speed;
		}
		
		if((int)this.shape.getTranslateY() > initalY ) {
			speed = -speed;
		}

		/* Move up */
		setPosition((int)this.shape.getTranslateX(),(int)this.shape.getTranslateY() - speed);
		
	}
}
