package game;

import javafx.scene.image.Image;

public class Animation {
	private Image spriteSheetImage;
	private double spriteSheetWidth;
	private double spriteSheetHeight;
	private int framesInX;
	private int framesInY;

	public Animation(Image spriteSheetImage, int framesInX, int framesInY) {
		super();
		this.spriteSheetImage = spriteSheetImage;
		this.spriteSheetWidth = this.spriteSheetImage.getWidth() / framesInX;
		this.spriteSheetHeight = this.spriteSheetImage.getHeight() / framesInY;
		this.framesInX = framesInX;
		this.framesInY = framesInY;
	}

	public Image getSpriteSheetImage() {
		return spriteSheetImage;
	}

	public void setSpriteSheetImage(Image spriteSheetImage) {
		this.spriteSheetImage = spriteSheetImage;
	}

	public double getSpriteSheetWidth() {
		return spriteSheetWidth;
	}

	public void setSpriteSheetWidth(double spriteSheetWidth) {
		this.spriteSheetWidth = spriteSheetWidth;
	}

	public double getSpriteSheetHeight() {
		return spriteSheetHeight;
	}

	public void setSpriteSheetHeight(double spriteSheetHeight) {
		this.spriteSheetHeight = spriteSheetHeight;
	}

	public int getFramesInX() {
		return framesInX;
	}

	public void setFramesInX(int framesInX) {
		this.framesInX = framesInX;
	}

	public int getFramesInY() {
		return framesInY;
	}

	public void setFramesInY(int framesInY) {
		this.framesInY = framesInY;
	}

}
