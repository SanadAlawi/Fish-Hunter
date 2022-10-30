package game;

import java.util.Queue;

import entity.Fish;
import entity.Player;
import javafx.scene.shape.Rectangle;

public class CollisionChecker {
	private GameWindow gameWindow;

	public CollisionChecker(GameWindow gameWindow) {
		super();
		this.gameWindow = gameWindow;
	}

	
	
	public Fish checkFishCollision(Player player) {
		Queue<Fish> fish = gameWindow.getFish();
		
		for(Fish fishElement: fish) {
			if(overlaps(player.getBoxCollider(), fishElement.getBoxCollider())) {
				return fishElement;
			}
		}
		
		return null;
	}
	
	public boolean overlaps(Rectangle entity, Rectangle object) {
		return object.getX() < entity.getX() + entity.getWidth() && object.getX() + object.getWidth() > entity.getX()
				&& object.getY() < entity.getY() + entity.getHeight()
				&& object.getY() + object.getHeight() > entity.getY();
	}
}
