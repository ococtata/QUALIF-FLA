package factory;

import manager.game.GameManager;
import model.Restaurant;
import utility.RandomUtil;

public class NameFactory implements RandomUtil {
	
	public static String generateInitial() {
		Restaurant activeRestaurant = GameManager.getInstance().getActiveRestaurant();
		String initial = "";
		
		do {
			initial = generateRandomInitial();
		} while(activeRestaurant.hasInitial(initial));
		
		return initial;
	}
	
	private static String generateRandomInitial() {
        char first = (char) ('A' + rand.nextInt(26));
        char second = (char) ('A' + rand.nextInt(26));
        return "" + first + second;
    }
}
