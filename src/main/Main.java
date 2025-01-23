package main;

import manager.GameManager;

public class Main {
	GameManager gameManager = GameManager.getInstance();
	
	public Main() {
		gameManager.startGame();
		System.out.println("Test");
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
