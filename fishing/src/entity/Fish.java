package entity;

import game.Animation;
import game.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Fish extends Entity{
	
	public Fish(GameWindow gameWindow, double xPosition, double yPosition, double width, double height, String spriteSheetType) {
		super(gameWindow, xPosition, yPosition, width, height);
		this.speed = (Math.random() * 3) + 1;
		if(xPosition > 0) speed = -speed; 
		
		Image spriteSheetImage;
		if(xPosition > 0) spriteSheetImage = new Image(String.format("/fish/%sFishLeftSwim.png", spriteSheetType));
		else spriteSheetImage = new Image(String.format("/fish/%sFishRightSwim.png", spriteSheetType));
		this.spriteSheet = new Animation(spriteSheetImage, 4, 3);
		
		
		boxColliderOffsetY = (int)height * gameWindow.getTileSize();
		boxColliderOffsetX = (int)width * gameWindow.getTileSize();
		this.boxCollider = new Rectangle(xPosition, yPosition, this.width - boxColliderOffsetX / 2, this.height - boxColliderOffsetY / 2);
	}

	@Override
	public void update() {
		updateBoxCollider();
		
		xPosition += speed;
	}
	
	@Override
	public void updateBoxCollider() {
		if(speed > 0) boxCollider.setX(xPosition + (width / 2.5));
		else boxCollider.setX(xPosition + (width / 7));
		boxCollider.setY(yPosition + (height / 4));
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		
		double spriteSheetWidth = spriteSheet.getSpriteSheetWidth();
		double spriteSheetHeight = spriteSheet.getSpriteSheetHeight();
		
		graphicsContext.drawImage(spriteSheet.getSpriteSheetImage(), xFrame * spriteSheetWidth, 0, spriteSheetWidth, spriteSheetHeight, xPosition, yPosition, width, height);
		
//		graphicsContext.setFill(Color.RED);
//		graphicsContext.fillRect(boxCollider.getX(), boxCollider.getY(), boxCollider.getWidth(), boxCollider.getHeight());
		
		
		changeFrameInX();
	}

	
	

}
