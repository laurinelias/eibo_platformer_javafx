package obstacles;

import application.Settings;
import javafx.scene.shape.Rectangle;

/**
 * GameObject -> like a powerup, only for slowing down
 * @author kchri,laurinelias
 *
 */
public class Slow extends GameObject {
	
	/**
	 * Constructor
	 * @param spriteUrl
	 * @param x spawn x
	 * @param y spawn y
	 */
	public Slow(String spriteUrl,int x, int y) {
		super(new Rectangle(Settings.PIXEL_SIZE, Settings.PIXEL_SIZE), spriteUrl, x, y);
		this.shape.setRotate(180);
	}
}
