package model;

import utility.RandomUtil;

public class Customer extends Entity implements RandomUtil {
	
	private int tolerance;
	
	public Customer(String name) {
		super(name);
		this.tolerance = 10 + rand.nextInt(10);
	}

	public int getTolerance() {
		return tolerance;
	}

	public void reduceTolerance(int amount) {
        tolerance -= amount;
        if (tolerance <= 0) {
            getMediator().customerLeft(this);
            setRunning(false);
        }
    }

	@Override
	public void run() {
		while(isRunning()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			reduceTolerance(1);
		}
		
	}
}
