package obstacles;

import application.Settings;
import javafx.scene.shape.Rectangle;

/**
 * GameObject -> Ground where you can move on
 * @author kchri,laurinelias
 *
 */
public class Ground extends GameObject {
	/**
	 * Constructor
	 * @param spriteUrl
	 * @param x spawn x
	 * @param y spawn y
	 */
	public Ground(String spriteUrl,int x, int y) {
		super(new Rectangle(Settings.PIXEL_SIZE, Settings.PIXEL_SIZE), spriteUrl, x, y);
	}
}
