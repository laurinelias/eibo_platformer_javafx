package obstacles;

import application.Settings;
import javafx.scene.shape.Rectangle;

/**
 * GameObject -> powerup for speed
 * @author kchri,laurinelias
 *
 */
public class Speed extends GameObject {
	/**
	 * Constructor
	 * @param spriteUrl
	 * @param x spawn x
	 * @param y spawn y
	 */
	public Speed(String spriteUrl,int x, int y) {
		super(new Rectangle(Settings.PIXEL_SIZE, Settings.PIXEL_SIZE), spriteUrl, x, y);
	}
}
