package application;


import manager.LevelManager;
import mp3_player.Mp3Player;
import obstacles.GameObject;
import player.Player;

import java.util.List;

import enums.Direction;

import javafx.animation.AnimationTimer;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

import manager.KeyboardManager;
import manager.Level;
import ui.GameView;
import ui.UIRoot;
import ui.components.LevelButton;
import ui.screens.HomeScreen;
import ui.screens.LevelSelection;
import ui.screens.LoseScreen;
import ui.screens.SettingsScreen;
import ui.screens.VictoryScreen;


/*    
 * MVC - Stateless
 * 
 * Controlls the View and Buisness
 * */
public class GameController {
	
	Mp3Player mp3Player;
	Player player;
	LevelManager levelManager;
	KeyboardManager keyboardManager;
	GameView gameView; 
	
	/* Dereferenzierung des UI */
	UIRoot uiRoot;
	HomeScreen homeScreen;
	LevelSelection levelSelection;
	LoseScreen loseScreen;
	SettingsScreen settingsScreen;
	VictoryScreen victoryScreen;
	
	/**
	 * Constructor
	 */
	public GameController() {
		mp3Player = new Mp3Player();
		player = new Player();
		keyboardManager = new KeyboardManager();
		gameView = new GameView();
		levelManager = new LevelManager();		
		keyboardManager.initListener(gameView.scene);
		
		dereferenzeUi();
		initListeners();
		initMusicSlider();
		initializeGameThread();      
		updateLevelSelection();
		updateMenu();
	}
	
	/* Listener etc. */
	
