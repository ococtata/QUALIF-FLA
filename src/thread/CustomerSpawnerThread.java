package thread;

import java.util.ArrayList;

import factory.employee.EntityFactory;
import manager.config.CustomerConfig;
import mediator.GameMediator;
import model.Customer;
import model.Restaurant;
import observer.IObserver;
import observer.ISubject;
import utility.RandomUtil;

public class CustomerSpawnerThread implements Runnable, RandomUtil, ISubject {
    private Restaurant restaurant;
    private boolean active;
    private GameMediator mediator;
    private ArrayList<IObserver> observers;

    public CustomerSpawnerThread(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.mediator = GameMediator.getInstance();
        this.active = false;
        this.observers = new ArrayList<>();
    }

    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            notifyObservers();
        }
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

	@Override
	public void notifyObservers() {
		for(IObserver observer : observers) {
			observer.notify(this);
		}
	}
	
	@Override
	public void run() {
		while (true) {
			if(active && restaurant.hasAvailableSeats()) {
				if (rand.nextFloat() < CustomerConfig.SPAWN_RATE) {
					spawnCustomer();
				}
			}
			
			try {
				Thread.sleep(CustomerConfig.SPAWN_SPEED);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void spawnCustomer() {
        Customer newCustomer = EntityFactory.customerFactory();
        mediator.addCustomer(newCustomer);
        restaurant.addCustomer(newCustomer);
        
        new Thread(newCustomer).start();
    }
}
