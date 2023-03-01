package ui.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Levelbutton (in ui for selection levels)
 * can be locked and open
 * @author kchri,laurinelias
 *
 */
public class LevelButton extends HBox {
	
	public Label levelName;
	public Icon open,close;
	public Region ws;

	/**
	 * Constructor
	 * @param levelName
	 * @param isOpen
	 */
	public LevelButton(String levelName, boolean isOpen) {
		this.levelName = new Label(levelName);
		open = new Icon("open");
		close = new Icon("close");
		
		ws = new Region();
		HBox.setHgrow(ws, Priority.ALWAYS);
		this.levelName.getStyleClass().add("level-name");
		this.getChildren().addAll(this.levelName,ws);
		
		if(isOpen) {
			this.getChildren().add(open);
			this.getStyleClass().add("open");
		} else {
			this.getChildren().add(close);
			this.getStyleClass().add("close");
		}
		this.getStyleClass().add("level-button");
	}
	
}
