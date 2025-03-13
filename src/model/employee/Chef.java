package model.employee;

import manager.config.employee.ChefConfig;

public class Chef extends Employee {
	
	private int skill;

	public Chef(String name) {
		super(name);
		this.skill = ChefConfig.BASE_SKILL;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
