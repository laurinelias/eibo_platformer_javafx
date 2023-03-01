package ui.screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Homescreen
 * @author kchri,laurinelias
 *
 */
public class HomeScreen extends VBox {
	
	public Label currentLevel;
	public Button play,levelSelection, settings;
	
	/**
	 * Constructor
	 */
	public HomeScreen() {
		currentLevel = new Label("LEVEL 1");
		currentLevel.getStyleClass().add("current-level-text");
		
		play = new Button("Start / Resume");
		levelSelection = new Button("Level Selection");
		settings = new Button("Settings");
		
		this.getChildren().addAll(currentLevel, play, levelSelection, settings);
		this.getStyleClass().add("screen");
	}

}
