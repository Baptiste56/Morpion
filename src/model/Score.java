package model;

import java.util.ArrayList;

import observer.*;

public class Score implements Observable{
	private Result result = null;
	private ArrayList<Observer> listObservers = new ArrayList<Observer>();
	
	public Score(){

	}

	@Override
	public void addObserver(Observer obs) {
		listObservers.add(obs);
	}
	
	public void setResult(Result result){
		this.result = result;
	}

	@Override
	public void notifyObservers() {
		for(Observer obs : listObservers){
			obs.update(this, result);
		}	
	}

}
