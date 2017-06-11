package model;

import java.util.ArrayList;

import observer.*;
import view.*;

public class Game implements Observable{
	private Administrator admin;
	private Guest guest;
	private GameWindow fen = null;
	private Result result = Result.Draw;
	private boolean endGame = false;
	private Player playerTurn;
	private Grid grid = new Grid(this);
	private Info info = new Info(this);
	private Score score = new Score();
	private ArrayList<Observer> listObservers = new ArrayList<Observer>();
	
	public Game(){
		admin = new Administrator("Baptiste");
		admin.setSymbol(Symbol.Cross);
		guest = new Guest("Paulo");
		guest.setSymbol(Symbol.Circle);
	}
	
	public Game(Administrator admin, Guest guest){
		this.admin = admin;
		admin.setSymbol(Symbol.Cross);
		this.guest = guest;
		guest.setSymbol(Symbol.Circle);
	}
	
	public void lauchGame(){
		playerTurn = guest;
		fen = new GameWindow(this);
	}
	
	public void nextTurn(){
		if(!endGame)
			this.changePlayer();
	}
		
	private void changePlayer(){
		if(playerTurn == guest)
			playerTurn = admin;
		else
			playerTurn = guest;
		this.notifyObservers();
	}
	
	public void endGame(int resultInt){
		if (resultInt == 1)
			result = Result.Administrator;
		else if(resultInt == 2)
			result = Result.Guest;
		endGame = true;
		info.notifyObservers();
		score.setResult(result);
		score.notifyObservers();
		fen.requestFocus();
	}
	
	public void newGame(){
		endGame = false;
		grid.resetGrid();
		nextTurn();
	}

	public Player getPlayerTurn() {
		return playerTurn;
	}
	
	public Score getScore(){
		return score;
	}
	
	public Info getInfo(){
		return info;
	}
	
	public Grid getGrid(){
		return this.grid;
	}
	
	public Result getResult(){
		return result;
	}
	
	public Administrator getAdmin(){
		return admin;
	}
	
	public Guest getGuest(){
		return guest;
	}
	
	public boolean getEndGame(){
		return endGame;
	}

	@Override
	public void addObserver(Observer obs) {
		this.listObservers.add(obs);
	}

	@Override
	public void notifyObservers() {
		    for(Observer obs : this.listObservers )
		      obs.update(this, this.playerTurn);
	}		
}
