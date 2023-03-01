package ui;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Background ui image view
 * @author kchri,laurinelias
 *
 */
public class Background extends Pane {
	
	ImageView imgView = new ImageView();
	
	public Background() {
		this.getChildren().add(imgView);
	}
	
	public void setBackground(String url) {
		imgView.setImage(new Image(url));
	}
	
	public void clearBackground() {
		imgView.setImage(null);
	}
 
}
