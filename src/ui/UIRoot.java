package ui;
import javafx.scene.layout.StackPane;
import ui.screens.HomeScreen;
import ui.screens.LevelSelection;
import ui.screens.LoseScreen;
import ui.screens.SettingsScreen;
import ui.screens.VictoryScreen;

/**
 * UI root, holds all different screens
 * @author kchri,laurinelias
 *
 */
public class UIRoot extends StackPane {
	public VictoryScreen victoryScreen;
	public LoseScreen loseScreen;
	public SettingsScreen settingsScreen;
	public HomeScreen homeScreen;
	public LevelSelection levelSelection;

	public UIRoot() {
		victoryScreen = new VictoryScreen();
		loseScreen = new LoseScreen();
		settingsScreen = new SettingsScreen();
		homeScreen = new HomeScreen();
		levelSelection = new LevelSelection();
	}
	
}
