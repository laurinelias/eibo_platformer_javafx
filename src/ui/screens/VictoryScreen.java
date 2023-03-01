package ui.screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Victory screen
 * @author kchri,laurinelias
 *
 */
public class VictoryScreen extends VBox {
	
	public Label victoryText;
	public Button nextLevel, home;

	/**
	 * Constructor
	 */
	public VictoryScreen() {
		victoryText = new Label("VICTORY");
		nextLevel = new Button("Next Level");
		home = new Button("Home");
		victoryText.getStyleClass().addAll("victory");
		this.getStyleClass().add("screen");
		this.getChildren().addAll(victoryText, nextLevel, home);
	}
	
}


