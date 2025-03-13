package model;

import mediator.GameMediator;

public abstract class Entity implements Runnable {
	private GameMediator mediator;
    private boolean running;

	private String name;
	
	public Entity(String name) {
		this.name = name;
		this.mediator = GameMediator.getInstance();
		this.running = true;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	protected GameMediator getMediator() {
		return mediator;
	}
}
