package manager.game;

import java.io.IOException;

import controller.InputHandler;
import mediator.GameMediator;
import model.Restaurant;
import model.employee.Chef;
import model.employee.Waiter;
import thread.CustomerSpawnerThread;
import utility.ScannerUtil;

public class GameManager implements ScannerUtil{
	public static boolean exitStatus;
	private static GameManager instance;
	private Restaurant restaurant;
	private GameMediator mediator;
		
	private GameManager() {
		this.mediator = GameMediator.getInstance();
	}
	
	public static GameManager getInstance() {
		if(instance == null) instance = new GameManager();
		return instance;
	}
	
	public Restaurant getActiveRestaurant() {
		if(restaurant == null) restaurant = new Restaurant();
		return restaurant;
	}
	
	public void startGame() {
		exitStatus = false;
		
		showTitleScreen();	
		
		InputHandler.clearScreen();
		showMainMenu();
		int choice = InputHandler.getValidIntInput(" Input [1..3]: ", 1, 3);
		mainMenuChoiceHandler(choice);
				
		if(!exitStatus) {
			Thread displayThread = new Thread(this::displayLoop);
			displayThread.start();
			
			Thread inputThread = new Thread(this::inputLoop);
			inputThread.start();
			
			CustomerSpawnerThread spawner = new CustomerSpawnerThread(restaurant);
			spawner.setActive(true);
			new Thread(spawner).start();
		}
	}
	
	private void displayLoop() {
		while(!exitStatus) {
			InputHandler.clearScreen();
			displayRestaurantInfo();
			System.out.println();
			displayPeopleInRestaurant();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void inputLoop() {
		while(!exitStatus) {
			try {
				if (System.in.available() > 0) {
				    scan.nextLine(); 
				    pauseGame();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void pauseGame() {
		boolean paused = true;
		System.out.println(" PAUSED GAME");
		System.out.println(" Press ENTER to resume");
		while(paused) {
			try {
				if (System.in.available() > 0) {
				    scan.nextLine(); 
				    break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void displayRestaurantInfo() {
		System.out.printf(" \t    Restaurant \'%s\' is on Business!\n", restaurant.getName());
		System.out.println();
		displayRestaurantStatus();
		
	}
	
	private void displayRestaurantStatus() {
		System.out.println(" \t    Status");
		System.out.println(" \tMoney : Rp. " + restaurant.getMoney());
		System.out.printf( " \tScore : %d Points\n", restaurant.getScore());
		System.out.printf( " \tSize  : %d seats\n", restaurant.getSeatAmount());
	}
	
	private void displayPeopleInRestaurant() {
	    int tableSize = restaurant.getMaxPeopleSize();
	    
	    System.out.println("======================================================================");
	    System.out.printf("| %-20s | %-20s | %-20s |\n", "Customer", "Waiter", "Cook");
	    System.out.println("----------------------------------------------------------------------");

	    int waiterIndex = 0;
	    int chefIndex = 0;

	    for (int i = 0; i < tableSize; i++) {
	        String customerInfo = (i < restaurant.getCustomerList().size()) ? restaurant.getCustomerList().
	        		get(i).getName() : "";
	        String waiterInfo = "";
	        String chefInfo = "";
	        
	        while (waiterIndex < mediator.getWaiterList().size()) {
	            if (mediator.getWaiterList().get(waiterIndex) instanceof Waiter) {
	                waiterInfo = mediator.getWaiterList().get(waiterIndex).getName();
	                waiterIndex++;
	                break;
	            }
	            waiterIndex++;
	        }

	        while (chefIndex < mediator.getChefList().size()) {
	            if (mediator.getChefList().get(chefIndex) instanceof Chef) {
	                chefInfo = mediator.getChefList().get(chefIndex).getName();
	                chefIndex++;
	                break;
	            }
	            chefIndex++;
	        }
	        
	        customerInfo = String.format("%s (%d)", 
	        		customerInfo, 
	        		restaurant.getCustomerList().get(i).getTolerance());
	        
	        System.out.printf("| %-20s | %-20s | %-20s |\n", customerInfo, waiterInfo, chefInfo);
	    }

	    System.out.println("======================================================================");
	}
	
	private void showMainMenu() {
		System.out.println(" Main Menu\n");
		System.out.println(" 1. Play New Restaurant");
		System.out.println(" 2. High Score");
		System.out.println(" 3. Exit");
	}
	
	private void showTitleScreen() {
		System.out.println(" Da Resto");
		InputHandler.pressEnterToContinue();
	}
	
	private void mainMenuChoiceHandler(int choice) {
		switch (choice) {
		case 1:
			restaurant = getActiveRestaurant();	
			break;
		case 2:
			
			break;
		case 3:
			exitStatus = true;
			break;
		default:
			System.out.println(" Invalid input!");
			break;
		}
	}
	
	
}
