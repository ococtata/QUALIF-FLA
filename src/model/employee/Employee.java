package model.employee;

import manager.config.employee.EmployeeConfig;
import model.Entity;

public abstract class Employee extends Entity {
	private int speed;
	
	public Employee(String name) {
		super(name);
		this.speed = EmployeeConfig.BASE_SPEED;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
