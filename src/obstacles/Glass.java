package obstacles;

import application.Settings;
import javafx.scene.shape.Rectangle;

/**
 * GameObject -> see throw glass, no collision
 * @author kchri, laurinelias
 *
 */
public class Glass extends GameObject {
	/**
	 * Constructor
	 * @param spriteUrl
	 * @param x spawn x
	 * @param y spawn y
	 */
	public Glass(String spriteUrl,int x, int y) {
		super(new Rectangle(Settings.PIXEL_SIZE, Settings.PIXEL_SIZE), spriteUrl, x, y);
	}
}
