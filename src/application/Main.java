package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	GameController gameController;
	
	@Override
	public void start(Stage primaryStage) {
		gameController = new GameController();
		try {
			gameController.getRootScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(gameController.getRootScene());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void stop(){
		gameController.mp3Player.pause();
	}
}