	/**
	 * Init all Listeners for UI and level manager
	 */
	public void initListeners() {
		
		/* Ui Listener **/
		uiRoot.homeScreen.play.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
			playLevel();
			switchToUi("close");
		});
		
		uiRoot.homeScreen.settings.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
			switchToUi("settings");
		});
		
		uiRoot.homeScreen.levelSelection.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
			switchToUi("levels");
		});
		
		uiRoot.settingsScreen.backToLevel.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
			playLevel();
			switchToUi("close");
		});
		
		uiRoot.settingsScreen.backToMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
			switchToUi("home");
		});
		
		uiRoot.levelSelection.back.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
			switchToUi("home");
		});
		
		uiRoot.settingsScreen.musicSlider.get().valueProperty().addListener((o,oldValue,newValue) -> {
			if(mp3Player.getAudioPlayer() == null) return;
			mp3Player.setGain((double)newValue);
		});
		
		uiRoot.loseScreen.home.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
			switchToUi("home");
			clearLevel();
			loadLevel(levelManager.getCurrentLevel().getId()); 
		});
		
		uiRoot.loseScreen.tryAgain.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
			clearLevel();
			loadLevel(levelManager.getCurrentLevel().getId()); 
			switchToUi("close");
			playLevel();
		});
		
		uiRoot.victoryScreen.home.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
			clearLevel();
			loadLevel(levelManager.getCurrentLevel().getId()); 
			switchToUi("home");
		});
		
		uiRoot.victoryScreen.nextLevel.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
			clearLevel();
			loadLevel(levelManager.getNextLevelId()); 
			switchToUi("close");
			playLevel();
		});
		
		levelManager.getLevelStates().addListener(new ListChangeListener<Boolean>() {
			public void onChanged(Change<? extends Boolean> arg0) {
				updateLevelSelection();
			}
		});
		
		levelManager.currentLevelProperty().addListener((o, oldValue, newValue) -> {
			updateMenu();
		});
		
	}
	
	/**
	 * Updates the level selection in the ui
	 */
	public void updateLevelSelection() {	
		levelSelection.levelContainer.getChildren().clear();
		for(int i = 0; i < levelManager.getLevelStates().size(); i++) {
			
			LevelButton levelButton = new LevelButton("Level " + (i + 1),levelManager.getLevelStates().get(i));
			levelSelection.levelContainer.getChildren().add(levelButton);
			
			if(levelManager.getLevelStates().get(i)) {
				levelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
					
					/* Find id of button */
					clearLevel();
					loadLevel(levelSelection.levelContainer.getChildren().indexOf(event.getSource())); 
					switchToUi("close");
					playLevel();
				
				});
			}
		}
	}

	/**
	 * Updates the Menu ui 
	 */
	public void updateMenu() {
		homeScreen.currentLevel.setText("Level " + (levelManager.getCurrentLevel().getId() + 1));
	}
	
	/* Level behaviour */
	
	/**
	 * Fiers before the the game loop start
	 */
	public void onGameStart() {
		initPlayer();
		loadLevel(0);  
		playLevel();
	}
	
	/**
	 * Pauses the game
	 */
	public void pauseLevel() {
		player.setPause(true);
		mp3Player.pause();
	}
	
	/**
	 * Initialize player default settings and listeners
	 */
	public void initPlayer() {
		player.setPause(true);
		player.setInAir(false);
		player.setMoveDir(Direction.RIGHT);

		/* Die */
		player.getPlayerNode().translateYProperty().addListener((o, oldValue, newValue) -> {
			if(newValue.intValue() > Settings.WINDOW_HEIGHT) {
				switchToUi("lose");
				pauseLevel();
			}
		});
		
		/* Win */
		player.getPlayerNode().translateXProperty().addListener((o, oldValue, newValue) -> {
			if(newValue.intValue() > levelManager.getFinalX()) {
				switchToUi("victory");
				pauseLevel();
				levelManager.wonLevel(levelManager.getCurrentLevel().getId());
			}
		});
		
		player.getPlayerNode().translateXProperty().addListener((o, oldValue, newValue) -> {
			/* Camera */
			gameView.levelAndPlayer.setTranslateX(-newValue.intValue() + 200);
			/* Parallax */
			gameView.levelbackground.setTranslateX(-newValue.intValue() * 0.2);
			
		});
	}
	
	/**
	 * Starts / Unpauses the game
	 */
	public void playLevel() {
		mp3Player.play();
		player.setSpeed(calculateSpeedByBPM(levelManager.getCurrentLevel().getTrack().getBpm()));
		player.setPause(false);
	}
	
	/**
	 * Loades a new level
	 * @param level levelID
	 */
	public void loadLevel(int level) {
		
		/* Load Level */
		levelManager.setLevel(level);
		addLevelToView(levelManager.getCurrentLevel().getLevelObstacles());
		setBackground(levelManager.getCurrentLevel().getBackground());
		addPlayerToView(player.getPlayerNode());
		
		/* Reset Player */
		player.startCollisionDetection();
		player.setPosition(levelManager.getSpawnPosition().getX(), levelManager.getSpawnPosition().getY());
		player.setInAir(false);
		player.setMoveDir(Direction.RIGHT);
		player.setPause(true);
		
		
		/* Init music */
		mp3Player.load(levelManager.getCurrentLevel().getTrack());
		mp3Player.setGain(settingsScreen.musicSlider.get().getValue());
		
	}
	
	/**
	 * Clears current level
	 */
	public void clearLevel() {
		
		/* Mp3 clean */
		mp3Player.pause();
		
		/* View clean */
		clearBackground();
		clearPlayerFormView(player.getPlayerNode());
		clearLevelFromView(levelManager.getCurrentLevel().getLevelObstacles());
		
		/* Buisness clean */
		player.setPause(true);
	}
  	
	/**
	 * Game loop
	 * @param delta time since last frame
	 * @param now current time 
	 */
	public void update(double delta, double now) {
		
		/* Keyboard */
		if(keyboardManager.isPressed(KeyCode.ESCAPE)) {
			
			if(uiRoot.getChildren().size() > 0 && (uiRoot.getChildren().get(0) == victoryScreen || uiRoot.getChildren().get(0) == loseScreen)) {
				return;
			}
			
			pauseLevel();
			switchToUi("home");
		}
			
		
		/* GameObjects */
		if(!player.isPause()) {
			levelManager.getCurrentLevel().getLevelObstacles().forEach((gameObject) -> {
				gameObject.update();
			});
		}
		
		/* Player */
		if(player != null) {
			
			/* Controlls */
			if(keyboardManager.isPressed(KeyCode.SPACE)) {
				player.jump();
			}
			              
			/* Player */
			player.update(delta, levelManager.getCurrentLevel().getLevelObstacles());
		}
		
	}
	
	/**
	 * Initializes the game thread
	 */
	public void initializeGameThread() {

		onGameStart();
		
		new AnimationTimer() {
			private int SECONDS2NANO_SECONDS = 1_000 * 1_000_000;
			private int FPNS_DELTA = SECONDS2NANO_SECONDS / Settings.FPS;
			private long lastRendered = 0;
			double zeit = 0;
		
			@Override
			public void handle(long now) {
				
				/* alle 120 Frames */
				if(lastRendered + FPNS_DELTA < now) {
					zeit++;
					double delta = lastRendered == 0 ? 0 : (now - lastRendered) / (double)SECONDS2NANO_SECONDS;
					update(delta,zeit);
					lastRendered = now;
				}		
			}
			
		}.start();
		
	}
	
	/**
	 * Synchronizing the speed based on the bpm of the current song
	 * @param bpm
	 * @return
	 */
	public double calculateSpeedByBPM(int bpm) {
		/* 120 BPM -> 13 */
		/* 100 BPM -> 11 */
		/* 75  BPM -> 8 */
		
		return bpm / 9.0909090;
	}
	
	/** View Related Functions */
	
	/**
	 * Init the music slider 
	 */
	public void initMusicSlider() {
		int shift = 10;
		uiRoot.settingsScreen.musicSlider.get().setMin(-80 + shift);
		uiRoot.settingsScreen.musicSlider.get().setMax(5 + shift);
		uiRoot.settingsScreen.musicSlider.get().setValue(-30 + shift);
	}
	
	/**
	 * Adding a list of GameObjects to the view, wich represent the current level
	 * @param level list of gameObjects
	 */
	public void addLevelToView(List<GameObject> level) {
		level.forEach((gameObject) -> {			
			gameView.levelAndPlayer.getChildren().add(gameObject.getShape());
		});
	}
	
	/**
	 * Clears the current gameObjects (representing the level) from the View
	 * @param level list of gameObjects
	 */
	public void clearLevelFromView(List<GameObject> level) {
		level.forEach((gameObject) -> {			
			gameView.levelAndPlayer.getChildren().remove(gameObject.getShape());
		});
	}
	
	/**
	 * adding the player shape to the view 
	 * @param player shape
	 */
	public void addPlayerToView(Shape player) {
		gameView.levelAndPlayer.getChildren().add(player);
	}
	
	/**
	 * Clearing the player shape from the view
	 * @param player shape
	 */
	public void clearPlayerFormView(Shape player) {
		gameView.levelAndPlayer.getChildren().remove(player);
	}
	
	/**
	 * Setting the background
	 * @param url background img
	 */
	public void setBackground(String url) {
		gameView.levelbackground.setBackground(url);
	}
	
	/**
	 * Clearing the background img
	 */
	public void clearBackground() {
		gameView.levelbackground.clearBackground();
	}
	
	/**
	 * Switching to prefered ui based on name 
	 * @param ui name (home, settings, lose, victory, levels)
	 */
	public void switchToUi(String ui) {
		
		uiRoot.getChildren().clear();
		
		switch(ui) {
			case "home" : {
				uiRoot.getChildren().add(homeScreen);
				break;
			}
			case "settings" : {
				uiRoot.getChildren().add(settingsScreen);
				break;
			}
			case "lose" : {
				uiRoot.getChildren().add(loseScreen);
				break;
			}
			case "victory" : {
				uiRoot.getChildren().add(victoryScreen);
				break;
			}
			case "levels":{
				uiRoot.getChildren().add(levelSelection);
				break;
			}
			default : break;
		}
	}
	
	/**
	 * Dereferezing ui variables
	 */
	public void dereferenzeUi() {
		uiRoot = gameView.uiRoot;
		victoryScreen = uiRoot.victoryScreen;
		loseScreen = uiRoot.loseScreen;
		settingsScreen = uiRoot.settingsScreen;
		homeScreen = uiRoot.homeScreen;
		levelSelection = uiRoot.levelSelection;
	}
	
	/**
	 * get root scene
	 */
	public Scene getRootScene() {
		return gameView.scene;
	}

}
