package ui;

import game.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NextLevelMenu extends UIStructure {

	public NextLevelMenu(GameWindow gameWindow, GraphicsContext graphicsContext) {
		super(gameWindow, graphicsContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void uiHeader() {
		graphicsContext.setFill(Color.YELLOW);
		
		graphicsContext.setFont(vecnaFont_header);
		String text = "You have successfully passed the stage";
		double textWidth = getTextWidth(text, 40 * gameWindow.getScale());
		graphicsContext.fillText(text, (gameWindow.getGameWindowWidth() / 2) - (textWidth / 2),
				gameWindow.getGameWindowHeight() / 3);

	}

	@Override
	public void uiOptions() {
		graphicsContext.setFont(vecnaFont_options);

		String optionText = "Next Level";
		double optionTextWidth = getTextWidth(optionText, 20 * gameWindow.getScale());
		int distanceBetweenOptions = gameWindow.getTileSize();

		double x = (gameWindow.getGameWindowWidth() / 2) - (optionTextWidth / 2);
		double y = (gameWindow.getGameWindowHeight() / 2) + distanceBetweenOptions;

		if (currentOption == 0) graphicsContext.fillText(">", x - distanceBetweenOptions, y);
		graphicsContext.fillText(optionText, x, y);

		optionText = "Try again";
		optionTextWidth = getTextWidth(optionText, 20 * gameWindow.getScale());
		distanceBetweenOptions = gameWindow.getTileSize();

		x = (gameWindow.getGameWindowWidth() / 2) - (optionTextWidth / 2);
		y += distanceBetweenOptions;

		if (currentOption == 1) graphicsContext.fillText(">", x - distanceBetweenOptions, y);
		graphicsContext.fillText(optionText, x, y);

		optionText = "Quit";
		optionTextWidth = getTextWidth(optionText, 20 * gameWindow.getScale());
		distanceBetweenOptions = gameWindow.getTileSize();

		x = (gameWindow.getGameWindowWidth() / 2) - (optionTextWidth / 2);
		y += distanceBetweenOptions;

		if (currentOption == 2) graphicsContext.fillText(">", x - distanceBetweenOptions, y);
		graphicsContext.fillText(optionText, x, y);

	}

}
