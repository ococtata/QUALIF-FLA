package model;

import java.util.ArrayList;

import controller.InputHandler;
import factory.employee.EntityFactory;
import manager.config.RestaurantConfig;
import mediator.GameMediator;
import model.employee.Chef;
import model.employee.Waiter;

public class Restaurant {
	private String name;
	private ArrayList<String> usedInitials;
	private int seatAmount;
	private int money;
	private ArrayList<Customer> customerList;
	private ArrayList<Chef> chefList;
	private ArrayList<Waiter> waiterList;
	
	private int score;
	private int chefCount;
	private int waiterCount;
	
	public Restaurant() {
	    super();
	    this.name = "";
	    this.seatAmount = 4;
	    this.money = 1300;
	    this.score = 0;
	    this.customerList = new ArrayList<Customer>();
	    this.chefList = new ArrayList<Chef>();
	    this.waiterList = new ArrayList<Waiter>();
	    this.usedInitials = new ArrayList<String>();
	    getRestaurantName();
	}
	
	private void getRestaurantName() {
	    String name = "";
	    while (true) {
	        name = InputHandler.getValidStringInput(" Enter Restaurant name [length 3 - 15]: ");
	        if (name.length() >= 3 && name.length() <= 15) {
	            setName(name);
	            break;
	        } 
	        else {
	            System.out.println("Invalid input! The name must be between 3 and 15 characters.");
	        }
	    }
	}
	
	public void initializeEmployees() {
		for(int i = 0; i < RestaurantConfig.EMPLOYEE_BASE_COUNT; i++) {
			Chef chef = EntityFactory.chefFactory();
			this.chefList.add(chef);
			GameMediator.getInstance().addChef(chef);	
			
			Waiter waiter = EntityFactory.waiterFactory();
			this.waiterList.add(waiter);
			GameMediator.getInstance().addWaiter(waiter);
		}		
		
	}
	
	public boolean hasInitial(String initial) {
		return this.usedInitials.contains(initial);
	}
	
	public boolean hasAvailableSeats() {
		return this.seatAmount > customerList.size();
	}
	
	public int getMaxPeopleSize() {
		int max = 0;
		if(chefCount > max) max = chefCount;
		if(waiterCount > max) max = waiterCount;
		
		int customerCount = customerList.size();
		if(customerCount > max) max = customerCount;
		return max;
	}
	
	public void reduceScore(int amount) {
		setScore(getScore() - amount);
	}
	
	public void increaseScore(int amount) {
		setScore(getScore() + amount);
	}
	
//	public void addChef(Chef chef) {
//		chefList.add(chef);
//	}
//	
//	public void addWaiter(Waiter waiter) {
//		waiterList.add(waiter);
//	}
	
	public void addCustomer(Customer customer) {
		customerList.add(customer);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeatAmount() {
		return seatAmount;
	}

	public void setSeatAmount(int seatAmount) {
		this.seatAmount = seatAmount;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
}