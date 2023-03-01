package ui.screens;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

/**
 * Settings screen
 * @author kchri,laurinelias
 *
 */
public class SettingsScreen extends VBox {
	
	public SimpleObjectProperty<Slider> musicSlider;
	public Button backToMenu, backToLevel;	
	public Label musicText;
	
	/**
	 * Constructor
	 */
	public SettingsScreen() {
		musicText = new Label("Music");
		musicText.getStyleClass().add("music-text");
		backToMenu = new Button("Back To Menu");
		backToLevel = new Button("Back to Level");
		musicSlider = new SimpleObjectProperty<Slider>(new Slider());
		
		this.getStyleClass().add("screen");
		this.getChildren().addAll(musicText, musicSlider.get(), backToLevel, backToMenu);
	}

}
