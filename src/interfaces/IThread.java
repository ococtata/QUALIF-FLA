package interfaces;

import manager.game.GameManager;
import model.Restaurant;

public interface IThread {
	public static final Restaurant restaurant = GameManager.getInstance().getActiveRestaurant();
	void pause();
	void unPause();
	void startThread();
}
