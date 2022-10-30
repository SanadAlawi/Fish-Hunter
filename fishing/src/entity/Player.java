package entity;

import constants.State;
import game.Animation;
import game.GameWindow;
import game.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Entity {

	private KeyHandler keyHandler;

	private double xVelocity = 0;
	private double yVelocity = 0;

	private double xDestination = 0;
	private double yDestination = 0;

	private int score = 0;
	private int incrementSizeAccordingToScore = 0;
	
	private final static int NUMBER_OF_FISH_TO_GROW = 10;
	private final static int NUMBER_OF_FISH_TO_WIN = 40;

	private Animation SpriteSheetRight;
	
	public Player(GameWindow gameWindow, double xPosition, double yPosition, double speed, KeyHandler keyHandler,
			String spriteSheetType) {
		super(gameWindow, xPosition, yPosition, speed);
		this.keyHandler = keyHandler;
		
		Image spriteSheetImage = new Image(String.format("/fish/%sLeftSwim.png", spriteSheetType));
		Image spriteSheetImageRight = new Image(String.format("/fish/%sRightSwim.png", spriteSheetType));
		
		this.spriteSheet = new Animation(spriteSheetImage, 4, 3);
		this.SpriteSheetRight = new Animation(spriteSheetImageRight, 4, 3);

		boxColliderOffsetY = 20;
		
		boxCollider = new Rectangle(xPosition, yPosition, width , height - boxColliderOffsetY * 2);
	}

	@Override
	public void update() {
		updateBoxCollider();

		xDestination = keyHandler.getMouseX() - width / 2;
		yDestination = keyHandler.getMouseY() - height / 2;

		xVelocity = xPosition - xDestination;
		yVelocity = yPosition - yDestination;


		if (xPosition != xDestination) xPosition -= xVelocity / 30.0;
		if (yPosition != yDestination) yPosition -= yVelocity / 30.0;

		Fish colliedBubble = gameWindow.getCollisionChecker().checkFishCollision(this);
		if (colliedBubble != null) fishColision(colliedBubble);

		if (incrementSizeAccordingToScore != 0 && incrementSizeAccordingToScore % NUMBER_OF_FISH_TO_GROW == 0) {
			incrementSize();
			incrementSizeAccordingToScore = 0;
		}
		
		if(score == NUMBER_OF_FISH_TO_WIN) {
			gameWindow.win();
		}
	}

	private void fishColision(Fish colliedFish) {
		if(size >= colliedFish.getSize()) {
			gameWindow.getFish().remove(colliedFish);
			incrementScore();
		}else {
			gameWindow.setGameState(State.GAME_OVER_STATE);
		}
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		double spriteSheetWidth = spriteSheet.getSpriteSheetWidth();
		double spriteSheetHeight = spriteSheet.getSpriteSheetHeight();
		
//		graphicsContext.setFill(Color.RED);
//		graphicsContext.fillRect(boxCollider.getX(), boxCollider.getY(), boxCollider.getWidth(), boxCollider.getHeight());

		if (xPosition >= xDestination) graphicsContext.drawImage(spriteSheet.getSpriteSheetImage(), spriteSheetWidth * xFrame, 0, spriteSheetWidth, spriteSheetHeight, xPosition, yPosition, width, height);
		else graphicsContext.drawImage(SpriteSheetRight.getSpriteSheetImage(), spriteSheetWidth * xFrame, 0, spriteSheetWidth, spriteSheetHeight, xPosition, yPosition, width, height);
			
		
		changeFrameInX();
	}
	
	
	public void setDefaultValues(int currentSize) {
		score = 0;
		incrementSizeAccordingToScore = 0;
		width = gameWindow.getTileSize() * currentSize;
		height = gameWindow.getTileSize() * currentSize;
		size = width;
		boxColliderOffsetY = 20;
		boxCollider = new Rectangle(xPosition, yPosition , width, height - boxColliderOffsetY * 2);
		
	}
	
	public void incrementSize() {
		width += gameWindow.getTileSize();
		height += gameWindow.getTileSize();
		boxColliderOffsetY += 10;
		size = width;
		boxCollider = new Rectangle(xPosition, yPosition , width, height - boxColliderOffsetY * 2);
	}
	

	public void incrementScore() {
		score++;
		incrementSizeAccordingToScore++;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getxDestination() {
		return xDestination;
	}

	public void setxDestination(double xDestination) {
		this.xDestination = xDestination;
	}

}
