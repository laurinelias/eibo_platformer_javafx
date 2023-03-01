package ui;
import application.Settings;
import javafx.scene.shape.Circle;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Root of the view, holds gameroot and uiroot 
 * @author kchri,laurinelias
 *
 */
public class GameView {
	public Pane root;
	public UIRoot uiRoot;
	public Pane gameRoot;
	public Pane levelAndPlayer;
	public Background levelbackground;
	public Scene scene;
	
	/**
	 * Constructor
	 */
	public GameView () {		
		root = new StackPane();
		
		uiRoot = new UIRoot();
		gameRoot = new StackPane();
		
		levelAndPlayer = new Pane();
		levelbackground = new Background();
		
		gameRoot.getChildren().addAll(levelbackground,levelAndPlayer);
		
		root.getChildren().addAll(gameRoot, uiRoot);
		scene = new Scene(root, Settings.WINDOW_LENGTH, Settings.WINDOW_HEIGHT);
	}
	
	/**
	 * Drawing points mainly for debug purpose
	 * @param point
	 */
	public void drawPoint(Point2D point) {
		Circle shape = new Circle();
		shape.setTranslateX(point.getX());
		shape.setTranslateY(point.getY());
		shape.setFill(Color.BLACK);
		shape.setRadius(4);
		gameRoot.getChildren().add(shape);
	}
	
}
