package ui;

import java.io.InputStream;

import constants.State;
import game.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UI {
	private GameWindow gameWindow;
	private GraphicsContext graphicsContext;
	private Font vecnaFont;
	
	private GameOverUI gameOverUI;
	private PauseUI pauseUI;
	private NextLevelMenu nextLevelMenu;
	
	
	public UI(GameWindow gameWindow, GraphicsContext graphicsContext) {
		super();
		this.gameWindow = gameWindow;
		this.graphicsContext = graphicsContext;
		this.gameOverUI = new GameOverUI(gameWindow, graphicsContext);
		this.pauseUI = new PauseUI(gameWindow, graphicsContext);
		this.nextLevelMenu = new NextLevelMenu(gameWindow, graphicsContext);
		
		InputStream is = getClass().getResourceAsStream("/fonts/Vecna-oppx.ttf");
		this.vecnaFont = Font.loadFont(is, 20 * gameWindow.getScale());
		
	}
	
	public void draw() {
		switch (gameWindow.getGameState()) {
		case State.PLAY_STATE: playStateUI(); break;		
		case State.GAME_OVER_STATE: gameOverUI.show(); break;		
		case State.PAUSE_STATE: pauseUI.show(); break;		
		case State.NEXT_LEVEL_MENU_STATE: nextLevelMenu.show(); break;		
		}
	}

	private void playStateUI() {
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.setFont(vecnaFont);
		
		graphicsContext.fillText(String.format("Score: %s", gameWindow.getPlayer().getScore()), gameWindow.getTileSize(), gameWindow.getTileSize());
		graphicsContext.fillText(String.format("Level: %s", gameWindow.getCurrentLevel()), gameWindow.getTileSize(), gameWindow.getTileSize() * 2);
	}

	public GameOverUI getGameOverUI() {
		return gameOverUI;
	}

	public void setGameOverUI(GameOverUI gameOverUI) {
		this.gameOverUI = gameOverUI;
	}

	public PauseUI getPauseUI() {
		return pauseUI;
	}

	public void setPauseUI(PauseUI pauseUI) {
		this.pauseUI = pauseUI;
	}

	public NextLevelMenu getNextLevelMenu() {
		return nextLevelMenu;
	}

	public void setNextLevelMenu(NextLevelMenu nextLevelMenu) {
		this.nextLevelMenu = nextLevelMenu;
	}
	
	
	
}
