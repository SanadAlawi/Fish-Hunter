package ui;

import java.io.InputStream;

import game.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class UIStructure {
	
	protected GameWindow gameWindow;
	protected GraphicsContext graphicsContext;
	protected Font vecnaFont_options;
	protected Font vecnaFont_header;
	
	protected int currentOption = 0;

	public UIStructure(GameWindow gameWindow, GraphicsContext graphicsContext) {
		super();
		this.gameWindow = gameWindow;
		this.graphicsContext = graphicsContext;
		
		InputStream is_40 = getClass().getResourceAsStream("/fonts/Vecna-oppx.ttf");
		InputStream is_80 = getClass().getResourceAsStream("/fonts/Vecna-oppx.ttf");
		vecnaFont_options = Font.loadFont(is_40, 20 * gameWindow.getScale());
		vecnaFont_header = Font.loadFont(is_80, 40 * gameWindow.getScale());
	}
	
	public void show() {
		uiHeader();
		uiOptions();
	}

	public abstract void uiHeader();
	public abstract void uiOptions();

	public int getCurrentOption() {
		return currentOption;
	}

	public void setCurrentOption(int currentOption) {
		this.currentOption = currentOption;
	}
	
	public int getTextWidth(String text, int fontSize) {
		Text stringText = new Text(text);
		stringText.setFont(new Font("Arial", fontSize));
		return (int) stringText.getLayoutBounds().getWidth();
	}
	
}
