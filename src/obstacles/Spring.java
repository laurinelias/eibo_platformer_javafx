package obstacles;

import application.Settings;
import javafx.scene.shape.Rectangle;

/**
 * GameObject -> like a trampoline
 * @author kchri,laurinelias
 *
 */
public class Spring extends GameObject {
	
	/**
	 * Constructor
	 * @param spriteUrl
	 * @param x spawn x
	 * @param y spawn y
	 */
	public Spring(String spriteUrl,int x, int y) {
		super(new Rectangle(Settings.PIXEL_SIZE, Settings.PIXEL_SIZE / 8), spriteUrl, x, y + ((Settings.PIXEL_SIZE / 8) * 7));
	}
}
