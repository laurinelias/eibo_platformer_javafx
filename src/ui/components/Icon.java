package ui.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.util.HashMap;

/**
 * Icon
 * @author kchri, laurinelias
 *
 */
public class Icon extends StackPane {

	ImageView iconInner;
	private final String ICON_FOLDER = "icons"; 

	@SuppressWarnings("serial")
	private HashMap<String, String> icons = new HashMap<>() {
		{
			put("open", "lock-open.png");
			put("close", "lock-closed.png");
		}
	};

	
	/**
	 * Constructor
	 * gives back icon based on name (hashmap)
	 */
	public Icon(String name) {
		this.getStyleClass().add("icon-wrapper");
		try {
			iconInner = new ImageView(new Image(new FileInputStream(ICON_FOLDER + "/" + icons.get(name))));
			this.getChildren().add(iconInner);
			this.setMaxSize(0,0);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

}
