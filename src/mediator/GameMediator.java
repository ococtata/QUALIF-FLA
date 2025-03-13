package mediator;

import java.util.ArrayList;

import manager.config.RestaurantConfig;
import manager.game.GameManager;
import model.Customer;
import model.Restaurant;
import model.employee.Chef;
import model.employee.Waiter;

public class GameMediator {
	
	private static GameMediator instance;

	private ArrayList<Waiter> waiterList = new ArrayList<Waiter>();
	private ArrayList<Chef> chefList = new ArrayList<Chef>();
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	private GameMediator() {
		// TODO Auto-generated constructor stub
	}
	
	public static GameMediator getInstance() {
		if(instance == null) instance = new GameMediator();
		return instance;
	}
	
	public void addChef(Chef chef) {
		chefList.add(chef);
	}
	
	public void addWaiter(Waiter waiter) {
		waiterList.add(waiter);
	}
	
	public void addCustomer(Customer customer) {
		customerList.add(customer);
	}
	
	public void customerLeft(Customer customer) {
		Restaurant restaurant = GameManager.getInstance().getActiveRestaurant();
		
		restaurant.reduceScore(RestaurantConfig.REDUCE_SCORE_AMOUNT);
		customerList.remove(customer);
		restaurant.getCustomerList().remove(customer);
	}

	public ArrayList<Waiter> getWaiterList() {
		return waiterList;
	}

	public ArrayList<Chef> getChefList() {
		return chefList;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
}
