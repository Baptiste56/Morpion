package model;

import java.util.ArrayList;

import observer.*;

public class Info implements Observable{
	private boolean endGame = false;
	private ArrayList<Observer> listObservers = new ArrayList<Observer>();
	
	public Info(Game game){
		this.endGame = game.getEndGame();
	}

	@Override
	public void addObserver(Observer obs) {
		listObservers.add(obs);
	}

	@Override
	public void notifyObservers() {
		for(Observer obs : listObservers){
			obs.update(this, endGame);
		}		
	}
}
