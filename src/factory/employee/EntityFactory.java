package factory.employee;

import factory.NameFactory;
import manager.game.GameManager;
import model.Customer;
import model.Restaurant;
import model.employee.Chef;
import model.employee.Waiter;

public abstract class EntityFactory {
	Restaurant currentRestaurant = GameManager.getInstance().getActiveRestaurant();
	
	public static Chef chefFactory() {
		return new Chef(NameFactory.generateInitial());
	}
	
	public static Waiter waiterFactory() {
		return new Waiter(NameFactory.generateInitial());
	}
	
	public static Customer customerFactory() {
		return new Customer(NameFactory.generateInitial());
	}
}
