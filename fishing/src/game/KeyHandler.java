package game;

import constants.State;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyHandler {

	private GameWindow gameWindow = null;
	private boolean mousePressed = false;
	private double mouseX = 0;
	private double mouseY = 0;
//	private Scene scene = null;

	public KeyHandler(GameWindow gameWindow, Scene scene) {
		this.gameWindow = gameWindow;

		scene.setOnMouseMoved(e -> {
			mouseX = e.getX();
			mouseY = e.getY();
		});

		scene.setOnKeyPressed(e -> {
			KeyCode keyPressed = e.getCode();
			
			switch (gameWindow.getGameState()) {
			case State.PLAY_STATE: playStateInput(keyPressed); break;
			case State.GAME_OVER_STATE: gameOverStateInput(keyPressed); break;
			case State.PAUSE_STATE: pauseStateInput(keyPressed); break;
			case State.NEXT_LEVEL_MENU_STATE: nextLevelMenuStateInput(keyPressed); break;
			}
			
		});

	}

	private void nextLevelMenuStateInput(KeyCode keyPressed) {
		switch (keyPressed) {
		case UP:
			gameWindow.getUi().getNextLevelMenu().setCurrentOption(gameWindow.getUi().getNextLevelMenu().getCurrentOption() - 1);
			if(gameWindow.getUi().getNextLevelMenu().getCurrentOption() < 0) gameWindow.getUi().getNextLevelMenu().setCurrentOption(2);
			break;
		case DOWN:
			gameWindow.getUi().getNextLevelMenu().setCurrentOption(gameWindow.getUi().getNextLevelMenu().getCurrentOption() + 1);
			if(gameWindow.getUi().getNextLevelMenu().getCurrentOption() > 2) gameWindow.getUi().getNextLevelMenu().setCurrentOption(0);
			break;
			
		case ENTER:
			if(gameWindow.getUi().getNextLevelMenu().getCurrentOption() == 0) gameWindow.nextLevel();
			else if(gameWindow.getUi().getNextLevelMenu().getCurrentOption() == 1) gameWindow.reloadCurrentLevelGame();
			else System.exit(0);
			break;

		}
	}

	private void playStateInput(KeyCode keyPressed) {
		switch (keyPressed) {
		case P:
			gameWindow.setGameState(State.PAUSE_STATE);
			break;
		}
	}

	private void gameOverStateInput(KeyCode keyPressed) {
		switch (keyPressed) {
		case UP:
			gameWindow.getUi().getGameOverUI().setCurrentOption(gameWindow.getUi().getGameOverUI().getCurrentOption() - 1);
			if (gameWindow.getUi().getGameOverUI().getCurrentOption() < 0) gameWindow.getUi().getGameOverUI().setCurrentOption(2);
			break;

		case DOWN:
			gameWindow.getUi().getGameOverUI().setCurrentOption(gameWindow.getUi().getGameOverUI().getCurrentOption() + 1);
			if (gameWindow.getUi().getGameOverUI().getCurrentOption() > 2) gameWindow.getUi().getGameOverUI().setCurrentOption(0);
			break;

		case ENTER:
			if (gameWindow.getUi().getGameOverUI().getCurrentOption() == 0) gameWindow.setGameState(State.MENU_STATE);
			else if (gameWindow.getUi().getGameOverUI().getCurrentOption() == 1) gameWindow.reloadCurrentLevelGame();
			else System.exit(0);
			break;
		}

	}

	private void pauseStateInput(KeyCode keyPressed) {
		switch (keyPressed) {
		case UP:
			gameWindow.getUi().getPauseUI().setCurrentOption(gameWindow.getUi().getPauseUI().getCurrentOption() - 1);
			if (gameWindow.getUi().getPauseUI().getCurrentOption() < 0)
				gameWindow.getUi().getPauseUI().setCurrentOption(2);
			break;

		case DOWN:
			gameWindow.getUi().getPauseUI().setCurrentOption(gameWindow.getUi().getPauseUI().getCurrentOption() + 1);
			if (gameWindow.getUi().getPauseUI().getCurrentOption() > 2)
				gameWindow.getUi().getPauseUI().setCurrentOption(0);
			break;

		case SPACE:
		case ENTER:
			if (gameWindow.getUi().getPauseUI().getCurrentOption() == 0) gameWindow.setGameState(State.PLAY_STATE);
			else if (gameWindow.getUi().getPauseUI().getCurrentOption() == 1) gameWindow.reloadCurrentLevelGame();
			else System.exit(0);
			break;
		}

	}

	public double getMouseX() {
		return mouseX;
	}

	public void setMouseX(double mouseX) {
		this.mouseX = mouseX;
	}

	public double getMouseY() {
		return mouseY;
	}

	public void setMouseY(double mouseY) {
		this.mouseY = mouseY;
	}

}
