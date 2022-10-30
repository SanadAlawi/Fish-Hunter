package game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import constants.State;
import entity.Fish;
import entity.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.UI;

public class GameWindow {
	private int scale = 0;
	private int tileSize = 16;
	private int GameWindowWidth = 0;
	private int GameWindowHeight = 0;
	private Canvas canvas;
	private GraphicsContext graphicsContext;
	private Scene scene;

	private int gameState = State.PLAY_STATE;
	private int currentLevel = 1;
	private int numberOfFrameToAddFish = 0;
	private Random random;

	private Timeline timeline;
	private KeyHandler keyHandler;
	private Player player;
	private Queue<Fish> fish;
	private CollisionChecker collisionChecker;
	private UI ui;

	private int frame = 0;

	public GameWindow(int canvasColumn, int canvasRow, int scale) {
		super();
		this.scale = scale;
		this.tileSize *= this.scale;
		this.GameWindowWidth = canvasColumn * this.tileSize;
		this.GameWindowHeight = canvasRow * this.tileSize;

		setUpGameStaff();
	}

	private void setUpGameStaff() {
		this.canvas = new Canvas(this.GameWindowWidth, this.GameWindowHeight);
		this.graphicsContext = canvas.getGraphicsContext2D();
		this.timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> run()));
		this.timeline.setCycleCount(Timeline.INDEFINITE);

		this.scene = new Scene(new Group(this.canvas));
		this.keyHandler = new KeyHandler(this, scene);
		this.player = new Player(this, (GameWindowWidth / 2), GameWindowHeight / 2, 0, keyHandler, "blackFish");
		this.fish = new LinkedList<Fish>();
		this.collisionChecker = new CollisionChecker(this);
		this.ui = new UI(this, graphicsContext);
		this.numberOfFrameToAddFish = 150;
		this.random = new Random();
	}

	// Open Window and start game loop function
	public void open(Stage stage) {
		stage.setScene(scene);
		stage.show();

		timeline.play();
	}

	private Image background = new Image("/background/underwater-fantasy-preview.png");

	// Game Loop Function
	private void run() {

		// PAINT
		graphicsContext.clearRect(0, 0, GameWindowWidth, GameWindowHeight);
		graphicsContext.drawImage(background, 0, 0, GameWindowWidth, GameWindowHeight);

		switch (gameState) {
		case State.PLAY_STATE: playState(); break;
		case State.PAUSE_STATE: pauseState(); break;
		case State.GAME_OVER_STATE: gameOverState(); break;
		case State.NEXT_LEVEL_MENU_STATE: nextLevelMenuState(); break;
		}

	

	}

	private void playState() {

		handleFish();

		if (gameState != State.PAUSE_STATE)
			player.update();
		player.draw(graphicsContext);

		ui.draw();

		frame++;

	}

	private void pauseState() {
		player.draw(graphicsContext);
		for (Fish fishElement : fish)
			fishElement.draw(graphicsContext);
		ui.draw();
	}

	private void gameOverState() {
		player.draw(graphicsContext);
		for (Fish fishElement : fish)
			fishElement.draw(graphicsContext);
		ui.draw();
	}

	private void nextLevelMenuState() {
		player.draw(graphicsContext);
		for (Fish fishElement : fish)
			fishElement.draw(graphicsContext);
		ui.draw();
	}

	public void nextLevel() {
		frame = 0;
		currentLevel++;
		if (currentLevel > levels.length)
			currentLevel = 1;
		fish.clear();
		int [] currentLevelSize = getFishSizeDependingOnLevel();
		player.setDefaultValues(currentLevelSize[0]);
		gameState = State.PLAY_STATE;
//		numberOfFrameToAddFish = 200;
		if(currentLevel == 1) numberOfFrameToAddFish = 150;
		if(currentLevel == 2) numberOfFrameToAddFish = 100;
		if(currentLevel == 3) numberOfFrameToAddFish = 80;
		if(currentLevel == 4) numberOfFrameToAddFish = 60;
	}
	

	private String [] fishColors = {"red", "blue", "green", "purple", "yellow", "black"};
	private void handleFish() {

		if (numberOfFrameToAddFish == frame) {
			int[] arraySize = getFishSizeDependingOnLevel();
			int randomSize = (random.nextInt(arraySize.length));

			double width = arraySize[randomSize];
			double height = arraySize[randomSize];

			double xPosition = random.nextInt(10) - 5 > 0 ? (0 - tileSize * width) : GameWindowWidth;
			double yPosition = random.nextDouble() * (GameWindowHeight - (tileSize * height));

			String spriteSheetType = fishColors[random.nextInt(fishColors.length)];
			fish.add(new Fish(this, xPosition, yPosition, width, height, spriteSheetType));
			frame = 0;
		}

		if (fish.size() > 40)
			fish.poll();

		for (Fish fish : fish) {
			if (gameState != State.PAUSE_STATE)
				fish.update();
			fish.draw(graphicsContext);
		}

	}

	private int[][] levels = { { 1, 1, 1, 1, 1, 1, 2, 2, 3, 4, 5 }, { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 4}, {2, 2, 2, 2, 3, 3, 3, 4, 5},
			{2, 2, 3, 3, 3, 4, 4, 4, 5, 6, 7} };

	private int[] getFishSizeDependingOnLevel() {
		return levels[currentLevel - 1];
	}

//	public void reloadGame() {
//		fish.clear();
//		player.setDefaultValues();
//		gameState = State.PLAY_STATE;
//		currentLevel = 1;
//	}

	public void reloadCurrentLevelGame() {
		fish.clear();
		int [] currentLevelSize = getFishSizeDependingOnLevel();
		player.setDefaultValues(currentLevelSize[0]);
		gameState = State.PLAY_STATE;

	}

	public void win() {
		gameState = State.NEXT_LEVEL_MENU_STATE;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getGameWindowWidth() {
		return GameWindowWidth;
	}

	public void setGameWindowWidth(int gameWindowWidth) {
		GameWindowWidth = gameWindowWidth;
	}

	public int getGameWindowHeight() {
		return GameWindowHeight;
	}

	public void setGameWindowHeight(int gameWindowHeight) {
		GameWindowHeight = gameWindowHeight;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}

	public void setGraphicsContext(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public CollisionChecker getCollisionChecker() {
		return collisionChecker;
	}

	public void setCollisionChecker(CollisionChecker collisionChecker) {
		this.collisionChecker = collisionChecker;
	}

	public Queue<Fish> getFish() {
		return fish;
	}

	public void setFish(Queue<Fish> fish) {
		this.fish = fish;
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	public UI getUi() {
		return ui;
	}

	public void setUi(UI ui) {
		this.ui = ui;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

}
