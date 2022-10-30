package game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
	
	private GameWindow gameWindow = new GameWindow(30, 15, 4);

	@Override
	public void start(Stage stage) throws Exception {
		gameWindow.open(stage);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
