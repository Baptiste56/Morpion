package model;

public abstract class Player {
	private String name = "default";
	private int score = 0;
	private Symbol symbol;
	
	public Player(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
}
