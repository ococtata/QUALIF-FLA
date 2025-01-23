package manager;

import util.InputHandler;

public class GameManager {
	
	private static GameManager instance;
	
	private GameManager() {
		
	}
	
	public static GameManager getInstance() {
		if(instance == null) instance = new GameManager();
		return instance;
	}
	
	public void startGame() {
		showTitleScreen();
		showMainMenu();
	}
	
	private void showMainMenu() {
		System.out.println(" Main Menu\n");
		System.out.println(" 1. Play New Restaurant");
		System.out.println(" 2. High Score");
		System.out.println(" 3. Exit");
		InputHandler.getValidIntInput(" Input [1..3]: ", 1, 3);
	}
	
	private void showTitleScreen() {
		System.out.println(" Da Resto");
		InputHandler.pressEnterToContinue();
	}

}
