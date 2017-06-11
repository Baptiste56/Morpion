package model;

import java.util.*;

import observer.Observable;
import observer.Observer;
import controller.*;

public class Grid implements Observable{
	private Game game;
	private Player playerTurn;
	private GridController gridController = null;
	private int grid [] = new int[9]; //0 : Empty / 1 : Admin / 2 : Guest
	private ArrayList<Observer> listObservers = new ArrayList<Observer>();
	private List<List<Integer>> solutions = new ArrayList<List<Integer>>();
	private ArrayList<Integer> squareAdmin = new ArrayList<Integer>();
	private ArrayList<Integer> squareGuest = new ArrayList<Integer>();
	private int lastAdded = 0;
	
	public Grid(Game game){
		this.game = game;
		Arrays.fill(grid, 0);
		this.generateSolutions();
		gridController = new GridController(this);
	}
	
	public void generateSolutions(){
		solutions = Arrays.asList(Arrays.asList(1,2,3),
									Arrays.asList(1,4,7),
									Arrays.asList(3,6,9),
									Arrays.asList(7,8,9),
									Arrays.asList(1,5,9),
									Arrays.asList(2,5,8),
									Arrays.asList(3,5,7),
									Arrays.asList(4,5,6));
	}
	
	public void addSymbol(int s){
		if(!game.getEndGame()){
		playerTurn = game.getPlayerTurn();
		lastAdded = s;
		if(playerTurn.getClass() == Administrator.class){
			squareAdmin.add(s);
			grid[s-1]=1;}
		if(playerTurn.getClass() == Guest.class){
			squareGuest.add(s);
			grid[s-1]=2;}
		notifyObservers();
		analyseGame();
		game.nextTurn();
		}
	}
	
	public void resetGrid(){
		Arrays.fill(grid, 0);
		squareAdmin.clear();
		squareGuest.clear();
		lastAdded = 0;
		notifyObservers();
	}
	
	@SuppressWarnings("unchecked")
	public void analyseGame(){
		List<List<Integer>> temp = null;
		List<Integer> t = null;
		int resultInt = 0;
		if(playerTurn.getClass() == Administrator.class){
			t = (ArrayList<Integer>)squareAdmin.clone();
			resultInt = 1;
		}
		else if(playerTurn.getClass() == Guest.class){
			t = (ArrayList<Integer>)squareGuest.clone();
			resultInt = 2;
		}
		t.sort(null);
		if(t.size() == 3)
			temp = Arrays.asList(Arrays.asList(t.get(0),t.get(1),t.get(2)));
		else if(t.size() == 4){
			temp = Arrays.asList(Arrays.asList(t.get(0),t.get(1),t.get(2)),
									Arrays.asList(t.get(1),t.get(2),t.get(3)),
									Arrays.asList(t.get(0),t.get(2),t.get(3)),
									Arrays.asList(t.get(0),t.get(1),t.get(3)));

		}
		if(temp != null){
			for(List<Integer> l : temp){
				if(solutions.contains(l)){
					for(int i = 0 ; i <=2 ; i++){
						lastAdded = l.get(i);
						notifyObservers();
					}
					game.endGame(resultInt);
				}
			}
		}
	}
	
	public GridController getGridController(){
		return this.gridController;
	}
	
	public int[] getTabGrid(){
		return this.grid;
	}
	
	@Override
	public void addObserver(Observer obs) {
		this.listObservers.add(obs);
	}

	@Override
	public void notifyObservers() {
		for(Observer obs : listObservers)
			obs.update(this, lastAdded);		
	}

}
