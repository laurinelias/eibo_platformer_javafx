package ui.screens;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * Losescreen
 * @author kchri,laurinelias
 *
 */
public class LoseScreen extends VBox {
	public Label loseText;
	public Button tryAgain, home;
	/**
	 * Constructor
	 */
	public LoseScreen() {
		loseText = new Label("YOU LOST");
		tryAgain = new Button("Try again");
		home = new Button("Home");
		loseText.getStyleClass().addAll("lose");
		this.getStyleClass().add("screen");
		this.getChildren().addAll(loseText, tryAgain, home);
	}
	
}