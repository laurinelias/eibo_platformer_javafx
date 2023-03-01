package manager;
import java.io.File;
import java.util.ArrayList;
import application.Settings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import utils.Shapes;


/**
 * LevelManager managing the states of the levels, unlocking new levels, and managing current level
 * @author kchri, laurinelias
 *
 */
public class LevelManager {

	/* General */
	public ArrayList<Level> levels;
	private ObservableList<Boolean> levelStates;
	public int levelCounter;
	private SimpleObjectProperty<Level> currentLevel;
	
	/**
	 * Constructor
	 */
	public LevelManager() {
		levelStates = FXCollections.observableArrayList(new ArrayList<Boolean>());
		levels = new ArrayList<Level>();
		currentLevel = new SimpleObjectProperty<Level>();
		addLevels();
		
		/* Build level states */ 
		levelCounter = 1;
		levels.forEach((level) -> {
			/* first level and all custom levels are unlocked */
			if(levelCounter == 1 || levelCounter > 5) {		
				levelStates.add(true);
			} else {
				levelStates.add(false);
			}
			levelCounter++;

		});
	}
	
	/**
	 * When level [id] won, it unlocks level [id + 1]
	 * @param level id who is won
	 */
	public void wonLevel(int id) {
		/* next level gets unlocked */		
		levelStates.set((id + 1) % (levelStates.size()), true);
	} 
	
	/**
	 * get next level id based on the current level
	 * @return next level id
	 */
	public int getNextLevelId() {		
		return (currentLevel.get().getId() + 1) % (levelStates.size());
	}
	
	/**
	 * Adding all levels to the manager, and additinol extra levels in the custom_levels folder
	 */
	public void addLevels() {
		addLevel(
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_1\\undertale.mp3",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_1\\level_1_background.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_1\\level_1.txt",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_1\\level_1_ground.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_1\\level_1_spike.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_1\\level_1_ball.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_1\\level_1_spring.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_1\\level_1_speed.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_1\\level_1_glass.png");
		
		addLevel(
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_2\\streetfigher.mp3",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_2\\level_2_background.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_2\\level_2.txt",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_2\\level_2_ground.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_2\\level_2_spike.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_2\\level_2_ball.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_2\\level_2_spring.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_2\\level_2_speed.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_2\\level_2_glass.png");
		
		addLevel(
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_3\\retro_1.mp3",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_3\\level_3_background.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_3\\level_3.txt",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_3\\level_3_ground.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_3\\level_3_spike.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_3\\level_3_ball.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_3\\level_3_spring.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_3\\level_3_speed.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_3\\level_3_glass.png");
	
		addLevel(
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_4\\retro_2.mp3",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_4\\level_4_background.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_4\\level_4.txt",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_4\\level_4_ground.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_4\\level_4_spike.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_4\\level_4_ball.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_4\\level_4_spring.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_4\\level_4_speed.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_4\\level_4_glass.png");
		
		addLevel(
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\castlevania.mp3",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\level_5_background.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\level_5.txt",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\level_5_ground.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\level_5_spike.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\level_5_ball.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\level_5_spring.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\level_5_speed.png",
				"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\level_5_glass.png");
		
		
		/* Custom vesv*/
		File dir = new File("media/custom_levels");
		File[] files = dir.listFiles();
		
		for(File file : files) {
			
			System.out.println(file.getAbsolutePath());
			addLevel(
					"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\castlevania.mp3",
					"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\level_5_background.png",
					file.getAbsolutePath(),
					"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_1\\level_1_ground.png",
					"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_2\\level_2_spike.png",
					"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_3\\level_3_ball.png",
					"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_4\\level_4_spring.png",
					"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_5\\level_5_speed.png",
					"C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\level_1\\level_1_glass.png");
		}
		
	}
	
	/**
	 * Settings the current level
	 * @param levelId desiered current level
	 */
	public void setLevel(int levelId) {	
		
		System.out.println("level set -> " + levelId);
		currentLevel.setValue(levels.get(levelId).clone());
	}
	
	/**
	 * Adding a new level to the manager with alle the infomation needed
	 * @param trackPath
	 * @param background
	 * @param levelTxtUrl
	 * @param ground_sprite
	 * @param spike_sprite
	 * @param ball_sprite
	 * @param spring_sprite
	 * @param speed_sprite
	 * @param glass_sprite
	 */
	public void addLevel(String trackPath, String background, String levelTxtUrl, String ground_sprite, String spike_sprite, String ball_sprite,
			String spring_sprite, String speed_sprite, String glass_sprite) {
		
		levels.add(new Level(
			levels.size(),
			trackPath,
			background,
			levelTxtUrl,
			ground_sprite,
			spike_sprite,
			ball_sprite,
			spring_sprite,
			speed_sprite,
			glass_sprite
		));
	}
	
	/**
	 * Get the Spawnposition based on the level (First block of the current level) 
	 * @return first block x position
	 */
	public Point2D getSpawnPosition() {
		
		Point2D out = new Point2D(
				Shapes.getPointFromRec((Rectangle)currentLevel.get().getLevelObstacles().get(0).getShape(), "top_left").getX(),
				Shapes.getPointFromRec((Rectangle)currentLevel.get().getLevelObstacles().get(0).getShape(), "top_left").getY() - Settings.PIXEL_SIZE
		);
		return out;
	};
	
	/**
	 * Getting the "finish line" of the level, based of the last block in game
	 * @return finishline y
	 */
	public int getFinalX() {
		return (int)Shapes.getPointFromRec((Rectangle)currentLevel.get().getLevelObstacles().get(currentLevel.get().getLevelObstacles().size() - 1).getShape(), "top_right").getX();
	};
	
	public Level getCurrentLevel() {
		return currentLevel.get();
	}
	
	public SimpleObjectProperty<Level> currentLevelProperty() {
		return currentLevel;
	}

	public ObservableList<Boolean> getLevelStates() {
		return levelStates;
	}
}


