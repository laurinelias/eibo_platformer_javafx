package ui.screens;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import ui.components.LevelButton;

/**
 * Level selection
 * @author kchri,laurinelias
 *
 */
public class LevelSelection extends VBox {
	public Button back;
	public LevelButton level, level2, level3, level4, level5;
	public VBox levelContainer;
	
	/**
	 * Constructor
	 */
	public LevelSelection () {
		levelContainer = new VBox();
		levelContainer.getStyleClass().add("level-buttons-conatiner");	
		back = new Button("Back");
		this.getChildren().addAll(levelContainer,back);
		this.getStyleClass().add("screen");
	}

}
